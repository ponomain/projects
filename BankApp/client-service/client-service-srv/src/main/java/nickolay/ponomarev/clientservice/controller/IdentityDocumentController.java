package nickolay.ponomarev.clientservice.controller;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.clientservice.dto.IdentityDocumentDto;
import nickolay.ponomarev.clientservice.source.IdentityDocumentSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nickolay.ponomarev.clientservice.audit.Audit;
import nickolay.ponomarev.clientservice.service.IdentityDocumentService;

import java.util.List;
import java.util.UUID;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class IdentityDocumentController implements IdentityDocumentSource {

    private final IdentityDocumentService identityDocumentService;

    @Override
    @Audit("Получение идентификационного документа")
    public ResponseEntity<IdentityDocumentDto> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(identityDocumentService.getIdentityDocumentById(id));
    }

    @Override
    @Audit("Получение всех идентификационных документов")
    public ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(identityDocumentService.getAllIdentityDocuments(page, size));
    }

    @Override
    @Audit("Добавление нового идентификационного документа")
    public ResponseEntity<IdentityDocumentDto> add(@RequestBody IdentityDocumentDto identityDocumentDto) {
        return ResponseEntity.ok(identityDocumentService.createIdentityDocument(identityDocumentDto));
    }

    @Override
    @Audit("Получение номера идентификационного документа")
    public ResponseEntity<String> getNumber(UUID id) {
        return ResponseEntity.ok(identityDocumentService.getNumberFromId(id));
    }

    @Override
    @Audit("Обновление идентификационного документа")
    public ResponseEntity<IdentityDocumentDto> update(@PathVariable("id") UUID id, @RequestBody IdentityDocumentDto identityDocumentDto) {
        return ResponseEntity.ok(identityDocumentService.updateIdentityDocument(id, identityDocumentDto));
    }

    @Override
    @Audit("Удаление идентификационного документа")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        identityDocumentService.deleteIdentityDocument(id);
        return ResponseEntity.ok().build();
    }
}
