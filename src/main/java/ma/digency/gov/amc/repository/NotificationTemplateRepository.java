package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.shared.TemplateNotification;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTemplateRepository extends GenericRepository<TemplateNotification, Long> {

    TemplateNotification findNotificationTemplateByNature(String nature);

    TemplateNotification findTemplateNotificationByRefTemplateNotification(String refObject);

    TemplateNotification findTemplateNotificationByNatureAndType(String nature, String Type);

}
