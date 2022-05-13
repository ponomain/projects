package nickolay.ponomarev.cardissuer.controller.client;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.service.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.client.BankServiceClientSource;
import nickolay.ponomarev.cardissuer.dto.BankServiceDto;
import nickolay.ponomarev.cardissuer.service.bank.BankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * Контроллер для пользователей по получению информации банковских сервисов
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class BankServiceClientController implements BankServiceClientSource {

    private final BankingService bankingService;

    @Override
    @Audit("Получение банковского сервиса")
    public ResponseEntity<BankServiceDto> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankingService.getBankServiceById(id));
    }

    @Override
    @Audit("Получение всех банковских сервисов")
    public ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(bankingService.getAllBankServices(page, size));
    }
}
