package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PublicLibraryResponse {

    private String refPublicLibrary;

    @NotEmptyString
    private String libraryName;

    private String nameOfTheResponsible;

    private String Partner;

    private List<String> partnerType;

    private String libraryStatus;

    @NotEmptyString
    private String libraryType;

    private LocalDate creationDate;

    private String space;

    private Integer employeesNumber;

    private String openingTime;

    private String address;

    private String region;

    private String provincePrefecture;

    private String office_Phone;

    private String fax;

    @NotEmptyString
    private String email;

    private String website;

    private String internetConnection;

    private String updateDateDocumentaryFund;

    private Boolean publication;

    private byte[] libraryPicture;


}
