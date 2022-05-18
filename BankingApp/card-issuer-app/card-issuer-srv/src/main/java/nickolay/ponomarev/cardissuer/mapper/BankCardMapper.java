package nickolay.ponomarev.cardissuer.mapper;

import nickolay.ponomarev.cardissuer.dto.BankCardDto;
import nickolay.ponomarev.cardissuer.model.BankCard;
import org.mapstruct.*;

/**
 * 25.02.2022
 * Bank card mapper
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Mapper(uses = {BankAccountMapper.class, CardTariffMapper.class, BankServiceMapper.class})
public interface BankCardMapper extends CardIssuerMapper<BankCard, BankCardDto>{

    @Override
    @Mapping(target = "account.bankCards", ignore = true)
    BankCardDto toDto(BankCard bankCard);

    @Override
    @Mapping(target = "account.bankCards", ignore = true)
    BankCard toSource(BankCardDto bankCardDto, @MappingTarget BankCard bankCard);

}
