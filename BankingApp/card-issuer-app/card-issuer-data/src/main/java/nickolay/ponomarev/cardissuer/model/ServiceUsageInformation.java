package nickolay.ponomarev.cardissuer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 15.03.2022
 * Сущность для отображения информации по использованию сервисом пользователем
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "service_usage_information")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUsageInformation extends Deletable{

    @Column(name = "bank_client_id")
    private UUID bankClient;

    @OneToOne
    @JoinColumn(name = "bank_service_id")
    private BankService bankService;

    @Column(name = "starting_usage_date")
    private LocalDate startingUsageDate;
    
    private boolean valid;
}
