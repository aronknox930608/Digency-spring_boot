package ma.digency.gov.amc.service.attributionsPrix1;

import ma.digency.gov.amc.repository.entity.attributionsPrix1.PricePlanning;

public interface PricePlanningService {

    PricePlanning createOrUpdatePricePlanning(PricePlanning pricePlanning);

    PricePlanning getPricePlanningByRef(String ref);

    Void deletePricePlanning(PricePlanning pricePlanning);
}
