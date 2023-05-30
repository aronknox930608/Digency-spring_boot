package ma.digency.gov.amc.dto.library;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.library.LibraryOtherProduct;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Validated
public class LibraryRequest {

    @NotBlank
    private String libraryName;

    private String libraryOwnerName;

    private LocalDate libraryCreationDate;

    private String businessRegisterNumber;

    private String StandardDefinitionOfBusiness;

    private String cnssNumber;

    private String librarySpace;

    private String Address;

    @NotBlank
    private String city;

    private String phoneNumber;

    @Email
    private String email;

    private String adressesNumber;

    private List<String> booksLanguageSold;

    private Integer permanentEmployeesNumber;

    private Integer temporaryWorkersNumber;

    private List<String> supplySource;

    private List<String> customerBase;

    private List<String> libraryOtherProduct;

    private List<String> suggestedTypesBook;


}
