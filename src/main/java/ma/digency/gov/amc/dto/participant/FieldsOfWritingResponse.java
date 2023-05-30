package ma.digency.gov.amc.dto.participant;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FieldsOfWritingResponse {

    @NotEmptyString
    private String refFieldsOfWriting;

    @NotEmptyString
    private String Name;

    private String description;
}
