package nickolay.ponomarev.cardissuer.model;

import lombok.*;

import javax.persistence.*;

/**
 * 10.02.2022
 * Сущность для отделений банка
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "bank_branch")
@NoArgsConstructor
@AllArgsConstructor
public class BankBranch extends Deletable {

    @Column(name = "department_number", nullable = false)
    private String departmentNumber;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_branch_id")
    private BankBranch headBranch;

    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @OneToOne(cascade = {CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumn(name = "address_branch_id")
    private AddressBranch addressBranch;

}
