package nickolay.ponomarev.cardissuer.service;

import nickolay.ponomarev.cardissuer.dto.ServiceUsageInformationDto;

import java.util.UUID;

/**
 * 16.03.2022
 * Сервис по управлению информацией пользования продуктом клиента
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface ServicingUsageInformation {

    ServiceUsageInformationDto getInformationFromClient(UUID id);

    ServiceUsageInformationDto getInformationFromService(UUID id);

    ServiceUsageInformationDto setValidity(UUID id, boolean valid);

    void deleteUsageInformation(UUID id);
}
