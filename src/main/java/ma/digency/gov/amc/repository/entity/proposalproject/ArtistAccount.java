package ma.digency.gov.amc.repository.entity.proposalproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.Address;
import ma.digency.gov.amc.repository.entity.BirthData;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.IdentityType;
import ma.digency.gov.amc.utils.enumeration.MaritalStatus;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArtistAccount extends AcmAbstractAuditEntity {
    @Column(name = "ref_artist_account")
    private String refArtistAccount;

    private String cin;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name_ar")
    private String firstNameAR;

    @Column(name = "last_name_ar")
    private String lastNameAR;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "artist_name_ar")
    private String artistNameAR;

    private String gender;

    @Column(name = "identity_type")
    @Enumerated(EnumType.STRING)
    private IdentityType identityType;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "identity_prof_type")
    private String identityProfType;

    @Column(name = "artist_speciality")
    private String artistSpeciality;

    @Column(name = "artist_speciality_ar")
    private String artistSpecialityAR;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "other_phone_number")
    private String otherPhoneNumber;

    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "dependent_children")
    private Integer dependentChildren;

    @Column(name = "other_job_name")
    private String otherJobName;

    @Column(name = "social_security_name")
    private String socialSecurityName;

    @Column(name = "social_security_id")
    private String socialSecurityID;

    @Column(name = "artistic_work_start_date")
    private LocalDate artisticWorkStartDate;

    @Column(name = "last_artistic_activity")
    private String lastArtisticActivity;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_creation_date")
    private LocalDate teamCreationDate;

    @Column(name = "study_level")
    private String studyLevel;

    @Column(name = "artistic_establishment_name")
    private String artisticEtablishmentName;

    @Embedded
    private BirthData birthdata;

    @Column(name = "rib_number")
    private String ribNumber;

    @Column(name = "domain_name")
    private String domainName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Embedded
    private Address address;

    private String biography;

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