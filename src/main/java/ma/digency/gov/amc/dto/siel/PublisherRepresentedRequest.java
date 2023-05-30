package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.shared.DocumentRequest;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class PublisherRepresentedRequest {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(this.refPublisherRepresented==null) return false;
        PublisherRepresentedRequest that = (PublisherRepresentedRequest) o;
        return refPublisherRepresented.equals(that.refPublisherRepresented);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refPublisherRepresented);
    }

    private String refPublisherRepresented;

    @NotBlank
    private String businessName;

    private String ownerName;

    private LocalDate creationDate;

    private String businessRegisterNumber;

    private String commonCompanyIdentifier;

    private String cnssNumber;

    private String space;

    private String Address;

    private String city;

    @NotBlank
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

    @NotBlank
    private String responsibleName;

    private Integer numberForeignPublishing;

    private List<String> activityBranches;

    private DocumentRequest photoScanned;
}
