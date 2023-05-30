package ma.digency.gov.amc.repository.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractEntity;
import ma.digency.gov.amc.utils.enumeration.CommissionTypeEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Commission extends AcmAbstractEntity {

    private static final String COMMISSION_REF_COMMISSION = "ref_commission";

    @Column(name = "ref_commission")
    private String refCommission;

    @Enumerated(EnumType.STRING)
    private CommissionTypeEnum commissionType;

    private LocalDate startedDate;

    private LocalDate endDate;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = COMMISSION_REF_COMMISSION, referencedColumnName = COMMISSION_REF_COMMISSION)
    private List<Planning> plannings;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = COMMISSION_REF_COMMISSION, referencedColumnName = COMMISSION_REF_COMMISSION)
    private List<CommissionMember> members;


}
