package ma.digency.gov.amc.repository.entity.shared;

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
public class CommissionMember extends AcmAbstractAuditEntity {

    @Column(name = "ref_commission")
    private String refCommission;

    @Column(name = "ref_commission_account")
    private String refCommissionAccount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("ref_commission_account")
    @JoinColumn(name = "ref_commission_account", insertable = false, unique = false,
            referencedColumnName = "ref_commission_account")
    private CommissionAccount commissionAccount;
}
