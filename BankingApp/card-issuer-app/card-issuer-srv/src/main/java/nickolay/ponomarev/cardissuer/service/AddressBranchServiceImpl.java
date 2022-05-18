package nickolay.ponomarev.cardissuer.service;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.dto.AddressBranchDto;
import nickolay.ponomarev.cardissuer.mapper.AddressBranchMapper;
import nickolay.ponomarev.cardissuer.model.AddressBranch;
import nickolay.ponomarev.cardissuer.model.BankBranch;
import nickolay.ponomarev.cardissuer.repository.AddressRepository;
import nickolay.ponomarev.cardissuer.repository.BankBranchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * 14.02.2022
 * Сервис для работы с адресами
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AddressBranchServiceImpl implements AddressBranchService {

    private final AddressRepository addressRepository;
    private final AddressBranchMapper addressBranchMapper;
    private final BankBranchRepository bankBranchRepository;

    @Override
    public AddressBranchDto getAddressByIdFromBranch(@NotNull UUID id) throws IllegalArgumentException{
        BankBranch existingBranch = bankBranchRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Branch with id -" + id + "didn't found"));
        AddressBranch existingAddressBranch = existingBranch.getAddressBranch();
        return addressBranchMapper.toDto(existingAddressBranch);
    }

    @Override
    @Transactional
    public AddressBranchDto createAddress(@NotNull AddressBranchDto addressBranchDto) {
        AddressBranch newAddressBranch = new AddressBranch();
        addressBranchMapper.toSource(addressBranchDto, newAddressBranch);
        newAddressBranch = addressRepository.save(newAddressBranch);
        return addressBranchMapper.toDto(newAddressBranch);
    }

    @Override
    public void deleteAddress(@NotNull UUID id) {
        addressRepository.deleteById(id);
    }
}
