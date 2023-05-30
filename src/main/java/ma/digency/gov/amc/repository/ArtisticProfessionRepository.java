package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtisticProfessionRepository extends GenericRepository<ArtisticProfession,Long> {

    List<ArtisticProfession> findAll();

    Optional<ArtisticProfession> findByRefArtisticProfession(String ref);

    List<ArtisticProfession> findArtisticProfessionByRefArtisticProfessionDomain(String refArtisticProfessionDomain);

    Boolean deleteByRefArtisticProfession(String ref);
}
