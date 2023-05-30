package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.attributionsprix.RoleTheater;

import java.util.List;

public interface RoleTheaterService {

    RoleTheater findRoleTheaterByRef(String refRoleTheater);

    RoleTheater findRoleTheaterByName(String roleName);

    List<RoleTheater> findAllRoleTheater();
}
