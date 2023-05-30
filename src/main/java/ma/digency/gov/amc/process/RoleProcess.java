package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.shared.RoleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleProcess {
    void addRole(RoleDto roleDto);

    void updateRole(RoleDto roleDto);

    void deleteRole(String refRole);

    RoleDto findRoleByRef(String refRole);

    List<RoleDto> findAll();

    PageableResponse<RoleDto> findAllPageable(Pageable pageable);

    PageableResponse<RoleDto> findAllPageableNotDeleted(Pageable pageable);
}
