package nickolay.ponomarev.notification.source;

import nickolay.ponomarev.notification.dto.NotificationDto;

/**
 * 12.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface NotificationProducer {

    void send(NotificationDto notificationDto);
}
