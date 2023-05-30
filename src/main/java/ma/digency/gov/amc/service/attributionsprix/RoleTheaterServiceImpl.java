package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.RoleTheaterRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.RoleTheater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleTheaterServiceImpl implements RoleTheaterService{

    private final RoleTheaterRepository roleTheaterRepository;

    @Override
    public RoleTheater findRoleTheaterByRef(String refRoleTheater) {
        return roleTheaterRepository.findRoleTheaterByRefRoleTheater(refRoleTheater);
    }

    @Override
    public RoleTheater findRoleTheaterByName(String roleName) {
        return roleTheaterRepository.findRoleTheaterByName(roleName);
    }

    @Override
    public List<RoleTheater> findAllRoleTheater() {
        return roleTheaterRepository.findAll();
    }
}
