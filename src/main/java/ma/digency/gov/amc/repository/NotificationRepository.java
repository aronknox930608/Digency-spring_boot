package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationRepository extends GenericRepository<Notification, Long> {

    List<Notification> findNotificationsByRefObject(String refObject);
}
