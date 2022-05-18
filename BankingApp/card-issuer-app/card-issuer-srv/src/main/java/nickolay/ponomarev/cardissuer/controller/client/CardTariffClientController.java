package nickolay.ponomarev.cardissuer.controller.client;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.client.CardTariffClientSource;
import nickolay.ponomarev.cardissuer.dto.CardTariffDto;
import nickolay.ponomarev.cardissuer.service.bank.CardTariffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * Контроллер для пользователей по получению информации тарифов карт
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class CardTariffClientController implements CardTariffClientSource {

    private final CardTariffService cardTariffService;

    @Override
    @Audit("Получение тарифа для банковских карт")
    public ResponseEntity<CardTariffDto> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(cardTariffService.getCardTariffById(id));
    }

    @Override
    @Audit("Получение всех тарифов банковских карт")
    public ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(cardTariffService.getAllCardTariffs(page, size));
    }
}
