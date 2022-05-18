package nickolay.ponomarev.cardissuer.mapper;

import nickolay.ponomarev.cardissuer.dto.BankAccountDto;
import nickolay.ponomarev.cardissuer.dto.BankCardDto;
import nickolay.ponomarev.cardissuer.model.BankAccount;
import nickolay.ponomarev.cardissuer.model.BankCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * 25.04.2022
 * card-issuer
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Mapper
public interface BankAccountMapper extends CardIssuerMapper<BankAccount, BankAccountDto> {
}
