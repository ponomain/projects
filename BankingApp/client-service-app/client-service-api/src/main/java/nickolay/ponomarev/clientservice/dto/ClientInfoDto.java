package nickolay.ponomarev.clientservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 11.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class ClientInfoDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthDate;
    private Boolean resident;

}
