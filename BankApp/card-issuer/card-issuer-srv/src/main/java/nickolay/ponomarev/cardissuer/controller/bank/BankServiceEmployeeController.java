package nickolay.ponomarev.cardissuer.controller.bank;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.service.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.bank.BankServiceEmployeeSource;
import nickolay.ponomarev.cardissuer.dto.BankServiceDto;
import nickolay.ponomarev.cardissuer.service.bank.BankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * Контроллер для банковских работников по управлению банковскими сервисами
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class BankServiceEmployeeController implements BankServiceEmployeeSource {

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

    @Override
    @Audit("Добавление нового банковского сервиса")
    public ResponseEntity<BankServiceDto> add(@RequestBody BankServiceDto bankServiceDto) {
        return ResponseEntity.ok(bankingService.createBankService(bankServiceDto));
    }

    @Override
    @Audit("Обновление существующего банковского сервиса")
    public ResponseEntity<BankServiceDto> update(@PathVariable("id") UUID id, @RequestParam("endDate") String endDate) {
        return ResponseEntity.ok(bankingService.updateBankService(id, endDate));
    }

    @Override
    @Audit("Удаление банковского сервиса")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        bankingService.deleteBankService(id);
        return ResponseEntity.ok().build();
    }
}
