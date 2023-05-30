package ma.digency.gov.amc.dto.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommissionMemberResponse {

    private String refCommissionMember;

    private String cin;

    private String firstName;

    private String lastName;

    private String gender;

}
