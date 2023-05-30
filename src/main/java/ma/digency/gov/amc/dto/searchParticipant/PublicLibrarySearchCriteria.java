package ma.digency.gov.amc.dto.searchParticipant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class PublicLibrarySearchCriteria {


    private String libraryName;


    private String nameOfTheResponsible;


    private String partner;


    private String libraryStatus;
}
