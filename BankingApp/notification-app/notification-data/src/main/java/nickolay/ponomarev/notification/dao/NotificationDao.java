package nickolay.ponomarev.notification.dao;

import nickolay.ponomarev.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * NotificationDao.
 */
public interface NotificationDao extends JpaRepository<Notification, Long> {
}
