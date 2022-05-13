package nickolay.ponomarev.clientservice.mapper;

import nickolay.ponomarev.clientservice.dto.ClientInfoDto;
import nickolay.ponomarev.clientservice.model.ClientInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * 12.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Mapper(uses = {AddressClientMapper.class, IdentityDocumentMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface ClientInfoMapper {

    ClientInfo toEntity(ClientInfoDto clientInfoDto, @MappingTarget ClientInfo clientInfo);

    ClientInfoDto toDto(ClientInfo clientInfo);
}
