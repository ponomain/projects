package nickolay.ponomarev.cardissuer.controller;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.audit.Audit;
import nickolay.ponomarev.cardissuer.sources.BankBranchSource;
import nickolay.ponomarev.cardissuer.dto.BankBranchDto;
import nickolay.ponomarev.cardissuer.service.bank.BankBranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 24.02.2022
 * Контроллер для получения информации по банковским отделениям
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class BankBranchController implements BankBranchSource {

    private final BankBranchService bankBranchService;

    @Override
    @Audit("Получение отделения банка")
    public ResponseEntity<BankBranchDto> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bankBranchService.getBankBranchById(id));
    }

    @Override
    @Audit("Получение всех отделений банка")
    public ResponseEntity<List<UUID>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(bankBranchService.getAllBankBranches(page, size));
    }
}
