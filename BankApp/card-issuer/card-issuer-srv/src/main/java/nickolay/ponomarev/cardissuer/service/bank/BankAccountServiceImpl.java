package nickolay.ponomarev.cardissuer.service.bank;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.dto.BankAccountDto;
import nickolay.ponomarev.cardissuer.mapper.BankAccountMapper;
import nickolay.ponomarev.cardissuer.model.BankAccount;
import nickolay.ponomarev.cardissuer.model.Deletable;
import nickolay.ponomarev.cardissuer.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 27.02.2022
 * Bank account service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public BankAccountDto getBankAccountById(@NotNull UUID id) throws IllegalArgumentException {
        BankAccount existingBankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Account with id -" + id + "didn't found"));
        return bankAccountMapper.toDto(existingBankAccount);
    }


    @Override
    public List<UUID> getAllBankAccountsFromClient(@NotNull UUID id) {
        List<BankAccount> existingBankAccounts = bankAccountRepository.findBankAccountsByBankClient(id);
        return existingBankAccounts.stream().map(Deletable::getId).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteBankAccount(@NonNull UUID id) {
        bankAccountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void blockAccountOperation(@NonNull UUID accountId, boolean blocked) throws IllegalArgumentException{
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException(
                "Account with id -" + accountId + "didn't found"));
        bankAccount.setBlocked(blocked);
        if (blocked) {
            bankAccount.setBlockingDate(ZonedDateTime.now());
        } else {
            bankAccount.setBlockingDate(null);
        }
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public String getAccountNumberById(@NotNull UUID id) throws IllegalArgumentException{
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Account with id -" + id + "didn't found"));
        String number = bankAccount.getNumber();
        return number.substring(number.length() - 4);
    }

    @Override
    public String getInformationFromId(@NotNull UUID id) throws IllegalArgumentException{
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Account with id -" + id + "didn't found"));
        return "Number - " + bankAccount.getNumber() + "\n" + "Balance - " + bankAccount.getBalance();
    }
}
