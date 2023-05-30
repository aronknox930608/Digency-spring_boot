package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.RoleTheater;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleTheaterRepository extends GenericRepository<RoleTheater,Long>{

    RoleTheater findRoleTheaterByRefRoleTheater(String refRoleTheater);

    RoleTheater findRoleTheaterByName(String roleName);
}
