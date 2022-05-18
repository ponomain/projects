package nickolay.ponomarev.clientservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "identity_document")
public class IdentityDocument extends Deletable {

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_info_id")
    private ClientInfo clientInfo;

    @Column(name = "serial", nullable = false)
    private String serial;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "date_Of_Issue", nullable = false)
    private LocalDate dateOfIssue;

    @Column(name = "organization", nullable = false)
    private String organization;

    @Column(name = "organization_code", nullable = false)
    private String organizationCode;

    @Column(name = "validity_date", nullable = false)
    private LocalDate validityDate;
}
