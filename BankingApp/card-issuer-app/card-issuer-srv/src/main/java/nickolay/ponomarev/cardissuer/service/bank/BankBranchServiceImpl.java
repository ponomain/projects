package nickolay.ponomarev.cardissuer.service.bank;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.dto.BankBranchDto;
import nickolay.ponomarev.cardissuer.mapper.BankBranchMapper;
import nickolay.ponomarev.cardissuer.model.BankBranch;
import nickolay.ponomarev.cardissuer.model.Deletable;
import nickolay.ponomarev.cardissuer.repository.BankBranchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 27.02.2022
 * Bank branch service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class BankBranchServiceImpl implements BankBranchService {

    private final BankBranchRepository bankBranchRepository;
    private final BankBranchMapper bankBranchMapper;

    @Override
    public BankBranchDto getBankBranchById(@NotNull UUID id) throws IllegalArgumentException{
        BankBranch existingBranch = bankBranchRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Branch with id -" + id + "didn't found"));
        return bankBranchMapper.toDto(existingBranch);
    }

    @Override
    public List<UUID> getAllBankBranches(int page, int size) {
        PageRequest request = PageRequest.of(page, size, Sort.by("id"));
        Page<BankBranch> result = bankBranchRepository.findAll(request);
        List<BankBranch> branches = result.getContent();
        return branches.stream().map(Deletable::getId).collect(Collectors.toList());
    }
}
