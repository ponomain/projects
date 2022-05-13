package nickolay.ponomarev.clientservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nickolay.ponomarev.notification.dto.NotificationDto;
import nickolay.ponomarev.notification.source.NotificationProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * 12.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducerImpl implements NotificationProducer{

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void send(NotificationDto notificationDto) {
        log.info("Send:" + notificationDto.toString());
        kafkaTemplate.send("to_notification", notificationDto);
    }
}
