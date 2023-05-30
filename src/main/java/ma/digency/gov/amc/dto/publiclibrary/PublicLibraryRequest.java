package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Validated
public class PublicLibraryRequest {


    private String refPublicLibrary;

    @NotBlank
    private String libraryName;

    private String nameOfTheResponsible;

    private String Partner;

    private List<String> partnerType;

    private String libraryStatus;

    @NotBlank
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

    @NotBlank
    private String email;

    private String website;

    private String internetConnection;

    private String updateDateDocumentaryFund;

    private Boolean publication;

    private byte[] libraryPicture;


}
