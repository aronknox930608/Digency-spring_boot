package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateNotificationDto {

    private String refTemplateNotification;

    private String type;  //MAIL /SMS

    private String nature; // validation / cle TOP ....

    private String templateBody;

    private String templateObject;
}
