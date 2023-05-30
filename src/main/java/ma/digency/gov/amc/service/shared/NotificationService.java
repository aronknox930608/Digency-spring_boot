package ma.digency.gov.amc.service.shared;

import ma.digency.gov.amc.repository.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification createNotification(Notification ntf);

    List<Notification> getAllNotificationByRefObject(String refObject);
}
