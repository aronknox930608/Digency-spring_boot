package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.AwardTheaterRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardTheater;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardTheaterServiceImpl implements AwardTheaterService{

    private final AwardTheaterRepository awardTheaterRepository;

    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public AwardTheater findAwardTheaterByRef(String refAwardTheater) {
        return awardTheaterRepository.findAwardTheaterByRefAwardTheater(refAwardTheater);
    }

    @Override
    public AwardTheater createOrUpdate(AwardTheater awardTheater) {
        if(null==awardTheater.getRefAwardTheater()){
            var ref=referenceSequenceService.generateRefAwardTheater();
            awardTheater.setRefAwardTheater(ref);
        }
        return awardTheaterRepository.save(awardTheater);
    }

    @Override
    public List<AwardTheater> getAllAwardTheater() {
        return awardTheaterRepository.findAll();
    }

    @Override
    public Void deleteAwardTheater(AwardTheater awardTheater) {
       awardTheaterRepository.delete(awardTheater);
       return null;
    }
}
