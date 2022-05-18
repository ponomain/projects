package nickolay.ponomarev.cardissuer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 10.02.2022
 * Сущность для тарифов карт
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "card_tariff")
@AllArgsConstructor
@NoArgsConstructor
public class CardTariff extends Deletable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "subscription_amount", nullable = false)
    private BigDecimal subscriptionAmount;

    @Column(name = "subscription_periodicity", nullable = false)
    @Enumerated(EnumType.STRING)
    private Periodicity subscriptionPeriodicity;

    @Column(name = "cash_withdrawal_limit_bank")
    private BigDecimal cashWithdrawalLimitBank;

    @Column(name = "cash_withdrawal_limit_atm")
    private BigDecimal cashWithdrawalLimitAtm;

    @Column(name = "transfer_limit")
    private BigDecimal transferLimit;

    @Column(name = "currency", nullable = false)
    private String currency;
}
