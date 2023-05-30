package ma.digency.gov.amc.service.attributionsPrix1;

import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardType;

import java.util.List;

public interface AwardCategoriesService {

    AwardCategories createOrUpdateAwardCategory(AwardCategories awardCategories);

    List<AwardCategories> findAwardCategoriesByRefAwardType(AwardType awardType);

    AwardCategories getCategory(String refCategory);

    Void deleteAwardCategories(String refCategory);

    AwardCategories getAwardCategory(String ref);
}
