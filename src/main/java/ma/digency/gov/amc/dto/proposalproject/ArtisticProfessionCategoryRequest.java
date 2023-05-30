package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;



@Getter
@Setter
@Validated
public class ArtisticProfessionCategoryRequest {

    private String Name;

    private String NameAr;
}