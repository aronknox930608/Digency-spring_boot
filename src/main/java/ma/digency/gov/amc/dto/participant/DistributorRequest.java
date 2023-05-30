package ma.digency.gov.amc.dto.participant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Validated
public class DistributorRequest {

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

    @Email
    private String email;

    private String website;

    private List<String> distributedBooksDomain;

    private List<String> booksLanguage;

    private Integer permanentEmployeesNumber;

    private Integer temporaryEmployeesNumber;

    private List<String> typeOfPointSale;

    private List<String> sourceSupply;

    private Integer numberPointSaleUrban;

    private Integer numberPointSaleRural;

    private Integer averageSharesDistributedAnnually;

    private Integer averageNumberContactsAnnuallyWithPublishers;

    private String overseasDistribution;

    private Integer costOfDistributionComparedRetailPrice;

    private List<String> formsOfPromotingBooks;

    private String customerBase;

    private String membershipAssociationOrSyndicate;


}
