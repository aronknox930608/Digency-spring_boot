package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AssignRolesRequest {
    private String refAccount;

    private List<String> roles;
}
