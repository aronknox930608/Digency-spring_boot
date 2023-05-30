package ma.digency.gov.amc.dto.participant;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Validated
public class FacilitiesServicesRequest {

    @NotBlank
    private String Name;

    private String Description;


}
