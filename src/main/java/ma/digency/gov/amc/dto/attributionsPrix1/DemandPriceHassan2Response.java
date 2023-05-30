package ma.digency.gov.amc.dto.attributionsPrix1;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.attributionsprix.ManuscriptInformationResponse;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Validated
public class DemandPriceHassan2Response {

    private String refDemand;

    private String status;

    private Date decision_date;

    private String comment;

    private AwardCategoriesResponse awardCategories;

    private List<AwardCategoriesResponse> listAwardCategories;

    private ManuscriptInformationResponse manuscriptInformation;
}
