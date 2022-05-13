package nickolay.ponomarev.cardissuer.repository;

import nickolay.ponomarev.cardissuer.model.BankAccount;

import java.util.List;
import java.util.UUID;

/**
 * 13.02.2022
 * Репозиторий банковских аккаунтов
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface BankAccountRepository extends DeletableRepository<BankAccount> {
    List<BankAccount> findBankAccountsByBankClient(UUID id);
}
