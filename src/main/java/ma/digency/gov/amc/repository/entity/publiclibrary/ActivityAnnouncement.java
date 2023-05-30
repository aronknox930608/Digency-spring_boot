package ma.digency.gov.amc.repository.entity.publiclibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityAnnouncement extends AcmAbstractAuditEntity {
    protected static final String ACTIVITY_ANNOUNCEMENT_REF_ACTIVITY_ANNOUNCEMENT= "ref_activity_announcement";


    @Column(name = "ref_activity_announcement")
    private String refActivityAnnouncement;

    private String activityTitle;

    private String activityAdText;

    @Lob
    private byte[] picture;
}
