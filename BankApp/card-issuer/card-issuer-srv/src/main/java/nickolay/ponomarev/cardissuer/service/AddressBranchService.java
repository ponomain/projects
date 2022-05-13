package nickolay.ponomarev.cardissuer.service;

import nickolay.ponomarev.cardissuer.dto.AddressBranchDto;

import java.util.UUID;

/**
 * 14.02.2022
 * Сервис для работы с адресами
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface AddressBranchService {

    AddressBranchDto getAddressByIdFromBranch(UUID id);

    AddressBranchDto createAddress(AddressBranchDto addressBranchDto);

    void deleteAddress(UUID id);

}
