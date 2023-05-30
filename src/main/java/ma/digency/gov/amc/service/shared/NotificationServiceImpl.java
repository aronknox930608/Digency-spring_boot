package ma.digency.gov.amc.service.shared;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.NotificationRepository;
import ma.digency.gov.amc.repository.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {


    private final NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Notification ntf) {
        return notificationRepository.save(ntf);
    }

    @Override
    public List<Notification> getAllNotificationByRefObject(String refObject) {
        return notificationRepository.findNotificationsByRefObject(refObject);
    }
}
