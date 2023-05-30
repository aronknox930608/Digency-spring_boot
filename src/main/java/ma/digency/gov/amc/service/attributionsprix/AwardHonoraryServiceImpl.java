package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.AwardHonoraryRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardHonorary;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardHonoraryServiceImpl implements AwardHonoraryService{

    private final AwardHonoraryRepository awardHonoraryRepository;

    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public AwardHonorary findAwardHonoraryByRef(String refAwardHonorary) {
        return awardHonoraryRepository.findAwardHonoraryByRefAwardHonorary(refAwardHonorary);
    }

    @Override
    public AwardHonorary createOrUpdate(AwardHonorary awardHonorary) {
        if(null==awardHonorary.getRefAwardHonorary()){
            var ref=referenceSequenceService.generateRefAwardHonorary();
            awardHonorary.setRefAwardHonorary(ref);
        }
        return awardHonoraryRepository.save(awardHonorary);
    }

    @Override
    public List<AwardHonorary> findAllAwardHonorary() {
        return awardHonoraryRepository.findAll();
    }

    @Override
    public void delete(AwardHonorary awardHonorary) {
        awardHonoraryRepository.delete(awardHonorary);
    }
}
