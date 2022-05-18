package nickolay.ponomarev.cardissuer.service.bank;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.dto.*;
import nickolay.ponomarev.cardissuer.mapper.BankServiceMapper;
import nickolay.ponomarev.cardissuer.model.*;
import nickolay.ponomarev.cardissuer.repository.BankServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 14.02.2022
 * Сервис для работы с банками
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class BankingServiceImpl implements BankingService {

    private final BankServiceRepository bankServiceRepository;
    private final BankServiceMapper bankServiceMapper;


    @Override
    public BankServiceDto getBankServiceById(@NotNull UUID id) throws IllegalArgumentException{
        BankService existingService = bankServiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Service with id -" + id + "didn't found"));
        return bankServiceMapper.toDto(existingService);
    }

    @Override
    public List<UUID> getAllBankServices(int page, int size) {
        PageRequest request = PageRequest.of(page, size, Sort.by("id"));
        Page<BankService> result = bankServiceRepository.findAll(request);
        List<BankService> services = result.getContent();
        return services.stream().map(Deletable::getId).collect(Collectors.toList());
    }

    @Override
    public BankServiceDto createBankService(@NotNull BankServiceDto bankServiceDto) {
        BankService newBankService = new BankService();
        bankServiceMapper.toSource(bankServiceDto, newBankService);
        newBankService = bankServiceRepository.save(newBankService);
        return bankServiceMapper.toDto(newBankService);
    }

    @Override
    @Transactional
    public BankServiceDto updateBankService(@NotNull UUID id,@NotNull String endDate) throws IllegalArgumentException{
        BankService existingBankService = bankServiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Service with id -" + id + "didn't found"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(endDate, formatter);
        existingBankService.setEndDate(date);
        existingBankService = bankServiceRepository.save(existingBankService);
        return bankServiceMapper.toDto(existingBankService);
    }

    @Override
    @Transactional
    public void deleteBankService(@NotNull UUID id) {
        bankServiceRepository.deleteById(id);
    }

}
