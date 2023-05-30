package ma.digency.gov.amc.service.shared;

import ma.digency.gov.amc.repository.entity.shared.Role;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role findRoleByCodeRole(String codeRole);

    void addRole(Role role);

    Optional<Role> findRoleByRef(String refRole);

    Page<Role> findAllPageable(Pageable pageable);

    Page<Role> findAllPageableWithNotStatus(Pageable pageable, StatusEnum status);

    void updateRole(Role role);

    void updateStatusRole(Role role,StatusEnum status);

    void deleteRoleByRef(String refRole);

    List<Role> findAll();
}
