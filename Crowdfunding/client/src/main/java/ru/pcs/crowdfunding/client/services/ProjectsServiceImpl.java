package ru.pcs.crowdfunding.client.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.pcs.crowdfunding.client.api.TransactionServiceClient;
import ru.pcs.crowdfunding.client.domain.Client;
import ru.pcs.crowdfunding.client.domain.Project;
import ru.pcs.crowdfunding.client.domain.ProjectImage;
import ru.pcs.crowdfunding.client.domain.ProjectStatus;
import ru.pcs.crowdfunding.client.dto.*;
import ru.pcs.crowdfunding.client.exceptions.ImageProcessingError;
import ru.pcs.crowdfunding.client.exceptions.DateMustBeFutureError;
import ru.pcs.crowdfunding.client.repositories.ClientsRepository;
import ru.pcs.crowdfunding.client.repositories.ProjectImagesRepository;
import ru.pcs.crowdfunding.client.repositories.ProjectStatusesRepository;
import ru.pcs.crowdfunding.client.repositories.ProjectsRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectsServiceImpl implements ProjectsService {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    private final ProjectsRepository projectsRepository;

    private final ClientsRepository clientsRepository;

    private final ProjectStatusesRepository projectStatusesRepository;

    private final ProjectImagesRepository projectImagesRepository;

    private final TransactionServiceClient transactionServiceClient;

    @Override
    public BigDecimal getMoneyCollectedByProjectId(Long projectId) {
        Project project = projectsRepository.getProjectById(projectId).get();
        return transactionServiceClient.getBalance(project.getAccountId());
    }

    @Override
    public Long getContributorsCountByProjectId(Long projectId) {
        Project project = projectsRepository.getProjectById(projectId).get();
        return transactionServiceClient.getContributorsCount(project.getAccountId());
    }

    @Override
    public List<ProjectDto> getProjectsFromClient(ClientDto clientDto) {
        List<Project> projects = clientDto.getProjects();
        return projects.stream().map(project -> {
            Optional<ProjectDto> projectDto = findById(project.getId());
            if (!projectDto.isPresent()) {
                log.error("Project didn't found");
                throw new IllegalArgumentException("Project didn't found");
            }
            return projectDto.get();
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectDto> findById(Long id) {
        Optional<Project> project = projectsRepository.findById(id);
        if (!project.isPresent()) {
            log.warn("Project with 'id' = {} not found", id);
            return Optional.empty();
        }

        Long accountId = project.get().getAccountId();
        BigDecimal balance = transactionServiceClient.getBalance(accountId);
        Long donorsCount = transactionServiceClient.getContributorsCount(accountId);
        List<Long> imagesIds = project.get().getImages().stream()
                .map(ProjectImage::getId)
                .collect(Collectors.toList());

        ProjectDto projectDto = ProjectDto.from(project.get());
        projectDto.setMoneyCollected(balance);
        projectDto.setContributorsCount(donorsCount);
        projectDto.setImagesIds(imagesIds);
        log.info("Result of 'findById' - {}", projectDto);
        return Optional.of(projectDto);
    }

    @Override
    public BigDecimal getMoneyGoal(Long projectId) {
        Optional<ProjectDto> optionalProjectDto = findById(projectId);
        if(!optionalProjectDto.isPresent()) {
            return null;
        }
        return optionalProjectDto.get().getMoneyGoal();
    }

    @Override
    @Transactional
    public Optional<Long> createProject(ProjectForm form, MultipartFile file) {
        log.info("Trying to create project from {}", form.toString());
        ProjectStatus projectStatus = projectStatusesRepository.getByStatus(ProjectStatus.Status.CONFIRMED);

        Project project = Project.builder()
                .author(clientsRepository.getById(form.getClientId()))
                .title(form.getTitle())
                .description(form.getDescription())
                .createdAt(Instant.now())
                .finishDate(getInstantFromString(form.getFinishDate(), YYYY_MM_DD))
                .moneyGoal(new BigDecimal(form.getMoneyGoal()))
                .status(projectStatus)
                .build();
        if (project.getFinishDate().isBefore(Instant.now())) {
            throw new DateMustBeFutureError();
        }

        // создаём запрос в transaction-service на создание счёта для проекта
        log.info("Trying to create account for project");
        CreateAccountResponse response = transactionServiceClient.createAccount();
        Long projectAccountId = response.getId();
        log.info("Was created new account for project with id={}", projectAccountId);
        project.setAccountId(projectAccountId);

        projectsRepository.save(project);

        if (!file.isEmpty()) {
            log.info("Try to save image with name={}", file.getOriginalFilename());
            ProjectImage projectImage = getImage(file, project);
            Long id = projectImagesRepository.save(projectImage).getId();
            log.info("Image was saved with id={}", id);
        }
        return Optional.of(project.getId());
    }

    @Override
    @Transactional
    public ProjectDto updateProject(Long id, ProjectForm form, MultipartFile file) {
        log.info("Try to update project with id={}", id);
        Optional<Project> project = projectsRepository.getProjectById(id);
        if (!project.isPresent()) {
            log.error("Project with id={} was not found", id);
            throw new IllegalArgumentException("Project was not found");
        }
        Project existedProject = project.get();
        log.info("The existed {}", existedProject);
        log.info("The new data from {}", form.toString());
        if (form.getTitle() != null) {
            existedProject.setTitle(form.getTitle());
        }
        if (form.getDescription() != null) {
            existedProject.setDescription(form.getDescription());
        }
        if (form.getFinishDate() != null) {
            Instant finishDate = getInstantFromString(form.getFinishDate(), YYYY_MM_DD);
            if (finishDate.isBefore(Instant.now())) {
                throw new DateMustBeFutureError();
            }
            existedProject.setFinishDate(finishDate);
        }
        projectsRepository.save(existedProject);
        log.info("Project data was updated successfully");
        updateProjectImage(file, existedProject);
        return ProjectDto.from(existedProject);
    }

    private Instant getInstantFromString(String input, String pattern) {
        DateTimeFormatter DTF = new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
                .toFormatter()
                .withZone(ZoneId.of("GMT"));
        return DTF.parse(input, Instant::from);
    }

    private void updateProjectImage(MultipartFile file, Project existedProject) {
        if (!file.isEmpty()) {
            log.info("Try to update project image with new image: name={} and size={}", file.getOriginalFilename(), file.getSize());
            Optional<ProjectImage> projectImage = projectImagesRepository.findProjectImageByProject(existedProject);
            try {
                if (!projectImage.isPresent()) {
                    log.info("The project has no image, create new one");
                    ProjectImage newProjectImage = ProjectImage.builder()
                            .project(existedProject)
                            .content(file.getBytes())
                            .name(file.getName())
                            .build();
                    projectImagesRepository.save(newProjectImage);
                } else {
                    projectImage.get().setContent(file.getBytes());
                    projectImage.get().setName(file.getOriginalFilename());
                    projectImagesRepository.save(projectImage.get());
                }
            } catch (IOException e) {
                log.info("Exception while updating image", e);
                throw new ImageProcessingError();
            }
            log.info("Project image was updated successfully");
        }
    }

    @Override
    public Optional<ImageDto> getImageById(Long id) {
        return projectImagesRepository.findById(id)
                .map(image -> ImageDto.builder()
                        .format(FilenameUtils.getExtension(image.getName()))
                        .content(image.getContent())
                        .build());
    }

    private ProjectImage getImage(MultipartFile file, Project project) {
        try {
            return ProjectImage.builder()
                    .content(file.getBytes())
                    .name(file.getOriginalFilename())
                    .project(project)
                    .build();
        } catch (IOException e) {
            log.error("Can't save image {}", file.getOriginalFilename(), e);
            throw new ImageProcessingError();
        }
    }

    @Override
    public List<ProjectDto> getConfirmedProjects() {
        ProjectStatus statusConfirmed = projectStatusesRepository
                .getByStatus(ProjectStatus.Status.CONFIRMED);

        List<Project> projects = projectsRepository.findProjectsByStatusEquals(
                statusConfirmed);

       return projects.stream().map(project -> findById(project.getId()).get()).collect(Collectors.toList());
    }

    @Override
    public Long getAccountIdByProjectId(Long projectId){
        Long accountId;
        Optional<ProjectDto> optionalProject = findById(projectId);
        if(!optionalProject.isPresent()) {
            return null;
        }
        return optionalProject.get().getAccountId();
    }

    @Override
    public Long getAuthorByProjectId(Long projectId){
        Long accountId;
        Optional<ProjectDto> optionalProject = findById(projectId);
        if(!optionalProject.isPresent()) {
            return null;
        }
        return optionalProject.get().getClientId();
    }

    @Override
    public void setProjectStatus(Long projectId, ProjectStatus projectStatus){

        Optional<Project> optionalProject = projectsRepository.getProjectById(projectId);

        if(!optionalProject.isPresent()) {
            throw new IllegalArgumentException("Project with id = " + projectId + " does not exits");
        }
        optionalProject.get().setStatus(projectStatus);
    }


    /**
     * @deprecated в текущей реализации (сохранение картинки в базу) данный метод не используется
     */
    private void createDirectoryIfNotExists(String path) {
        if (Files.notExists(Paths.get(path))) {
            try {
                Files.createDirectory(Paths.get(path).toAbsolutePath().normalize());
            } catch (IOException e) {
                log.error("Can't create directory {}", path, e);
                throw new IllegalArgumentException(e);
            }
        }
    }
}
