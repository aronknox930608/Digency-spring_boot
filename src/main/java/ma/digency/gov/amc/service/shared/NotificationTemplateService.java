package ma.digency.gov.amc.service.shared;

import ma.digency.gov.amc.repository.entity.shared.TemplateNotification;
import ma.digency.gov.amc.utils.enumeration.NotificationNature;
import ma.digency.gov.amc.utils.enumeration.NotificationType;

public interface NotificationTemplateService {

    TemplateNotification findNotificationByNature(NotificationNature nature);

    TemplateNotification findNotificationTemplate(String refNotification);

    TemplateNotification saveNotificationTemplate(TemplateNotification templateNotification);

    void updateNotificationTemplate(TemplateNotification templateNotification);

    TemplateNotification findTemplateNotificationByNatureAndType(NotificationType type, NotificationNature nature);
}
