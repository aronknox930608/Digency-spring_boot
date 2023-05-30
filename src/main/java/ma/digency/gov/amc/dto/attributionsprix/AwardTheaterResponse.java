package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class AwardTheaterResponse {

    private String refAwardTheater;

    private String type;

    private float amount;
}
