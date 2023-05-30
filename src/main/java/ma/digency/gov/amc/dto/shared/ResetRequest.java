package ma.digency.gov.amc.dto.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetRequest {

    private String email;

    private String redirectTo;
}
