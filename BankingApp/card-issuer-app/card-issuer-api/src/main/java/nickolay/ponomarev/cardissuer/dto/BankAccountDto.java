package nickolay.ponomarev.cardissuer.dto;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 10.02.2022
 * Dto для банковских аккаунтов
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class BankAccountDto {

    private UUID id;

    private UUID bankClient;

    @ToString.Exclude
    private List<BankCardDto> bankCards;

    private ZonedDateTime openingDate;

    private ZonedDateTime closingDate;

    private String currency;

    @ToString.Exclude
    private BankBranchDto bankBranch;

    private boolean blocked;

    private ZonedDateTime blockingDate;
}
