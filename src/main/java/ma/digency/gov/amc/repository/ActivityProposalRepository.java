package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.siel.ActivityProposal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityProposalRepository extends GenericRepository<ActivityProposal, Long> {

    Optional<ActivityProposal> findActivityProposalByRefActivityProposalAndRefExhibitor(String refActivity, String refExhibitor);

    Page<ActivityProposal> findActivityProposalsByRefExhibitor(String refExhibitor, Pageable pag);
}
