package ma.digency.gov.amc.service.attributionsPrix1;

import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardType;

import java.util.List;

public interface AwardTypeService {

    AwardType createOrUpdateAwardType(AwardType awardType);

    AwardType findAwardTypeByRef(String ref);

    Void deleteAwardType(String ref);

    List<AwardCategories> getListAwardCategories(AwardType awardType);

}
