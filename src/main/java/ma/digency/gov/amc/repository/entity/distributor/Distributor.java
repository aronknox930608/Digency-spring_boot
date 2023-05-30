package ma.digency.gov.amc.repository.entity.distributor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.library.Library;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Distributor extends AcmAbstractAuditEntity {
    protected static final String DISTRIBUTOR_REF_DISTRIBUTOR = "ref_distributor";
    protected static final String LIBRARY_REF_LIBRARY = "ref_library";

    @Column(name = "ref_distributor")
    private String refDistributor;

    private String businessName;

    private String lineOfBusiness;

    private String ownerName;

    private LocalDate creationDate;

    private String socialCapital;

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

    @Type(type = "list-array")
    @Column(name = "DISTRIBUTED_BOOKS_DOMAIN", columnDefinition = "varchar[]")
    private List<String> distributedBooksDomain;

    @Type(type = "list-array")
    @Column(name = "BOOKS_LANGUAGE", columnDefinition = "varchar[]")
    private List<String> booksLanguage;

    private Integer permanentEmployeesNumber;

    private Integer temporaryEmployeesNumber;

    @Type(type = "list-array")
    @Column(name = "TYPE_OF_POINT_SALE", columnDefinition = "varchar[]")
    private List<String> typeOfPointSale;

    @Type(type = "list-array")
    @Column(name = "SOURCE_SUPPLY", columnDefinition = "varchar[]")
    private List<String> sourceSupply;

    private Integer numberPointSaleUrban;

    private Integer numberPointSaleRural;

    private Integer averageSharesDistributedAnnually;

    private Integer averageNumberContactsAnnuallyWithPublishers;

    private String overseasDistribution;

    private Integer costOfDistributionComparedRetailPrice;

    @Type(type = "list-array")
    @Column(name = "FORMS_OF_PROMOTING_BOOKS", columnDefinition = "varchar[]")
    private List<String> formsOfPromotingBooks;

    private String customerBase;

    private String membershipAssociationOrSyndicate;

    @OneToOne
    @JoinColumn(name = LIBRARY_REF_LIBRARY, referencedColumnName = LIBRARY_REF_LIBRARY)
    private Library library;


}