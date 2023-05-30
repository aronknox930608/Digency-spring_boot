package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.shared.RoleAccount;

import java.util.List;

@Getter
@Setter
public class RolesAccountResponse {
    private List<RoleAccount> roles;
}
