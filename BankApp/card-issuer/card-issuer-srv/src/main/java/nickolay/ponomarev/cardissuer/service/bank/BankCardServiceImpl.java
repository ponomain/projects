package nickolay.ponomarev.cardissuer.service.bank;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.dto.BankCardDto;
import nickolay.ponomarev.cardissuer.dto.BankServiceDto;
import nickolay.ponomarev.cardissuer.mapper.BankCardMapper;
import nickolay.ponomarev.cardissuer.mapper.BankServiceMapper;
import nickolay.ponomarev.cardissuer.mapper.CardTariffMapper;
import nickolay.ponomarev.cardissuer.model.*;
import nickolay.ponomarev.cardissuer.repository.BankAccountRepository;
import nickolay.ponomarev.cardissuer.repository.BankCardRepository;
import nickolay.ponomarev.cardissuer.repository.BankServiceRepository;
import nickolay.ponomarev.cardissuer.repository.CardTariffRepository;
import nickolay.ponomarev.cardissuer.validation.ValidPinCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 27.02.2022
 * Bank Card service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class BankCardServiceImpl implements BankCardService {

    private final BankCardRepository bankCardRepository;
    private final BankCardMapper bankCardMapper;
    private final BankServiceRepository bankServiceRepository;
    private final CardTariffRepository cardTariffRepository;


    @Override
    public BankCardDto getBankCardById(@NotNull UUID id) throws IllegalArgumentException{
        BankCard existingCard = bankCardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Card with id -" + id + "didn't found"));

        return bankCardMapper.toDto(existingCard);
    }

    @Override
    @Transactional
    public BankCardDto createBankCard(@NotNull BankCardDto bankCardDto) {
        BankCard newBankCard = new BankCard();
        bankCardMapper.toSource(bankCardDto, newBankCard);
        newBankCard = bankCardRepository.save(newBankCard);
        return bankCardMapper.toDto(newBankCard);
    }

    @Override
    @Transactional
    public BankCardDto updateBankCard(@NotNull UUID id, @NotNull Set<UUID> services) throws IllegalArgumentException {
        BankCard existingBankCard = bankCardRepository.findById(id).orElseThrow();
        Set<BankService> existingServices = new HashSet<>();
        for (UUID uuid : services) {
            BankService service = bankServiceRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException(
                    "Service with id -" + uuid + "didn't found"));
            existingServices.add(service);
        }
        existingBankCard.setBankServices(existingServices);
        existingBankCard = bankCardRepository.save(existingBankCard);
        return bankCardMapper.toDto(existingBankCard);
    }

    @Override
    @Transactional
    public void deleteBankCard(@NotNull UUID id) {
        bankCardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void temporaryBlockCard(@NotNull UUID id) throws IllegalArgumentException{
        BankCard existingBankCard = bankCardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Card with id -" + id + "didn't found"));
        existingBankCard.setBlocked(true);
        existingBankCard.setTemporaryBlockingDate(ZonedDateTime.now());
        bankCardRepository.save(existingBankCard);
    }

    @Override
    @Transactional
    public void permanentBlockCard(@NotNull UUID id) throws IllegalArgumentException{
        BankCard existingBankCard = bankCardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Card with id -" + id + "didn't found"));
        existingBankCard.setBlocked(true);
        existingBankCard.setPermanentBlockingDate(ZonedDateTime.now());
        bankCardRepository.save(existingBankCard);
    }

    @Override
    @Transactional
    public void unBlockCard(@NotNull UUID id) throws IllegalArgumentException{
        BankCard existingBankCard = bankCardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Card with id -" + id + "didn't found"));
        if (existingBankCard.getPermanentBlockingDate() != null) {
            throw new IllegalArgumentException("Card already permanently blocked");
        }
        existingBankCard.setBlocked(false);
        existingBankCard.setTemporaryBlockingDate(null);
        bankCardRepository.save(existingBankCard);
    }

    @Override
    @Transactional
    public BankCardDto addServiceToCard(@NotNull UUID cardId, @NotNull UUID serviceId) throws IllegalArgumentException{
        BankCard existingBankCard = bankCardRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException(
                "Card with id -" + cardId + "didn't found"));
        Set<BankService> services = existingBankCard.getBankServices();
        BankService existingService = bankServiceRepository.findById(serviceId).orElseThrow(() -> new IllegalArgumentException(
                "Service with id -" + serviceId + "didn't found"));
        services.add(existingService);
        existingBankCard.setBankServices(services);
        existingBankCard = bankCardRepository.save(existingBankCard);
        return bankCardMapper.toDto(existingBankCard);
    }

    @Override
    @Transactional
    public BankCardDto changeTariff(@NotNull UUID cardId, @NotNull UUID tariffId) throws IllegalArgumentException{
        BankCard existingBankCard = bankCardRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException(
                "Card with id -" + cardId + "didn't found"));
        CardTariff cardTariff = cardTariffRepository.findById(tariffId).orElseThrow(() -> new IllegalArgumentException(
                "Tariff with id -" + tariffId + "didn't found"));
        existingBankCard.setTariff(cardTariff);
        existingBankCard = bankCardRepository.save(existingBankCard);
        return bankCardMapper.toDto(existingBankCard);
    }

    @Override
    @Transactional
    public BankCardDto changePinCode(@NotNull UUID id, @ValidPinCode String pinCode) throws IllegalArgumentException{
        BankCard existingBankCard = bankCardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Card with id -" + id + "didn't found"));
        existingBankCard.setPinCode(pinCode);
        existingBankCard = bankCardRepository.save(existingBankCard);
        return bankCardMapper.toDto(existingBankCard);
    }

    @Override
    public String getCardNumberById(@NotNull UUID id) throws IllegalArgumentException{
        BankCard existingBankCard = bankCardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Card with id -" + id + "didn't found"));
        String number = existingBankCard.getNumber();
        return number.substring(number.length() - 4);
    }

    @Override
    public String getRequisitesFromId(@NotNull UUID id) throws IllegalArgumentException{
        BankCard existingBankCard = bankCardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Card with id -" + id + "didn't found"));
        return "Card number - " + existingBankCard.getNumber() + "\n" + "CCV - " + existingBankCard.getCcv();
    }

    @Override
    public List<UUID> getAllBankCardsFromAccount(@NotNull UUID id) throws IllegalArgumentException{
        List<BankCard> cards = bankCardRepository.findBankCardByAccountId(id);
        return cards.stream().map(Deletable::getId).collect(Collectors.toList());
    }
}
