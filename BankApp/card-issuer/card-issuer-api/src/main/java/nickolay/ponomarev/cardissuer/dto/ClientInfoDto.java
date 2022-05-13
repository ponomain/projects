package nickolay.ponomarev.cardissuer.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 10.02.2022
 * Dto для клиентов
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class ClientInfoDto {

    private UUID id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private LocalDate birthDate;

    private Boolean resident;

}
