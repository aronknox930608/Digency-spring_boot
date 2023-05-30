package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Getter
@Setter
@Validated
public class DemandAwardSearchCriteria {

    private String status;

    private Date decision_date;

    private String demandOwnerFirstName;

    private String demandOwnerLastName;

    private String awardType;

}
