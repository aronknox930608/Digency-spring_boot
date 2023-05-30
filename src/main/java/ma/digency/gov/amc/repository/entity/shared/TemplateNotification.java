package ma.digency.gov.amc.repository.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateNotification extends AcmAbstractAuditEntity {

    @Column(name = "ref_template_notification")
    private String refTemplateNotification;

    private String type;  //MAIL /SMS

    private String nature; // validation / cle TOP ....

    private String templateBody;

    private String templateObject;

}
