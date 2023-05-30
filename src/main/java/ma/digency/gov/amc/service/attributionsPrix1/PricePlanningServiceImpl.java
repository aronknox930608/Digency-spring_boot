package ma.digency.gov.amc.service.attributionsPrix1;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.PricePlanningRepository;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.PricePlanning;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricePlanningServiceImpl implements PricePlanningService{

    private final ReferenceSequenceService referenceSequenceService;

    private final PricePlanningRepository pricePlanningRepository;

    @Override
    public PricePlanning createOrUpdatePricePlanning(PricePlanning pricePlanning) {
        if(null==pricePlanning.getRefPricePlaning()){
            var ref=referenceSequenceService.generateRefPricePlanning();
            pricePlanning.setRefPricePlaning(ref);
        }
        return pricePlanningRepository.save(pricePlanning);
    }

    @Override
    public PricePlanning getPricePlanningByRef(String ref) {
        return pricePlanningRepository.findPricePlanningByRefPricePlaning(ref);
    }

    @Override
    public Void deletePricePlanning(PricePlanning pricePlanning) {
        pricePlanningRepository.delete(pricePlanning);
        return null;
    }
}
