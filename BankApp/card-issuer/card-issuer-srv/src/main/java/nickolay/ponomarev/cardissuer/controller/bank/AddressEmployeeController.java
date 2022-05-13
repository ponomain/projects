package nickolay.ponomarev.cardissuer.controller.bank;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.dto.AddressBranchDto;
import nickolay.ponomarev.cardissuer.service.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.bank.AddressEmployeeSource;
import nickolay.ponomarev.cardissuer.service.AddressBranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

/**
 * 24.02.2022
 * Контроллер для работников банка по упралению адресами клиентов и банковских отделений
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class AddressEmployeeController implements AddressEmployeeSource {

    private final AddressBranchService addressBranchService;

    @Override
    @Audit("Получение адреса из отделения банка")
    public ResponseEntity<AddressBranchDto> getFromBranch(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(addressBranchService.getAddressByIdFromBranch(id));
    }

    @Override
    @Audit("Добавление нового адреса")
    public ResponseEntity<AddressBranchDto> add(@RequestBody AddressBranchDto addressBranchDto) {
        return ResponseEntity.ok(addressBranchService.createAddress(addressBranchDto));
    }

    @Override
    @Audit("Удаление адреса")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        addressBranchService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}
