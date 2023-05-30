package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardHassan2;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated

public class DemandAwardHassan2Request {

    private String refOwner;

    private String comment;

    private String award;

    private String refPersonConnected;
}
