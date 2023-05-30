package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.AwardHassan2;

public interface AwardHassanIIRepository extends GenericRepository<AwardHassan2, Long>{

    AwardHassan2 findAwardHassan2ByRefAwardHassan2(String refAward);


}
