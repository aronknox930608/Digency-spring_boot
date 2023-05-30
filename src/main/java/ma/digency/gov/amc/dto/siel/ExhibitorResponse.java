package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ExhibitorResponse {

    private String refExhibitor;

    @NotEmptyString
    private String country; //

    @Email
    private String email; //

    private String cin; //

    @NotEmptyString
    private String personalPhoneNumber; //

    @NotEmptyString
    private String city; //

    @NotEmptyString
    private String phoneNumber; //

    @NotEmptyString
    private String fax; //

    @NotEmptyString
    private String address;

    @NotEmptyString
    private String hallClass;

    @NotEmptyString
    private String specialization;

    @NotEmptyString
    private String numberArabicVersion;

    @NotEmptyString
    private String numberForeignVersion;

    @NotEmptyString
    private String companyRepresentative;

    private List<String> presentedMateriels;

    private List<String> activityBranches;

    @NotEmptyString
    private String wingAreaSquare;

    @NotEmptyString
    private String publishingHouseName;

    @NotEmptyString
    private String responsibleManagerName;

    private StatusEnum status;

    private LocalDate passportExpiration;

    private String passportNumber;

    private String passportType;

    private String personName;

    private LocalDate birthDay;

    private String birthCountry;

    private String birthNationality;

    private boolean hasMultipleRepresented;

    private boolean hasMultipleForiegen;

}
