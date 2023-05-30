package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.shared.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends GenericRepository<Role, Long> {

    Optional<Role> findRoleByCodeRole(String codeRole);

    Optional<Role> findRoleByRefRole(String refRole);
}
