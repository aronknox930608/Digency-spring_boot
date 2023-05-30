package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class AssignRoleRequest {

    @NotNull
    private Long id;

    @NotEmpty
    private List<String> roles;
}
