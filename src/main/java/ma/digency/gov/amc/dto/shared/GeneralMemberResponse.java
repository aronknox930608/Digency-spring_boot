package ma.digency.gov.amc.dto.shared;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GeneralMemberResponse {

    private String refGeneralMember;

    private String refParent;

    private String cin;

    private String firstName;

    private String lastName;

    private String gender;

    private String email;

    private String phoneNumber;

    private String role;

}
