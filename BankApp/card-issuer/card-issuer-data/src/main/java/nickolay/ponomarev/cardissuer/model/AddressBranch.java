package nickolay.ponomarev.cardissuer.model;

import lombok.*;

import javax.persistence.*;

/**
 * 10.02.2022
 * Сущность для адреса
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address_branch")
public class AddressBranch extends Deletable {

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house_num", nullable = false)
    private String houseNum;

    @Column(name = "litter")
    private String litter;

    @Column(name = "building")
    private String building;

    @Column(name = "room")
    private String room;

    @Column(name = "apartment")
    private String apartment;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_branch_id")
    private BankBranch bankBranch;
}
