package nickolay.ponomarev.clientservice.mapper;

import nickolay.ponomarev.clientservice.dto.AddressClientDto;
import nickolay.ponomarev.clientservice.model.AddressClient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Mapper(uses = {ClientInfoMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressClientMapper {

    AddressClientDto toDto(AddressClient addressClient);

    AddressClient toSource(AddressClientDto addressClientDto, @MappingTarget AddressClient addressClient);
}
