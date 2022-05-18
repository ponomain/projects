package nickolay.ponomarev.cardissuer.dto;

import lombok.*;

import java.util.UUID;

/**
 * 10.02.2022
 * Dto для отделений банков
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class BankBranchDto {

    private UUID id;

    private String departmentNumber;

    @ToString.Exclude
    private BankBranchDto headBranch;

    private String name;

    private AddressBranchDto address;
}
