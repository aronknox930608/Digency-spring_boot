package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardBook;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;

import java.util.List;

public interface AwardBookService {

    AwardBook findAwardBookByRefAwardBook(String refAwardBook);

    AwardBook updateAwardBook(AwardBook awardBook);

    AwardBook createOrUpdate(AwardBook awardBook);

    List<AwardBook> getAllAwards();

    void deleteAwardBook(AwardBook awardBook);

}
