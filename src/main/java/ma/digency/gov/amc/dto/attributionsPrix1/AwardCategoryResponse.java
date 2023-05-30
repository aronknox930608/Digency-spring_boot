package ma.digency.gov.amc.dto.attributionsPrix1;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardType;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class AwardCategoryResponse {

    private String refAwardCategory;

    private String titleFr;

    private String titleAr;

    private AwardType awardType ;
}
