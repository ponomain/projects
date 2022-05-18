package nickolay.ponomarev.notification.sourse.kafka;

import nickolay.ponomarev.notification.dto.NotificationDto;

/**
 * NotificationConsumer.
 */
public interface NotificationConsumer {

    void accept(NotificationDto dto);
}
