package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsPrix1.Award;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardRepository extends GenericRepository<Award, Long>{

    Award findAwardByRefAward(String refAward);
}
