package nickolay.ponomarev.cardissuer.mapper;

import nickolay.ponomarev.cardissuer.dto.BankServiceDto;
import nickolay.ponomarev.cardissuer.model.BankService;
import org.mapstruct.Mapper;

/**
 * 25.02.2022
 * Bank service mapper
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Mapper
public interface BankServiceMapper extends CardIssuerMapper<BankService, BankServiceDto> {

}
