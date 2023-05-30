package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfessionCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ArtisticProfessionCategoryRepository extends GenericRepository<ArtisticProfessionCategory,Long> {

    List<ArtisticProfessionCategory> findAll();

    Optional<ArtisticProfessionCategory> findByRefArtisticProfessionCategory(String ref);

    Boolean deleteByRefArtisticProfessionCategory(String ref);

}
