package nickolay.ponomarev.cardissuer.controller.client;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.dto.BankAccountDto;
import nickolay.ponomarev.cardissuer.service.audit.Audit;
import nickolay.ponomarev.cardissuer.service.bank.BankAccountService;
import nickolay.ponomarev.cardissuer.sources.client.BankAccountClientSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 20.03.2022
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class BankAccountClientController implements BankAccountClientSource {

    private final BankAccountService bankAccountService;

    @Override
    @Audit("Получение информации из банковского аккаунта")
    public ResponseEntity<String> getInformationFromAccount(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankAccountService.getInformationFromId(id));
    }

    @Override
    @Audit("Получение аккаунтов у клиента")
    public ResponseEntity<List<UUID>> getAccounts(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankAccountService.getAllBankAccountsFromClient(id));
    }

    @Override
    @Audit("Получение аккаунта по ID")
    public ResponseEntity<BankAccountDto> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankAccountService.getBankAccountById(id));
    }
}
