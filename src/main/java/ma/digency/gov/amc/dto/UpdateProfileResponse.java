package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileResponse {
    private String email;

    private String firstname;

    private String lastname;

    private String token;
}
