package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.Price;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository  extends GenericRepository<Price,Long>{

    Price findPriceByRefPrice(String ref);
}
