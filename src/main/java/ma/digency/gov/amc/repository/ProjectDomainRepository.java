package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.ProjectDomain;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectDomainRepository extends GenericRepository<ProjectDomain,Long> {

    Optional<ProjectDomain> findByRefDomain(String refDomain);
}
