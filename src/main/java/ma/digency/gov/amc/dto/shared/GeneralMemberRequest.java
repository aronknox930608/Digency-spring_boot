package ma.digency.gov.amc.dto.shared;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class GeneralMemberRequest {

    private String refParent;

    private String cin;

    private String firstName;

    private String lastName;

    private String gender;

    private String email;

    private String phoneNumber;

    private String role;

}
