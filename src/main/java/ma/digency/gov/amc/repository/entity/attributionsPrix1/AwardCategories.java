package ma.digency.gov.amc.repository.entity.attributionsPrix1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AwardCategories extends AcmAbstractAuditEntity {

    @Column(name = "ref_award_category")
    private String refAwardCategory;

    private String titleFr;

    private String titleAr;

    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name = "ref_award_type", referencedColumnName = "ref_award_type")
    private AwardType awardType ;

}
