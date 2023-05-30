package ma.digency.gov.amc.service.attributionsPrix1;


import ma.digency.gov.amc.repository.entity.attributionsPrix1.Award;

import java.util.List;

public interface AwardService {

    Award createOrUpdateAward(Award award);

    Award getAward(String refAward);

    Void deleteAward(String refAward);

    List<Award> getListAward();

}
