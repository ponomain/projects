package nickolay.ponomarev.cardissuer.controller.bank;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.dto.ServiceUsageInformationDto;
import nickolay.ponomarev.cardissuer.service.ServicingUsageInformation;
import nickolay.ponomarev.cardissuer.service.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.bank.ServiceUsageInformationEmployeeSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 16.03.2022
 * Контроллер для банковского работника по получению и изменению информации по пользованию сервисом пользователем
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class ServiceUsageInformationEmployeeController implements ServiceUsageInformationEmployeeSource {

    private final ServicingUsageInformation service;

    @Override
    @Audit("Получение информации по использованию сервиса от пользователя ")
    public ResponseEntity<ServiceUsageInformationDto> getFromClient(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getInformationFromClient(id));
    }

    @Override
    @Audit("Получение информации по использованию сервиса пользователем")
    public ResponseEntity<ServiceUsageInformationDto> getFromService(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getInformationFromService(id));
    }

    @Override
    @Audit("Установка валидности у информации по использованию сервиса пользователем")
    public ResponseEntity<ServiceUsageInformationDto> setValidity(@PathVariable("id") UUID id,@RequestParam Boolean valid) {
        return ResponseEntity.ok(service.setValidity(id, valid));
    }

    @Override
    @Audit("Удаление информации по использованию сервиса пользователем")
    public ResponseEntity<Void> delete(@PathVariable("id")UUID id) {
        service.deleteUsageInformation(id);
        return ResponseEntity.ok().build();
    }
}
