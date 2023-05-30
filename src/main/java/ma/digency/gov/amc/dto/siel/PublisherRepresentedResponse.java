package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class PublisherRepresentedResponse {

    private String refPublisherRepresented;

    @NotEmptyString
    private String businessName;

    private String ownerName;

    private LocalDate creationDate;

    private String businessRegisterNumber;

    private String commonCompanyIdentifier;

    private String cnssNumber;

    private String space;

    private String Address;

    private String city;

    @NotEmptyString
    private String phoneNumber;

    private String faxNumber;

    @Email
    private String email;

    private String website;

    private Integer numberBooksPublishedPerYear;

    private Integer numberBooksPublishedLastYear;

    private List<String> typeBooksPublished;

    private List<String> publicationLanguage;

    private Integer numberPublicationsPerYearArabic;

    private Integer numberPublicationsPerYearAmazigh;

    private Integer numberPublicationsPerYearFrench;

    private Integer numberPublicationsPerYearEnglish;

    private Integer permanentEmployeesNumber;

    private Integer temporaryEmployeesNumber;

    private List<String> diffusers;

    private List<String> customerBase;

    @NotEmptyString
    private String responsibleName;

    private List<String> activityBranches;

    @Override
    public int hashCode() {
        return Objects.hash(refPublisherRepresented);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PublisherRepresentedResponse)) {
            return false;
        }
        PublisherRepresentedResponse other = (PublisherRepresentedResponse) obj;
        return Objects.equals(refPublisherRepresented, other.refPublisherRepresented);
    }
}
