package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String user;

    private String fullname;

    private String token;

    private String img;

    private String cin;

    private String[] roles;
}
