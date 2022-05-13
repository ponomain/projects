package nickolay.ponomarev.cardissuer.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 10.02.2022
 * Dto для документов
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class IdentityDocumentDto {

    private UUID id;

    private LocalDate dateOfIssue;

    private String organization;

    private String organizationCode;

    private LocalDate validityDate;
}
