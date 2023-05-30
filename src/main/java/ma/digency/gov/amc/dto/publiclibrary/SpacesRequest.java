package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class SpacesRequest {

    private Integer numberOfSeats;

    private Integer childrenArea;

    private Integer adultSpace;

    private Integer youthArea;

    private Integer multimediaSpace;

    private Integer grandTotalOfSeats;
}
