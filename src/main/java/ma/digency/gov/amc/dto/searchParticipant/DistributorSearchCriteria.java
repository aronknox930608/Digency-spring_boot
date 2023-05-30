package ma.digency.gov.amc.dto.searchParticipant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class DistributorSearchCriteria {


    private String businessName;


    private String lineOfBusiness;


    private String ownerName;


    private String businessRegisterNumber;
}
