package nickolay.ponomarev.cardissuer.mapper;

import nickolay.ponomarev.cardissuer.dto.AddressBranchDto;
import nickolay.ponomarev.cardissuer.model.AddressBranch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * 25.02.2022
 * Address mapper
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Mapper(uses = {BankBranchMapper.class})
public interface AddressBranchMapper extends CardIssuerMapper<AddressBranch, AddressBranchDto>{

    @Override
    AddressBranchDto toDto(AddressBranch addressBranch);

    @Override
    AddressBranch toSource(AddressBranchDto addressBranchDto, @MappingTarget AddressBranch addressBranch);
}
