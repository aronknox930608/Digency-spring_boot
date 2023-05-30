package ma.digency.gov.amc.process;

import ma.digency.gov.amc.utils.enumeration.NotificationNature;

import java.util.HashMap;

public interface NotificationProcess {

    void sendNotification(HashMap<String, String> parameters, NotificationNature notificationNature);

    void sendEmail(HashMap<String, String> parameters, NotificationNature notificationNature);

    void sendNotificationAndMail(HashMap<String, String> parameters, NotificationNature notificationNature);
}
