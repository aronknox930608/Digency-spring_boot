package ma.digency.gov.amc.service.proposalproject;

import ma.digency.gov.amc.repository.entity.proposalproject.ProposalProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProposalProjectService {

    ProposalProject findByRefProposalProject(String refProposalProject);

    List<ProposalProject> findAllProposalProject();

    Page<ProposalProject> findAllProposalProjectPageable(Pageable pageable);

    ProposalProject createOrUpdate(ProposalProject proposalProject);


}
