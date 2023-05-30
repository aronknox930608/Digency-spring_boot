package ma.digency.gov.amc.dto.participant;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FacilitiesServicesResponse {

    @NotEmptyString
    private String refFacilitiesServices;

    @NotEmptyString
    private String Name;

    private String description;


}
