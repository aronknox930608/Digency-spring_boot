package ma.digency.gov.amc.repository.entity;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.shared.TemplateNotification;
import ma.digency.gov.amc.utils.enumeration.NotificationStatus;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Notification extends AcmAbstractAuditEntity {

    @Column(name = "ref_notification")
    private String refNotification;

    private String refObject;

    private String content;

    private Integer attemptNb;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @ManyToOne
    @JoinColumn(name = "ref_template_notification", referencedColumnName = "ref_template_notification")
    private TemplateNotification template;

}
