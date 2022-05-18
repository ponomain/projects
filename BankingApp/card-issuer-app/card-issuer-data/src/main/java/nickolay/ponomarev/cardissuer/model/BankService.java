package nickolay.ponomarev.cardissuer.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 10.02.2022
 * Сущность для банковских сервисов
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "bank_service")
@NoArgsConstructor
@AllArgsConstructor
public class BankService  extends Deletable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "subscription_amount", nullable = false)
    private BigDecimal subscriptionAmount;

    @Column(name = "subscription_periodicity", nullable = false)
    @Enumerated(EnumType.STRING)
    private Periodicity subscriptionPeriodicity;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "valid", nullable = false)
    private boolean valid;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ServiceUsageInformation serviceUsageInformation;

}
