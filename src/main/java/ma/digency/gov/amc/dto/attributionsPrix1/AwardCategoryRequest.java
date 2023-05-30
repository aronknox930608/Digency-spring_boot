package ma.digency.gov.amc.dto.attributionsPrix1;


import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class AwardCategoryRequest {

    private String titleFr;

    private String titleAr;

    private String refAwardType;
}
