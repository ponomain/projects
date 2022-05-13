package nickolay.ponomarev.cardissuer.service.bank;

import nickolay.ponomarev.cardissuer.dto.CardTariffDto;

import java.util.List;
import java.util.UUID;

/**
 * 27.02.2022
 * Card tariff service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface CardTariffService {

    CardTariffDto getCardTariffById(UUID id);

    List<UUID> getAllCardTariffs(int page, int size);

    CardTariffDto createCardTariff(CardTariffDto cardTariffDto);

    void deleteCardTariff(UUID id);
}
