package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.Price;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class AwardBookResponse {

    private String refAwardBook;

    private String type;

    private float amount;

    private Price price;
}
