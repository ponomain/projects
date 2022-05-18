package nickolay.ponomarev.cardissuer.mapper;

import nickolay.ponomarev.cardissuer.dto.CardTariffDto;
import nickolay.ponomarev.cardissuer.model.CardTariff;
import org.mapstruct.Mapper;

/**
 * 25.02.2022
 * Card tariff mapper
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Mapper
public interface CardTariffMapper extends CardIssuerMapper<CardTariff, CardTariffDto>{

}
