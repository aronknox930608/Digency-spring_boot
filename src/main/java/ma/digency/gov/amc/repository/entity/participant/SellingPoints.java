package ma.digency.gov.amc.repository.entity.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SellingPoints extends AcmAbstractAuditEntity {
    protected static final String SELLING_POINTS_REF_SELLING_POINTS= "ref_selling_points";


    @Column(name = "ref_selling_points")
    private String refSellingPoints;

    private String Name;

    private String description;


}
