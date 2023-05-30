package ma.digency.gov.amc.repository.entity.proposalproject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.Address;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyAccount extends AcmAbstractAuditEntity {

    @Column(name = "ref_company_account")
    private String refCompanyAccount;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "juridic_forme")
    private String juridicForm;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "responsible_name")
    private String responsibleName;

    @Column(name = "register_number")
    private String registerNumber;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_account", referencedColumnName = "ref_account")
    private Account account;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "ref_general_information", referencedColumnName = "ref_general_information")
    private GeneralInformation generalInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ref_artistic_profession", referencedColumnName = "ref_artistic_profession")
    private ArtisticProfession artisticProfession;

}
