package nickolay.ponomarev.cardissuer.service;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.dto.ServiceUsageInformationDto;
import nickolay.ponomarev.cardissuer.mapper.ServiceUsageInformationMapper;
import nickolay.ponomarev.cardissuer.model.BankService;
import nickolay.ponomarev.cardissuer.model.ServiceUsageInformation;
import nickolay.ponomarev.cardissuer.repository.BankServiceRepository;
import nickolay.ponomarev.cardissuer.repository.ServiceUsageInformationRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * 16.03.2022
 * Сервис по управлению информацией пользования продуктом клиента
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class ServicingUsageInformationImpl implements ServicingUsageInformation {

    private final ServiceUsageInformationRepository informationRepository;
    private final BankServiceRepository bankServiceRepository;
    private final ServiceUsageInformationMapper mapper;

    @Override
    public ServiceUsageInformationDto getInformationFromClient(@NotNull UUID id){
        ServiceUsageInformation information = informationRepository.findServiceUsageInformationByBankClient(id);
        return mapper.toDto(information);
    }

    @Override
    public ServiceUsageInformationDto getInformationFromService(@NotNull UUID id) throws IllegalArgumentException{
        BankService bankService = bankServiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Service with id -" + id + "didn't found"));
        ServiceUsageInformation information = bankService.getServiceUsageInformation();
        return mapper.toDto(information);
    }

    @Override
    public ServiceUsageInformationDto setValidity(@NotNull UUID id, @NotNull boolean valid) throws IllegalArgumentException{
        ServiceUsageInformation information = informationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Information with id -" + id + "didn't found"));
        information.setValid(valid);
        information = informationRepository.save(information);
        return mapper.toDto(information);
    }

    @Override
    public void deleteUsageInformation(@NotNull UUID id) {
        informationRepository.deleteById(id);
    }
}
