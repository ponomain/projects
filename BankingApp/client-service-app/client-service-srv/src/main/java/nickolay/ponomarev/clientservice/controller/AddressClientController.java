package nickolay.ponomarev.clientservice.controller;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.clientservice.dto.AddressClientDto;
import nickolay.ponomarev.clientservice.source.AddressClientSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import nickolay.ponomarev.clientservice.audit.Audit;
import nickolay.ponomarev.clientservice.service.AddressClientService;

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
public class AddressClientController implements AddressClientSource {

    private final AddressClientService addressClientService;

    @Override
    @Audit("Получение адреса у клиента")
    public ResponseEntity<AddressClientDto> getFromClient(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(addressClientService.getAddressByIdFromClient(id));
    }

    @Override
    @Audit("Добавление нового адреса")
    public ResponseEntity<AddressClientDto> add(@RequestBody AddressClientDto addressClientDto) {
        return ResponseEntity.ok(addressClientService.createAddress(addressClientDto));
    }

    @Override
    @Audit("Удаление адреса")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        addressClientService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}
