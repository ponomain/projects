package nickolay.ponomarev.clientservice.dto;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public class IdentityDocumentDto {

    private UUID id;

    private LocalDate dateOfIssue;

    private String organization;

    private String organizationCode;

    private LocalDate validityDate;
}
