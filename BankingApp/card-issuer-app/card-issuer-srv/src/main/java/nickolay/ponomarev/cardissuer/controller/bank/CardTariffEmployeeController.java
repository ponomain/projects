package nickolay.ponomarev.cardissuer.controller.bank;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.bank.CardTariffEmployeeSource;
import nickolay.ponomarev.cardissuer.dto.CardTariffDto;
import nickolay.ponomarev.cardissuer.service.bank.CardTariffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * Контроллер для банковских работников по управлению тарифами карт
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class CardTariffEmployeeController implements CardTariffEmployeeSource {

    private final CardTariffService cardTariffService;

    @Override
    @Audit("Получение тарифа для банковских карт")
    public ResponseEntity<CardTariffDto> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(cardTariffService.getCardTariffById(id));
    }

    @Override
    @Audit("Получение всех тарифов для банковских карт")
    public ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(cardTariffService.getAllCardTariffs(page, size));
    }

    @Override
    @Audit("Добавление нового тарифа для банковских карт")
    public ResponseEntity<CardTariffDto> add(@RequestBody CardTariffDto cardTariffDto) {
        return ResponseEntity.ok(cardTariffService.createCardTariff(cardTariffDto));
    }

    @Override
    @Audit("Удаление тарифа для банковских карт")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        cardTariffService.deleteCardTariff(id);
        return ResponseEntity.ok().build();
    }
}
