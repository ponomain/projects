package nickolay.ponomarev.clientservice.service;

import nickolay.ponomarev.clientservice.dto.IdentityDocumentDto;

import java.util.List;
import java.util.UUID;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface IdentityDocumentService {

    IdentityDocumentDto getIdentityDocumentById(UUID id);

    List<UUID> getAllIdentityDocuments(int page, int size);

    IdentityDocumentDto createIdentityDocument(IdentityDocumentDto identityDocumentDto);

    IdentityDocumentDto updateIdentityDocument(UUID id, IdentityDocumentDto identityDocumentDto);

    void deleteIdentityDocument(UUID id);

    String getNumberFromId(UUID id);
}
