package ma.digency.gov.amc.service.attributionsprix;


import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.AwardHassanIIRepository;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardHassan2;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardHassan2ServiceImpl implements AwardHassan2Service{


    private final AwardHassanIIRepository awardHassan2Repository;

    private  final ReferenceSequenceService referenceSequenceService;

    @Override
    public AwardHassan2 findAwardHassan2ByRefAwardHassan2(String refAwardHassan2) {
        return awardHassan2Repository.findAwardHassan2ByRefAwardHassan2(refAwardHassan2);
    }

    @Override
    public AwardHassan2 createOrUpdate(AwardHassan2 awardHassan2) {
        if(null==awardHassan2.getRefAwardHassan2()){
            var ref=referenceSequenceService.generateRefAwardHassan2();
            awardHassan2.setRefAwardHassan2(ref);
        }

        return awardHassan2Repository.save(awardHassan2);
    }

    @Override
    public List<AwardHassan2> findAllAwards() {
        return awardHassan2Repository.findAll();
    }

    @Override
    public void deleteAwardHassan2(AwardHassan2 awardHassan2) {
        awardHassan2Repository.delete(awardHassan2);
    }
}
