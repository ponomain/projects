package nickolay.ponomarev.clientservice.service;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.clientservice.dto.IdentityDocumentDto;
import nickolay.ponomarev.clientservice.model.Deletable;
import nickolay.ponomarev.clientservice.model.IdentityDocument;
import nickolay.ponomarev.clientservice.repository.IdentityDocumentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import nickolay.ponomarev.clientservice.mapper.IdentityDocumentMapper;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class IdentityDocumentServiceImpl implements IdentityDocumentService {

    private final IdentityDocumentRepository identityDocumentRepository;

    private final IdentityDocumentMapper identityDocumentMapper;


    @Override
    public IdentityDocumentDto getIdentityDocumentById(@NotNull UUID id) throws IllegalArgumentException {
        IdentityDocument existingDocument = identityDocumentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Document with id -" + id + "didn't found"));
        return identityDocumentMapper.toDto(existingDocument);
    }

    @Override
    public List<UUID> getAllIdentityDocuments(int page, int size) {
        PageRequest request = PageRequest.of(page, size, Sort.by("id"));
        Page<IdentityDocument> result = identityDocumentRepository.findAll(request);
        List<IdentityDocument> identityDocuments = result.getContent();
        return identityDocuments.stream().map(Deletable::getId).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public IdentityDocumentDto createIdentityDocument(@NotNull IdentityDocumentDto identityDocumentDto) {
        IdentityDocument newIdentityDocument = new IdentityDocument();
        identityDocumentMapper.toSource(identityDocumentDto, newIdentityDocument);
        newIdentityDocument = identityDocumentRepository.save(newIdentityDocument);
        return identityDocumentMapper.toDto(newIdentityDocument);
    }

    @Override
    @Transactional
    public IdentityDocumentDto updateIdentityDocument(@NotNull UUID id, @NotNull IdentityDocumentDto identityDocumentDto) throws IllegalArgumentException {
        IdentityDocument existingIdentityDocument = identityDocumentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Document with id -" + id + "didn't found"));
        identityDocumentMapper.toSource(identityDocumentDto, existingIdentityDocument);
        existingIdentityDocument = identityDocumentRepository.save(existingIdentityDocument);
        return identityDocumentMapper.toDto(existingIdentityDocument);
    }

    @Override
    @Transactional
    public void deleteIdentityDocument(@NotNull UUID id) {
        identityDocumentRepository.deleteById(id);
    }

    @Override
    public String getNumberFromId(@NotNull UUID id) throws IllegalArgumentException {
        IdentityDocument existingIdentityDocument = identityDocumentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Document with id -" + id + "didn't found"));
        String number = existingIdentityDocument.getNumber();
        return number.substring(number.length() - 3);
    }

}
