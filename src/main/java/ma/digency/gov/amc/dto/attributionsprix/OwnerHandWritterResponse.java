package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class OwnerHandWritterResponse {

    private String refOwnerHandWritten;

    private String cin;

    private String firstName;

    private String lastName;

}
