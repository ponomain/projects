package nickolay.ponomarev.cardissuer.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 10.02.2022
 * Сущность для банковских аккаунтов
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "bank_account")
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends Deletable {

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "bank_client_id")
    private UUID bankClient;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_card_id")
    private List<BankCard> bankCards;

    @Column(name = "opening_date", nullable = false)
    private ZonedDateTime openingDate;

    @Column(name = "closing_date")
    private ZonedDateTime closingDate;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false)
    private String currency;

    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "bank_branch_id")
    private BankBranch bankBranch;

    @Column(name = "blocked", nullable = false)
    private boolean blocked;

    @Column(name = "blocking_date")
    private ZonedDateTime blockingDate;

}
