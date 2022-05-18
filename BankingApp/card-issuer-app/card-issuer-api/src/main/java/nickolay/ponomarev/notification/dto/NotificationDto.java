package nickolay.ponomarev.notification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 11.04.2022
 * card-issuer
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
