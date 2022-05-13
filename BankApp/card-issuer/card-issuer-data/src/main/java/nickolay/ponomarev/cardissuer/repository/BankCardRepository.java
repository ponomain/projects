package nickolay.ponomarev.cardissuer.repository;

import nickolay.ponomarev.cardissuer.model.BankCard;

import java.util.List;
import java.util.UUID;


/**
 * 13.02.2022
 * репозиторий банковских карт
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface BankCardRepository extends DeletableRepository<BankCard> {
    List<BankCard> findBankCardByAccountId(UUID id);
}
