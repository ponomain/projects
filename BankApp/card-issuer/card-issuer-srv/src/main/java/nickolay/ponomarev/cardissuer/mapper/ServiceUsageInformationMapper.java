package nickolay.ponomarev.cardissuer.mapper;

import nickolay.ponomarev.cardissuer.dto.ServiceUsageInformationDto;
import nickolay.ponomarev.cardissuer.model.ServiceUsageInformation;
import org.mapstruct.Mapper;

/**
 * 16.03.2022
 * Service usage information mapper
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Mapper
public interface ServiceUsageInformationMapper extends CardIssuerMapper<ServiceUsageInformation, ServiceUsageInformationDto> {

}
