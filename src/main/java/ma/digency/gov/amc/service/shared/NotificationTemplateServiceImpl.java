package ma.digency.gov.amc.service.shared;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.NotificationTemplateRepository;
import ma.digency.gov.amc.repository.entity.shared.TemplateNotification;
import ma.digency.gov.amc.utils.enumeration.NotificationNature;
import ma.digency.gov.amc.utils.enumeration.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

    private final NotificationTemplateRepository notificationTemplateRepository;

    @Override
    public TemplateNotification findNotificationByNature(NotificationNature nature) {
        return notificationTemplateRepository.findNotificationTemplateByNature(nature.name());
    }

    @Override
    public TemplateNotification findNotificationTemplate(String refNotification) {
        return notificationTemplateRepository.findTemplateNotificationByRefTemplateNotification(refNotification);
    }

    @Override
    public TemplateNotification saveNotificationTemplate(TemplateNotification templateNotification) {
        return notificationTemplateRepository.save(templateNotification);
    }

    @Override
    public void updateNotificationTemplate(TemplateNotification templateNotification) {
        notificationTemplateRepository.save(templateNotification);
    }

    @Override
    public TemplateNotification findTemplateNotificationByNatureAndType(NotificationType type, NotificationNature nature) {
        return notificationTemplateRepository.findTemplateNotificationByNatureAndType(nature.name(), type.name());
    }
}
