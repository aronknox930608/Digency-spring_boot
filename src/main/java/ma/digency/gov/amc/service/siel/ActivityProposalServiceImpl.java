package ma.digency.gov.amc.service.siel;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.ActivityProposalRepository;
import ma.digency.gov.amc.repository.entity.siel.ActivityProposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityProposalServiceImpl implements ActivityProposalService {

    private final ActivityProposalRepository activityProposalRepository;

    @Override
    public void deleteActivityProposal(String refExhibitor, String refActivityProposal) {
        activityProposalRepository.delete(findActivityProposalByRefActivityProposalAndRefExhibitor(refActivityProposal, refExhibitor));
    }

    @Override
    public ActivityProposal findActivityProposalByRefActivityProposalAndRefExhibitor(String refActivityProposal,String refExhibitor) {
        return activityProposalRepository.findActivityProposalByRefActivityProposalAndRefExhibitor(refActivityProposal, refExhibitor)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND));

    }

    @Override
    public Page<ActivityProposal> findActivityProposalByRefExhibitor(String refExhibitor, Pageable pageable) {
        return activityProposalRepository.findActivityProposalsByRefExhibitor(refExhibitor,pageable);
    }
}
