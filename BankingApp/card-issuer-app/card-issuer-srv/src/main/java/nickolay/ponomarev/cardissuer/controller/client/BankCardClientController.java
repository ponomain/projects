package nickolay.ponomarev.cardissuer.controller.client;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.client.BankCardClientSource;
import nickolay.ponomarev.cardissuer.dto.BankCardDto;
import nickolay.ponomarev.cardissuer.service.bank.BankCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * Контроллер для пользователей по получанию информации банковских карт
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class BankCardClientController implements BankCardClientSource {

    private final BankCardService bankCardService;

    @Override
    @Audit("Получение банковской карты")
    public ResponseEntity<BankCardDto> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankCardService.getBankCardById(id));
    }

    @Override
    @Audit("Получение реквизитов банковской карты")
    public ResponseEntity<String> getRequisites(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankCardService.getRequisitesFromId(id));
    }

    @Override
    @Audit("Получение всех банковских карт, связанных с клиентом")
    public ResponseEntity<List<UUID>> getAll(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankCardService.getAllBankCardsFromAccount(id));
    }

    @Override
    @Audit("Добавление банковского сервиса в банковскую карту")
    public ResponseEntity<BankCardDto> addServiceToCard(@PathVariable("id") UUID id, @PathVariable("service-id") UUID serviceId) {
        return ResponseEntity.ok(bankCardService.addServiceToCard(id, serviceId));
    }

    @Override
    @Audit("Блокировка банковской карты")
    public ResponseEntity<Void> block(@PathVariable("id") UUID id) {
        bankCardService.temporaryBlockCard(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @Audit("Изменение тарифа банковской карты")
    public ResponseEntity<BankCardDto> changeTariff(@PathVariable("id") UUID id, @PathVariable("tariff-id") UUID tariffId) {
        return ResponseEntity.ok(bankCardService.changeTariff(id, tariffId));
    }

    @Override
    @Audit("Изменение пин кода банковской карты")
    public ResponseEntity<BankCardDto> changePinCode(@PathVariable("id") UUID id, @RequestParam String pinCode) {
        return ResponseEntity.ok(bankCardService.changePinCode(id, pinCode));
    }
}
