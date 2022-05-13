package nickolay.ponomarev.cardissuer.controller.bank;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.service.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.bank.BankAccountEmployeeSource;
import nickolay.ponomarev.cardissuer.dto.BankAccountDto;
import nickolay.ponomarev.cardissuer.service.bank.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * Контроллер для банковских работников по управлению банковскими аккаунтами
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class BankAccountEmployeeController implements BankAccountEmployeeSource {

    private final BankAccountService bankAccountService;

    @Override
    @Audit("Получение банковского аккаунта")
    public ResponseEntity<BankAccountDto> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankAccountService.getBankAccountById(id));
    }

    @Override
    @Audit("Получение номера из банковского аккаунта")
    public ResponseEntity<String> getAccountNumber(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankAccountService.getAccountNumberById(id));
    }

    @Override
    @Audit("Получение всех банковских аккаунтов из клиента")
    public ResponseEntity<List<UUID>> getAllFromClient(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankAccountService.getAllBankAccountsFromClient(id));
    }

    @Override
    @Audit("Удаление банковского аккаунта")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
       bankAccountService.deleteBankAccount(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @Audit("Блокировка или разблокировка банковского аккаунта")
    public ResponseEntity<Void> blockAccountOperation(@PathVariable("id") UUID id, @RequestParam Boolean blocked) {
        bankAccountService.blockAccountOperation(id, blocked);
        return ResponseEntity.ok().build();
    }

}
