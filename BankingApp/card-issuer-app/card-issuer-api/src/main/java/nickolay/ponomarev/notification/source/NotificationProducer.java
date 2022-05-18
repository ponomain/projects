package nickolay.ponomarev.notification.source;

import nickolay.ponomarev.notification.dto.NotificationDto;

/**
 * 11.04.2022
 * card-issuer
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface NotificationProducer {

    void send(NotificationDto notificationDto);
}
