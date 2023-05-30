package ma.digency.gov.amc.service.attributionsPrix1;

import ma.digency.gov.amc.repository.entity.attributionsPrix1.CategoriesPlanning;

import java.util.List;

public interface CategoriesPlanningService {

    CategoriesPlanning createOrUpdateCategoriesPlanning(CategoriesPlanning categoriesPlanning);

    List<CategoriesPlanning> getListCategoriesPlanningByRefPricePlanning(String ref);

    Void deleteCategoriesPlanning(String ref);
}
