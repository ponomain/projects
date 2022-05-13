package nickolay.ponomarev.cardissuer.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 10.02.2022
 * Dto для банковских сервисов
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class BankServiceDto {

    private UUID id;

    private String name;

    private BigDecimal subscriptionAmount;

    private String currency;

    private String subscriptionPeriodicity;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean valid;

    @ToString.Exclude
    private ServiceUsageInformationDto serviceUsageInformation;
}
