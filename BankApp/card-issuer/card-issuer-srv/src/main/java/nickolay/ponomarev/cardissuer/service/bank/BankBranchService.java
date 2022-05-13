package nickolay.ponomarev.cardissuer.service.bank;

import nickolay.ponomarev.cardissuer.dto.BankBranchDto;

import java.util.List;
import java.util.UUID;

/**
 * 27.02.2022
 * Bank branch service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface BankBranchService {

    BankBranchDto getBankBranchById(UUID id);

    List<UUID> getAllBankBranches(int page, int size);
}
