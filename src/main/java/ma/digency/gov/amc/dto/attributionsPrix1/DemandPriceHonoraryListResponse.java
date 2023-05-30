package ma.digency.gov.amc.dto.attributionsPrix1;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.AccountResponse;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Getter
@Setter
@Validated
public class DemandPriceHonoraryListResponse {

    private String refDemand;

    private String status;

    private Date decision_date;

    private String comment;

    private String key;

    private AccountResponse accountOwner;

    private AwardCategoriesResponse awardCategories;
}
