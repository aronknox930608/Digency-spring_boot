package ma.digency.gov.amc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationLoginRequest {

    private String email;

    private String password;
}
