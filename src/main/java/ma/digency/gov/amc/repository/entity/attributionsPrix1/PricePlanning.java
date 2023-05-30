package ma.digency.gov.amc.repository.entity.attributionsPrix1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.repository.entity.shared.RoleAccount;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PricePlanning extends AcmAbstractAuditEntity {

    @Column(name = "ref_price_planning")
    private String refPricePlaning;

    private Date startDate;

    private Date endDate;

    private Date announcementDate;

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_award", referencedColumnName = "ref_award")
    private Award award ;

}
