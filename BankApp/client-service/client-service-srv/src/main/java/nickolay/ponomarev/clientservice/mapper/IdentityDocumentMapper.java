package nickolay.ponomarev.clientservice.mapper;

import nickolay.ponomarev.clientservice.dto.IdentityDocumentDto;
import nickolay.ponomarev.clientservice.model.IdentityDocument;
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
public interface IdentityDocumentMapper {

    IdentityDocumentDto toDto(IdentityDocument identityDocument);

    IdentityDocument toSource(IdentityDocumentDto identityDocumentDto, @MappingTarget IdentityDocument identityDocument);
}
