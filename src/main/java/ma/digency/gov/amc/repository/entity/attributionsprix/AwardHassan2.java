package ma.digency.gov.amc.repository.entity.attributionsprix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.Price;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class AwardHassan2 extends AcmAbstractAuditEntity{

    @Column(name = "ref_award_hassan2")
    private String refAwardHassan2;

    private String type; //hassan 2, .... table award type

    private float amount;

    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name = "ref_price", referencedColumnName = "ref_price")
    private Price price;
}
