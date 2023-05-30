package ma.digency.gov.amc.dto.attributionsPrix1;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.Award;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Validated
public class startAwardRequest {

    private Date startDate;

    private Date endDate;

    private Date announcementDate;

    private String award;

    private List<String> awardCategoriesList;
}
