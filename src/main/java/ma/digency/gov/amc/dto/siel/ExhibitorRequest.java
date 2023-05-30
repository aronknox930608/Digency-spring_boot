package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Validated
public class ExhibitorRequest {

    @NotBlank
    private String country; //

    @Email
    private String email; //

    private String cin; //

    @NotBlank
    private String city; //

    @NotBlank
    private String phoneNumber; //

    @NotBlank
    private String fax; //

    @NotBlank
    private String address;

    @NotBlank
    private String hallClass;

    @NotBlank
    private String specialization;

    private List<String> activityBranches;

    @NotBlank
    private String personalPhoneNumber; //

    @NotBlank
    private String numberArabicVersion;

    @NotBlank
    private String numberForeignVersion;

    @NotBlank
    private String companyRepresentative;

    private List<String> presentedMateriels;

    @NotBlank
    private String wingAreaSquare;

    @NotBlank
    private String publishingHouseName;

    @NotBlank
    private String responsibleManagerName;

    private LocalDate passportExpiration;

    private String passportNumber;

    private String passportType;

    private String personName;

    private LocalDate birthDay;

    private String birthCountry;

    private String birthNationality;

    private boolean hasMultipleRepresented;

    private boolean hasMultipleForiegen;

    private boolean doNotSendEmail;


}
