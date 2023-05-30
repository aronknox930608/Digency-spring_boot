package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.ProjectSubDomain;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectSubDomainRepository extends GenericRepository<ProjectSubDomain,Long>{

    Optional<ProjectSubDomain> findByRefSubDomain(String refSubDomain);
}
