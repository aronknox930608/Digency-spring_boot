package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardCategoriesRepository extends GenericRepository<AwardCategories, Long>{


    List<AwardCategories> findAwardCategoriesByAwardType(AwardType awardType);

    AwardCategories findAwardCategoriesByRefAwardCategory(String ref);

}
