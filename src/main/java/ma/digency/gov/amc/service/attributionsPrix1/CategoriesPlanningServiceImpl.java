package ma.digency.gov.amc.service.attributionsPrix1;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.CategoriesPlanningRepository;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.CategoriesPlanning;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesPlanningServiceImpl implements CategoriesPlanningService{

    private final ReferenceSequenceService referenceSequenceService;

    private final CategoriesPlanningRepository categoriesPlanningRepository;

    @Override
    public CategoriesPlanning createOrUpdateCategoriesPlanning(CategoriesPlanning categoriesPlanning) {
        if(null==categoriesPlanning.getRefCategoriesPlanning()){
            var ref=referenceSequenceService.generateRefCategoriesPlanning();
            categoriesPlanning.setRefCategoriesPlanning(ref);
        }
        return categoriesPlanningRepository.save(categoriesPlanning);
    }

    @Override
    public List<CategoriesPlanning> getListCategoriesPlanningByRefPricePlanning(String ref) {
        return categoriesPlanningRepository.findCategoriesPlanningByRefPricePlanning(ref);
    }

    @Override
    public Void deleteCategoriesPlanning(String ref) {
        categoriesPlanningRepository.delete(categoriesPlanningRepository.findCategoriesPlanningByRefCategoriesPlanning(ref));
        return null;
    }
}
