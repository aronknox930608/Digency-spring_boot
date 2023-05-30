package ma.digency.gov.amc.dto.attributionsPrix1;


import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.attributionsprix.TheaterPieceRequest;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class DemandPriceTheaterRequest {

    private String comment;

    private String accountOwner;

    private String awardCategories;

    private TheaterPieceRequest theaterPieceRequest;
}
