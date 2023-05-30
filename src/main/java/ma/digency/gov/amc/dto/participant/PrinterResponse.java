package ma.digency.gov.amc.dto.participant;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PrinterResponse {

    private String refPrinter;

    @NotEmptyString
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

    @Email
    private String email;

    private String website;

    private List<String> booksLanguage;

    private Integer permanentEmployeesNumber;

    private Integer temporaryEmployeesNumber;

    private List<String> facilitiesAndServices;

    private String customerBase;

    private List<String> otherProducts;

    private Integer averageBooksPrintedAnnually;

    private Integer averageOrdersAnnuallyRequestPublishers;

    private String membershipAssociationOrSyndicate;

}
