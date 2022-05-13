package nickolay.ponomarev.cardissuer.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 10.02.2022
 * Dto для тарифов карт
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class CardTariffDto {

    private UUID id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal subscriptionAmount;

    private String subscriptionPeriodicity;

    private BigDecimal cashWithdrawalLimitBank;

    private BigDecimal cashWithdrawalLimitAtm;

    private BigDecimal transferLimit;

    private String currency;
}
