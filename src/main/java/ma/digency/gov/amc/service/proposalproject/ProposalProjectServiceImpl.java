package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.repository.ProposalProjectRepository;
import ma.digency.gov.amc.repository.entity.proposalproject.ProposalProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static ma.digency.gov.amc.exception.MinistryOfCultureMessage.AMC_PROPOSAL_PROJECT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProposalProjectServiceImpl implements ProposalProjectService{

    @Autowired
    private ProposalProjectRepository proposalProjectRepository;

    @Override
    public ProposalProject findByRefProposalProject(String refProposalProject) {
        return proposalProjectRepository.findByRefProject(refProposalProject)
                .orElseThrow(()-> new MinistryOfCultureBusinessException(AMC_PROPOSAL_PROJECT_NOT_FOUND));
    }

    @Override
    public List<ProposalProject> findAllProposalProject() {
        return proposalProjectRepository.findAll();
    }

    @Override
    public Page<ProposalProject> findAllProposalProjectPageable(Pageable pageable) {
        return proposalProjectRepository.findAll(pageable);
    }

    @Override
    public ProposalProject createOrUpdate(ProposalProject proposalProject) {
        return proposalProjectRepository.save(proposalProject);
    }
}
