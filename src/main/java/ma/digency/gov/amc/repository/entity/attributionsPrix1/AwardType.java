package ma.digency.gov.amc.repository.entity.attributionsPrix1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.Price;
import ma.digency.gov.amc.repository.entity.siel.BookingStand;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AwardType extends AcmAbstractAuditEntity {

    @Column(name = "ref_award_type")
    private String refAwardType;

    private String titleFr;

    private String titleAr;

    private String description;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = "ref_award_type", referencedColumnName = "ref_award_type", updatable = false)
    private List<AwardCategories> awardCategories;

}
