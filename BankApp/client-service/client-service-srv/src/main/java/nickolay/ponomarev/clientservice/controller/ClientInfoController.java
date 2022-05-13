package nickolay.ponomarev.clientservice.controller;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.clientservice.dto.ClientInfoDto;
import nickolay.ponomarev.clientservice.source.ClientInfoSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nickolay.ponomarev.clientservice.audit.Audit;
import nickolay.ponomarev.clientservice.service.ClientInfoService;

import java.util.List;
import java.util.UUID;

/**
 * 11.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class ClientInfoController implements ClientInfoSource {

    private final ClientInfoService clientInfoService;

    @Override
    @Audit("Получение информации по клиенту")
    public ResponseEntity<ClientInfoDto> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(clientInfoService.getClientById(id));
    }

    @Override
    @Audit("Получение информации по всем клиентам")
    public ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(clientInfoService.getAllClients(page, size));
    }

    @Override
    @Audit("Получение емейла у клиента")
    public ResponseEntity<String> getEmail(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(clientInfoService.getEmailFromId(id));
    }

    @Override
    @Audit("Получение номера телефона у клиена")
    public ResponseEntity<String> getPhoneNumber(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(clientInfoService.getPhoneNumberFromId(id));
    }

    @Override
    @Audit("Получение ИНН у клиента")
    public ResponseEntity<String> getInn(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(clientInfoService.getInnFromId(id));
    }

    @Override
    @Audit("Добавление нового клиента")
    public ResponseEntity<ClientInfoDto> add(@RequestBody ClientInfoDto clientDto) {
        return ResponseEntity.ok(clientInfoService.createClient(clientDto));
    }

    @Override
    @Audit("Обновление существующего клиента")
    public ResponseEntity<ClientInfoDto> update(@PathVariable("id") UUID id, @RequestBody ClientInfoDto clientDto) {
        return ResponseEntity.ok(clientInfoService.updateClient(id, clientDto));
    }

    @Override
    @Audit("Удаление клиента")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        clientInfoService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
