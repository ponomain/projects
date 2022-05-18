package nickolay.ponomarev.cardissuer.dto;

import lombok.*;

import java.util.UUID;

/**
 * 10.02.2022
 * Dto для адресов
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class AddressBranchDto {

    private UUID id;

    private String country;

    private String region;

    private String district;

    private String location;

    private String street;

    private String houseNum;

    private String litter;

    private String building;

    private String room;

    private String apartment;
}
