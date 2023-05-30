package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDtoRequest {

    private Long id;

    private String password;

    private String firstName;

    private String lastName;

    private String cin;

    private String phoneNumber;

}
