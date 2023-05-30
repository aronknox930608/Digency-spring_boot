package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.attributionsPrix1.PricePlanning;
import org.springframework.stereotype.Repository;

@Repository
public interface PricePlanningRepository extends GenericRepository<PricePlanning, Long>{

      PricePlanning findPricePlanningByRefPricePlaning(String ref);

}
