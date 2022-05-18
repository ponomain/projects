package nickolay.ponomarev.clientservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * 11.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Entity
@Getter
@Setter
@Table(name = "client_info")
public class ClientInfo extends Deletable {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "inn")
    private String inn;

    @Column(name = "resident")
    private Boolean resident;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "identity_document_id")
    private List<IdentityDocument> identityDocument;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_client_id")
    private AddressClient addressClient;
}
