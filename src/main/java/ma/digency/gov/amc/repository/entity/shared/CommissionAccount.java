package ma.digency.gov.amc.repository.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommissionAccount extends AcmAbstractAuditEntity {

    @Column(name = "ref_commission_account")
    private String refCommissionAccount;

    private String cin;

    private String firstName;

    private String lastName;

    private String gender;

    @OneToOne
    @JoinColumn(name = "ref_account", referencedColumnName = "ref_account")
    private Account account;
}
