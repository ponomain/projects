package nickolay.ponomarev.cardissuer.dto;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 10.02.2022
 * Dto для банковских карт
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class BankCardDto {

    private UUID id;

    @ToString.Exclude
    private BankAccountDto account;

    private String cardType;

    private ZonedDateTime dateOfIssue;

    private ZonedDateTime validity;

    private String paySystem;

    @ToString.Exclude
    private CardTariffDto tariff;

    @ToString.Exclude
    private List<BankServiceDto> bankServices;

    private boolean blocked;

    private ZonedDateTime temporaryBlockingDate;

    private ZonedDateTime permanentBlockingDate;
}
