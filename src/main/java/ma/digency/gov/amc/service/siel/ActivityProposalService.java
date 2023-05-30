package ma.digency.gov.amc.service.siel;

import ma.digency.gov.amc.repository.entity.siel.ActivityProposal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityProposalService {

    void deleteActivityProposal(String refExhibitor, String refActivityProposal);

    ActivityProposal findActivityProposalByRefActivityProposalAndRefExhibitor(String refExhibitor, String refActivityProposal);

    Page<ActivityProposal> findActivityProposalByRefExhibitor(String refExhibitor, Pageable pageable);
}
