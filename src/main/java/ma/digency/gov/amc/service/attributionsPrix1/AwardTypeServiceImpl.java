package ma.digency.gov.amc.service.attributionsPrix1;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.AwardCategoriesRepository;
import ma.digency.gov.amc.repository.AwardTypeRepository;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardType;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardTypeServiceImpl implements AwardTypeService {

    private final ReferenceSequenceService referenceSequenceService;

    private final AwardTypeRepository awardTypeRepository;

    private final AwardCategoriesRepository awardCategoriesRepository;

    @Override
    public AwardType createOrUpdateAwardType(AwardType awardType) {
        if(null==awardType.getRefAwardType()){
            var ref=referenceSequenceService.generateRefAwardType();
            awardType.setRefAwardType(ref);
        }
        return awardTypeRepository.save(awardType);
    }

    @Override
    public AwardType findAwardTypeByRef(String ref) {
        return awardTypeRepository.findAwardTypeByRefAwardType(ref);
    }

    @Override
    public Void deleteAwardType(String ref) {
        awardTypeRepository.delete(this.findAwardTypeByRef(ref));
        return null;
    }

    @Override
    public List<AwardCategories> getListAwardCategories(AwardType awardType) {
        return awardCategoriesRepository.findAwardCategoriesByAwardType(awardType);
    }
}
