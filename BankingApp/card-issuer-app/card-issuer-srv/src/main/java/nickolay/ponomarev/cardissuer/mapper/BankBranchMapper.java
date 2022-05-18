package nickolay.ponomarev.cardissuer.mapper;

import nickolay.ponomarev.cardissuer.dto.BankBranchDto;
import nickolay.ponomarev.cardissuer.model.BankBranch;
import org.mapstruct.Mapper;

/**
 * 25.02.2022
 * Bank branch mapper
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Mapper(uses = AddressBranchMapper.class)
public interface BankBranchMapper extends CardIssuerMapper<BankBranch, BankBranchDto>{

    @Override
    BankBranchDto toDto(BankBranch bankBranch);
}
