package nickolay.ponomarev.cardissuer.service.bank;

import nickolay.ponomarev.cardissuer.dto.*;

import java.util.List;
import java.util.UUID;

/**
 * 14.02.2022
 * Сервис для работы с банками
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface BankingService {

    BankServiceDto getBankServiceById(UUID id);

    List<UUID> getAllBankServices(int page, int size);

    BankServiceDto createBankService(BankServiceDto bankServiceDto);

    BankServiceDto updateBankService(UUID id, String endDate);

    void deleteBankService(UUID id);

}


