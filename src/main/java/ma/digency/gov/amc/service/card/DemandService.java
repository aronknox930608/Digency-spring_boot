package ma.digency.gov.amc.service.card;

import ma.digency.gov.amc.repository.entity.artistCard.DemandCard;
import ma.digency.gov.amc.utils.DemandPage;
import ma.digency.gov.amc.utils.searching.DemandSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DemandService {

    DemandCard createDemandCard(DemandCard demandCard);
    void deleteDemandCard(String refDemand);
    DemandCard getDemandByRefDemand(String refDemand);
    Page<DemandCard> allDemands(Pageable pageable);
    Page<DemandCard> getDemandsWithCriteriaSearch(DemandPage demandPage, DemandSearchCriteria demandSearchCriteria);
    List<DemandCard> getDemandsByRefArtistAccount(String refArtistAccount);
    List<DemandCard> getAllDemands();

}
