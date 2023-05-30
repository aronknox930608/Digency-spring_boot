package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsPrix1.CategoriesPlanning;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesPlanningRepository extends GenericRepository<CategoriesPlanning, Long>{

    List<CategoriesPlanning> findCategoriesPlanningByRefPricePlanning(String ref);

    CategoriesPlanning findCategoriesPlanningByRefCategoriesPlanning(String ref);
}
