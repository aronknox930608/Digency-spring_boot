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
public class CooperativeAccountBook extends AcmAbstractAuditEntity {
    @Column(name = "ref_cooperative_account_book")
    private String refCooperativeAccountBook;

    private String cooperativeName;

    private String phoneNumber;

    private String faxNumber;

    @Embedded
    private Address address;

    private String firstName;

    private String lastName;


    @Enumerated(EnumType.STRING)
    private StatusEnum status;


    private String cooperativeMember;

    private String responsibleName;

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
