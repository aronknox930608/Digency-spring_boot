package ma.digency.gov.amc.dto.attributionsPrix1;


import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class DemandPriceHonoraryRequest {

    private String comment;

    private String accountOwner;

    private String awardCategories;

}
