package ma.digency.gov.amc.service.proposalproject;

import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfessionDomain;

import java.util.List;
import java.util.Optional;

public interface ArtisticProfessionDomainService {
    ArtisticProfessionDomain createNewArtisticProfessionDomain(ArtisticProfessionDomain ArtisticProfessionDomain);
    ArtisticProfessionDomain updateArtisticProfessionDomain(ArtisticProfessionDomain ArtisticProfessionDomain);
    List<ArtisticProfessionDomain> findAllArtisticProfessionDomains();
    Optional<ArtisticProfessionDomain> findArtisticProfessionDomainByRef(String refArtisticProfessionDomain);
    List<ArtisticProfessionDomain> findAllArtisticProfessionDomainsByCategory(String refArtisticProfessionCategory);
    void deleteArtisticProfessionDomain(String refArtisticProfessionDomain);

}
