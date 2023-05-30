package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class DemandAwardBookRequest {

    private String refArtist;

    private String comment;

    private String refAwardBook;

    private String refPersonConnected;
}
