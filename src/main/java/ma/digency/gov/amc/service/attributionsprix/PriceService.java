package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.Price;

public interface PriceService {

    Price createOrUpdatePrice(Price price);

    Price getPriceByRef(String ref);

    Void deletePrice(Price price);
}
