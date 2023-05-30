package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.AwardTheater;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardTheaterRepository extends GenericRepository<AwardTheater,Long>{

    AwardTheater findAwardTheaterByRefAwardTheater(String refAwardTheater);
}
