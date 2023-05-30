package ma.digency.gov.amc.repository.entity.siel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublisherRepresented extends AcmAbstractAuditEntity {

    @Column(name = "ref_publisher_Represented")
    private String refPublisherRepresented;

    private String businessName;

    private String ownerName;

    private LocalDate creationDate;

    private String businessRegisterNumber;

    private String commonCompanyIdentifier;

    private String cnssNumber;

    private String space;

    private String Address;

    private String city;

    private String phoneNumber;

    private String faxNumber;

    private String email;

    private String website;

    private Integer numberBooksPublishedPerYear;

    private Integer numberBooksPublishedLastYear;

    @Type(type = "list-array")
    @Column(name = "TYPE_BOOKS_PUBLISHED", columnDefinition = "varchar[]")
    private List<String> typeBooksPublished;

    @Type(type = "list-array")
    @Column(name = "PUBLICATION_LANGUAGE", columnDefinition = "varchar[]")
    private List<String> publicationLanguage;

    private Integer numberPublicationsPerYearArabic;

    private Integer numberPublicationsPerYearAmazigh;

    private Integer numberPublicationsPerYearFrench;

    private Integer numberPublicationsPerYearEnglish;

    private Integer permanentEmployeesNumber;

    private Integer temporaryEmployeesNumber;

    @Type(type = "list-array")
    @Column(name = "DIFFUSERS", columnDefinition = "varchar[]")
    private List<String> diffusers;

    @Type(type = "list-array")
    @Column(name = "CUSTOMER_BASE", columnDefinition = "varchar[]")
    private List<String> customerBase;


    @Column(name = "ref_exhibitor")
    private String refExhibitor;

    @Type(type = "list-array")
    @Column(name = "ACTIVITY_BRANCHE", columnDefinition = "varchar[]", nullable = false)
    private List<String> activityBranches;
}
