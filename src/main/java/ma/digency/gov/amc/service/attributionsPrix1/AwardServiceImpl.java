package ma.digency.gov.amc.service.attributionsPrix1;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.AwardRepository;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.Award;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private final ReferenceSequenceService referenceSequenceService;

    private final AwardRepository awardRepository;


    @Override
    public Award createOrUpdateAward(Award award) {
        if(null==award.getRefAward()){
            var ref=referenceSequenceService.generateRefAward();
            award.setRefAward(ref);
        }
        return awardRepository.save(award);
    }

    @Override
    public Award getAward(String refAward) {
        return awardRepository.findAwardByRefAward(refAward);
    }

    @Override
    public Void deleteAward(String refAward) {
        awardRepository.delete(this.getAward(refAward));
        return null;
    }

    @Override
    public List<Award> getListAward() {
        return awardRepository.findAll();
    }
}

