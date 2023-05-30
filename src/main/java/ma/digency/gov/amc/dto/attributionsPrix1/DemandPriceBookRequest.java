package ma.digency.gov.amc.dto.attributionsPrix1;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class DemandPriceBookRequest {

    private String comment;

    private String accountOwner;

    private String awardCategories;

    private String bookPrice;

}
