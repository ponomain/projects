package nickolay.ponomarev.cardissuer.controller.bank;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.service.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.bank.BankCardEmployeeSource;
import nickolay.ponomarev.cardissuer.dto.BankCardDto;
import nickolay.ponomarev.cardissuer.service.bank.BankCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 24.02.2022
 * Контроллер для банковских работников по управлению банковскими картами
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class BankCardEmployeeController implements BankCardEmployeeSource {

    private final BankCardService bankCardService;

    @Override
    @Audit("Получение банковской карты")
    public ResponseEntity<BankCardDto> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankCardService.getBankCardById(id));
    }

    @Override
    @Audit("Получение номера банковской карты")
    public ResponseEntity<String> getNumberOfCard(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankCardService.getCardNumberById(id));
    }

    @Override
    @Audit("Получение всех банковских карт из банковского аккаунта")
    public ResponseEntity<List<UUID>> getAllFromAccount(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankCardService.getAllBankCardsFromAccount(id));
    }

    @Override
    @Audit("Добавление новой банковской карты")
    public ResponseEntity<BankCardDto> add(@RequestBody BankCardDto cardDto) {
        return ResponseEntity.ok(bankCardService.createBankCard(cardDto));
    }

    @Override
    @Audit("Обновление существующей банковской карта")
    public ResponseEntity<BankCardDto> updateServices(@PathVariable("id") UUID id, @RequestBody Set<UUID> services) {
        return ResponseEntity.ok(bankCardService.updateBankCard(id, services));
    }

    @Override
    @Audit("Удаление банковской карты")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        bankCardService.deleteBankCard(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @Audit("Временная блокировка банковской карты")
    public ResponseEntity<Void> temporaryBlock(@PathVariable("id") UUID id) {
        bankCardService.temporaryBlockCard(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @Audit("Постоянная блокировка банковской карты")
    public ResponseEntity<Void> permanentBlock(@PathVariable("id") UUID id) {
        bankCardService.permanentBlockCard(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @Audit("Разблокировка банковской карты")
    public ResponseEntity<Void> unblock(@PathVariable("id") UUID id) {
        bankCardService.unBlockCard(id);
        return ResponseEntity.ok().build();
    }
}
