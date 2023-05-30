package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.ProposalProject;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProposalProjectRepository extends GenericRepository<ProposalProject,Long>{

    Optional<ProposalProject> findByRefProject(String refProject);
}
