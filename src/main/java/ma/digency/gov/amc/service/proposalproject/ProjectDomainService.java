package ma.digency.gov.amc.service.proposalproject;

import ma.digency.gov.amc.repository.entity.proposalproject.ProjectDomain;
import ma.digency.gov.amc.repository.entity.proposalproject.ProjectSubDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectDomainService {

    ProjectDomain addOrUpdateProjectDomain(ProjectDomain projectDomain);

    void deleteProjectDomain(String refProjectDomain);

    ProjectDomain findProjectDomain(String refProjectDomain);

    List<ProjectDomain> findAll();

    Page<ProjectDomain> findAllPage(Pageable pageable);

    ProjectSubDomain findProjectSubDomain(String refSubDomain);
}
