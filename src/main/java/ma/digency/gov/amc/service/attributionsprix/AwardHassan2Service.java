package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardHassan2;

import java.util.List;

public interface AwardHassan2Service {

    AwardHassan2 findAwardHassan2ByRefAwardHassan2(String refAwardHassan2);

    AwardHassan2 createOrUpdate(AwardHassan2 awardHassan2);

    List<AwardHassan2> findAllAwards();

    void deleteAwardHassan2(AwardHassan2 awardHassan2);
}
