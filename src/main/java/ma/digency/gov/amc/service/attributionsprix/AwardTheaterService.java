package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.attributionsprix.AwardTheater;

import java.util.List;

public interface AwardTheaterService {

    AwardTheater findAwardTheaterByRef(String refAwardTheater);

    AwardTheater createOrUpdate(AwardTheater awardTheater);

    List<AwardTheater> getAllAwardTheater();

    Void deleteAwardTheater(AwardTheater awardTheater);
}
