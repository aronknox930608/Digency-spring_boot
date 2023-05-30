package ma.digency.gov.amc.dto.shared;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class CommissionMemberRequest {

    private String cin;

    private String firstName;

    private String lastName;

    private String gender;

    private String email;

    private String phoneNumber;

    private String login;

    @Override
    public int hashCode() {
        return Objects.hash(cin);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommissionMemberRequest)) {
            return false;
        }
        CommissionMemberRequest other = (CommissionMemberRequest) obj;
        return Objects.equals(cin, other.cin) || Objects.equals(login, other.login) || Objects.equals(email, other.email)
                || Objects.equals(phoneNumber, other.phoneNumber);
    }
}
