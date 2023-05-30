package ma.digency.gov.amc.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.entity.Notification;
import ma.digency.gov.amc.repository.entity.shared.TemplateNotification;
import ma.digency.gov.amc.service.shared.NotificationService;
import ma.digency.gov.amc.service.shared.NotificationTemplateService;
import ma.digency.gov.amc.utils.LoadResourceFile;
import ma.digency.gov.amc.utils.enumeration.NotificationNature;
import ma.digency.gov.amc.utils.enumeration.NotificationStatus;
import ma.digency.gov.amc.utils.enumeration.NotificationType;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import ma.digency.gov.amc.utils.service.SendMailSMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationProcessImpl implements NotificationProcess {

    private final NotificationTemplateService notificationTemplateService;

    private final LoadResourceFile loadResourceFile;

    private final SendMailSMSService sendMailSMSService;

    private final NotificationService notificationService;

    private final ReferenceSequenceService referenceSequenceService;

    private ObjectMapper mapper;

    @Override
    public void sendNotification(HashMap<String, String> parameters, NotificationNature notificationNature) {
        TemplateNotification tmpNtf = notificationTemplateService
                .findTemplateNotificationByNatureAndType(NotificationType.APP, notificationNature);


        Notification ntf = new Notification();
        ntf.setAttemptNb(1);
        ntf.setRefNotification(referenceSequenceService.generateRefNotification());
        ntf.setTemplate(tmpNtf);
        ntf.setRefObject(parameters.get("refObject"));
        ntf.setStatus(NotificationStatus.SEND);
        ntf.setContent(checkParametersTemplate(parameters, tmpNtf.getTemplateBody()));

        notificationService.createNotification(ntf);


    }

    @Override
    public void sendEmail(HashMap<String, String> parameters, NotificationNature notificationNature) {
        TemplateNotification ntf = notificationTemplateService
                .findTemplateNotificationByNatureAndType(NotificationType.MAIL, notificationNature);

        //load body mail
        String bodyTemplate = loadResourceFile.loadFile(ntf.getTemplateBody());
        //get destination
        String to = parameters.get("to");
        //check and update content body mail
        String content = checkParametersTemplate(parameters, bodyTemplate);
        //send mail
        sendMailSMSService.sendMail(to, ntf.getTemplateObject(), content);

    }

    @Override
    public void sendNotificationAndMail(HashMap<String, String> parameters, NotificationNature notificationNature) {
        sendNotification(parameters, notificationNature);
        sendEmail(parameters, notificationNature);
    }

    protected String checkParametersTemplate(HashMap<String, String> parameters, String content) {
        String newContent = content;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (entry.getKey().startsWith("#") && content.lastIndexOf(entry.getKey()) != -1) {
                newContent = newContent.replace(entry.getKey(), entry.getValue());
            }
        }
        return newContent;
    }

}
