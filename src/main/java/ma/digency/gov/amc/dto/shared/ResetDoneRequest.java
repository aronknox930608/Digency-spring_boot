package ma.digency.gov.amc.dto.shared;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ResetDoneRequest {
    @NotEmpty
    private String vkey;

    @NotEmpty
    private String password;
}
