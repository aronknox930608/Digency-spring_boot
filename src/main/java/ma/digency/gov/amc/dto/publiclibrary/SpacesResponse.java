package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

@Getter
@Setter
public class SpacesResponse {

    @NotEmptyString
    private String refSpaces;

    private Integer numberOfSeats;

    private Integer childrenArea;

    private Integer adultSpace;

    private Integer youthArea;

    private Integer multimediaSpace;

    private Integer grandTotalOfSeats;
}
