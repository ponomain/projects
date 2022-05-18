package nickolay.ponomarev.notification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 12.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class NotificationDto {
    private String theme;
    private String message;
}
