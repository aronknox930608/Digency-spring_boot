package ma.digency.gov.amc.dto.attributionsPrix1;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Validated
public class startAwardResponse {

    private String refPricePlaning;

    private Date startDate;

    private Date endDate;

    private Date announcementDate;

    private String award;

    private AwardTypeResponse awardType;

    private List<AwardCategoriesResponse> awardCategoriesList;

}
