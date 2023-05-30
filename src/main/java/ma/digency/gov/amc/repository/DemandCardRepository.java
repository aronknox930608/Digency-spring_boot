package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.artistCard.DemandCard;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandCardRepository extends GenericRepository<DemandCard,Long>{

        DemandCard findDemandByRefDemandCard(String refDemand);
        List<DemandCard> findDemandByArtistAccount(Optional<ArtistAccount> refArtistAccount);



}
