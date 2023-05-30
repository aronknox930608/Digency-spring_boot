package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.Address;
import ma.digency.gov.amc.repository.entity.BirthData;
import ma.digency.gov.amc.utils.enumeration.IdentityType;
import ma.digency.gov.amc.utils.enumeration.MaritalStatus;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@Validated
public class ArtistAccountRequest {
    private String refArtistAccount;

    private String cin;

    private String firstName;

    private String lastName;

    private String firstNameAR;

    private String lastNameAR;

    private String artistName;

    private String artistNameAR;

    private String gender;

    private IdentityType identityType;

    private String identityNumber;

    private String identityProfType;

    private String artistSpeciality;

    private String artistSpecialityAR;

    @Email
    private String email;

    private String phoneNumber;

    private String otherPhoneNumber;

    private MaritalStatus maritalStatus;

    private Integer dependentChildren;

    private String otherJobName;

    private String socialSecurityName;

    private String socialSecurityID;

    private LocalDate artisticWorkStartDate;

    private String lastArtisticActivity;

    private String teamName;

    private LocalDate teamCreationDate;

    private String studyLevel;

    private String artisticEtablishmentName;

    private BirthData birthdata;

    private String ribNumber;

    private String domainName;

    private Address address;

    private StatusEnum status;

    private GeneralInformationRequest generalInformation;

    private String refArtisticProfession;
}
