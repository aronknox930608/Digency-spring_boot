package ma.digency.gov.amc.repository.entity.printer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.author.Book;
import ma.digency.gov.amc.repository.entity.library.Library;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Printer extends AcmAbstractAuditEntity {
    protected static final String PRINTER_REF_PRINTER = "ref_printer";
    protected static final String LIBRARY_REF_LIBRARY = "ref_library";


    @Column(name = "ref_printer")
    private String refPrinter;

    private String businessName;

    private String lineOfBusiness;

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

    @Type(type = "list-array")
    @Column(name = "BOOKS_LANGUAGE", columnDefinition = "varchar[]")
    private List<String> booksLanguage;

    private Integer permanentEmployeesNumber;

    private Integer temporaryEmployeesNumber;

    @Type(type = "list-array")
    @Column(name = "FACILITIES_AND_SERVICES", columnDefinition = "varchar[]")
    private List<String> facilitiesAndServices;

    private String customerBase;

    @Type(type = "list-array")
    @Column(name = "OTHER_PRODUCTS", columnDefinition = "varchar[]")
    private List<String> otherProducts;

    private Integer averageBooksPrintedAnnually;

    private Integer averageOrdersAnnuallyRequestPublishers;

    private String membershipAssociationOrSyndicate;


    @OneToOne
    @JoinColumn(name = LIBRARY_REF_LIBRARY, referencedColumnName = LIBRARY_REF_LIBRARY)
    private Library library;
}