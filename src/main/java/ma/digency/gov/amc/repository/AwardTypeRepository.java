package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardType;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardTypeRepository extends GenericRepository<AwardType, Long>{

    AwardType findAwardTypeByRefAwardType(String ref);
}
