package ma.digency.gov.amc.repository.entity.publiclibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Activities extends AcmAbstractAuditEntity {
    protected static final String ACTIVITIES_REF_ACTIVITIES= "ref_activities";


    @Column(name = "ref_activities")
    private String refActivities;

    private Integer storyHoursNumber;

    private Integer readingClubsNumber;

    private Integer workshopsNumber;

    private Integer exhibitionsNumber;

    private Integer meetingsAndConferencesNumber;

    private Integer classVisitsNumber;

}
