package nickolay.ponomarev.cardissuer.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * 10.02.2022
 * Сущность для банковских карт
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "bank_card")
@NoArgsConstructor
@AllArgsConstructor
public class BankCard extends Deletable {

    private String number;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private BankAccount account;

    @Column(name = "card_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "date_of_issue", nullable = false)
    private ZonedDateTime dateOfIssue;

    @Column(name = "validity", nullable = false)
    private ZonedDateTime validity;

    @Column(name = "pay_system", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaySystem paySystem;

    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "tariff_id")
    private CardTariff tariff;

    @Column(name = "ccv", nullable = false)
    private String ccv;

    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumn(name = "bank_service_id")
    private Set<BankService> bankServices;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "temporary_blocking_date")
    private ZonedDateTime temporaryBlockingDate;

    @Column(name = "permanent_blocking_date")
    private ZonedDateTime permanentBlockingDate;

}
