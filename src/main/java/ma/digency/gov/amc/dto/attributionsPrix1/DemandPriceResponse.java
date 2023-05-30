package ma.digency.gov.amc.dto.attributionsPrix1;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.validation.annotation.Validated;
import java.util.Date;

@Getter
@Setter
@Validated
public class DemandPriceResponse {

    private String refDemand;

    private String status;

    private Date decision_date;

    private String comment;

    private Account accountOwner;

    private AwardCategories awardCategories;
}
