package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {
    private String email;

    private String firstname;

    private String lastname;
}
