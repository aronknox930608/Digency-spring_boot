package ma.digency.gov.amc.repository.entity.attributionsprix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.BirthData;
import ma.digency.gov.amc.utils.enumeration.IdentityType;
import ma.digency.gov.amc.utils.enumeration.MaritalStatus;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CandidateHonoraryAward extends AcmAbstractAuditEntity {

    @Column(name = "ref_candidate")
    private String refCandidate;

    private String cin;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name_ar")
    private String firstNameAR;

    @Column(name = "last_name_ar")
    private String lastNameAR;

    private String gender;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "dependent_children")
    private Integer dependentChildren;

    @Embedded
    private BirthData birthdata;

    @Column(name = "rib_number")
    private String ribNumber;

    @Column(name = "domain_name")
    private String domainName;

    private String province;

    private String postalCode;

    private String city;

    private String country;

    private String address;

     private String region;

    @Lob
    private byte[] picture;

    @Lob
    private byte[] cv;



}
