package nickolay.ponomarev.notification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * NotificationDto.
 */
@Getter
@Setter
@ToString
public class NotificationDto {
    private String theme;
    private String message;
}
