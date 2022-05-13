package nickolay.ponomarev.cardissuer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 15.03.2022
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class ServiceUsageInformationDto {

    private UUID id;

    @ToString.Exclude
    private BankServiceDto bankService;

    private LocalDate startingUsageDate;

    private boolean valid;
}
