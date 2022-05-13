package nickolay.ponomarev.cardissuer.service.bank;

import nickolay.ponomarev.cardissuer.dto.BankAccountDto;

import java.util.List;
import java.util.UUID;

/**
 * 27.02.2022
 * Bank account service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface BankAccountService {

    BankAccountDto getBankAccountById(UUID id);

    List<UUID> getAllBankAccountsFromClient(UUID id);

    void deleteBankAccount(UUID id);

    void blockAccountOperation(UUID accountId, boolean blocked);

    String getAccountNumberById(UUID id);

    String getInformationFromId(UUID id);
}
