package nickolay.ponomarev.notification.sourse.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nickolay.ponomarev.notification.dto.NotificationDto;
import nickolay.ponomarev.notification.service.NotificationService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * NotificationConsumerImpl.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@KafkaListener(topics = "${kafka.topic.to-notification}")
public class NotificationConsumerImpl implements NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaHandler
    @Override
    public void accept(NotificationDto dto) {
        log.info("Notify: {}", dto);
        notificationService.save(dto);
    }
}
