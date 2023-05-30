package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfession;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfessionDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtisticProfessionDomainRepository extends GenericRepository<ArtisticProfessionDomain,Long> {

    List<ArtisticProfessionDomain> findAll();

    Optional<ArtisticProfessionDomain> findByRefArtisticProfessionDomain(String ref);

    List<ArtisticProfessionDomain> findArtisticProfessionDomainByRefArtisticProfessionCategory(String refArtisticProfessionCategory);

    Boolean deleteByRefArtisticProfessionDomain(String ref);

}
