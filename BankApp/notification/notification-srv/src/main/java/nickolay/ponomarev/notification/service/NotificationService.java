package nickolay.ponomarev.notification.service;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.notification.dao.NotificationDao;
import nickolay.ponomarev.notification.dto.NotificationDto;
import nickolay.ponomarev.notification.entity.Notification;
import nickolay.ponomarev.notification.mapper.NotificationMapper;
import org.springframework.stereotype.Service;

/**
 * NotificationService.
 */
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationDao notificationDao;
    private final NotificationMapper notificationMapper;

    public void save(NotificationDto dto) {
        final Notification notification = notificationMapper.toEntity(dto);
        notificationDao.save(notification);
    }
}
