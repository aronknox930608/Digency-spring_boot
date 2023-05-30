package ma.digency.gov.amc.service.attributionsPrix1;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.AwardCategoriesRepository;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardType;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardCategoriesServiceImpl implements AwardCategoriesService{

    private final ReferenceSequenceService referenceSequenceService;

    private final AwardCategoriesRepository awardCategoriesRepository;
    @Override
    public AwardCategories createOrUpdateAwardCategory(AwardCategories awardCategories) {
        if(null==awardCategories.getRefAwardCategory()){
            var ref=referenceSequenceService.generateRefAwardCategories();
            awardCategories.setRefAwardCategory(ref);
        }
        return awardCategoriesRepository.save(awardCategories);
    }

    @Override
    public List<AwardCategories> findAwardCategoriesByRefAwardType(AwardType awardType) {
        return awardCategoriesRepository.findAwardCategoriesByAwardType(awardType);
    }

    @Override
    public AwardCategories getCategory(String refCategory) {
        return awardCategoriesRepository.findAwardCategoriesByRefAwardCategory(refCategory);
    }

    @Override
    public Void deleteAwardCategories(String refCategory) {
        awardCategoriesRepository.delete(this.getCategory(refCategory));
        return null;
    }

    @Override
    public AwardCategories getAwardCategory(String ref) {
        return awardCategoriesRepository.findAwardCategoriesByRefAwardCategory(ref);
    }
}
