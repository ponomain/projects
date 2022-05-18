package nickolay.ponomarev.cardissuer.service.bank;

import nickolay.ponomarev.cardissuer.dto.BankCardDto;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 27.02.2022
 * Bank card service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface BankCardService {

    BankCardDto getBankCardById(UUID id);

    BankCardDto createBankCard(BankCardDto bankCardDto);

    BankCardDto updateBankCard(UUID id, Set<UUID> services);

    List<UUID> getAllBankCardsFromAccount(UUID id);

    void deleteBankCard(UUID id);

    void temporaryBlockCard(UUID id);

    void permanentBlockCard(UUID id);

    void unBlockCard(UUID id);

    BankCardDto addServiceToCard(UUID cardId, UUID serviceId);

    BankCardDto changeTariff(UUID cardId, UUID tariffId);

    BankCardDto changePinCode(UUID id, String pinCode);

    String getCardNumberById(UUID id);

    String getRequisitesFromId(UUID id);
}


