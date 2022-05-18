package nickolay.ponomarev.notification.mapper;

import nickolay.ponomarev.notification.dto.NotificationDto;
import nickolay.ponomarev.notification.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * NotificationMapper.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {

    Notification toEntity(NotificationDto dto);

    NotificationDto toDto(Notification entity);
}
