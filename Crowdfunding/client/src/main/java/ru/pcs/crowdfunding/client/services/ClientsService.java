package ru.pcs.crowdfunding.client.services;

import org.springframework.web.multipart.MultipartFile;
import ru.pcs.crowdfunding.client.domain.Client;
import ru.pcs.crowdfunding.client.dto.ClientDto;
import ru.pcs.crowdfunding.client.dto.ClientForm;
import ru.pcs.crowdfunding.client.dto.ImageDto;
import ru.pcs.crowdfunding.client.dto.ProjectDto;

import java.util.Optional;

public interface ClientsService {
    Optional<ClientDto> findById(Long id);

    ClientForm updateClient(Long clientId, ClientForm form, MultipartFile file);

    Optional<ImageDto> getImageById(Long id);

    Long getAccountIdByClientId(Long clientId);

    Optional<Client> findByProject(ProjectDto projectDto);
}
