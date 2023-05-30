package ma.digency.gov.amc.service.proposalproject;

import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfession;

import java.util.List;
import java.util.Optional;

public interface ArtisticProfessionService {
    ArtisticProfession createNewArtisticProfession(ArtisticProfession ArtisticProfession);
    ArtisticProfession updateArtisticProfession(ArtisticProfession ArtisticProfession);
    List<ArtisticProfession> findAllArtisticProfessions();
    Optional<ArtisticProfession> findArtisticProfessionByRef(String refArtisticProfession);
    List<ArtisticProfession> findAllArtisticProfessionsByDomain(String refArtisticProfessionDomain);
    void deleteArtisticProfession(String refArtisticProfession);

}
