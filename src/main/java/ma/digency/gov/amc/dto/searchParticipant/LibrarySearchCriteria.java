package ma.digency.gov.amc.dto.searchParticipant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class LibrarySearchCriteria {


    private String libraryName;


    private String libraryOwnerName;


    private String businessRegisterNumber;


    private String cnssNumber;
}
