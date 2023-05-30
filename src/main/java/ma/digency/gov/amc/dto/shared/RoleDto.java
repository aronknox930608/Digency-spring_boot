package ma.digency.gov.amc.dto.shared;

import lombok.Data;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

@Data
public class RoleDto {
    private String refRole;

    private String codeRole;

    private String labelRole;

    private Boolean editable;

    private Boolean removable;

    private StatusEnum status;
}
