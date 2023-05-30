package ma.digency.gov.amc.service.proposalproject;

import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfessionCategory;

import java.util.List;
import java.util.Optional;

public interface ArtisticProfessionCategoryService {
    ArtisticProfessionCategory createNewArtisticProfessionCategory(ArtisticProfessionCategory ArtisticProfessionCategory);
    ArtisticProfessionCategory updateArtisticProfessionCategory(ArtisticProfessionCategory ArtisticProfessionCategory);
    List<ArtisticProfessionCategory> findAllArtisticProfessionCategory();
    Optional<ArtisticProfessionCategory> findArtisticProfessionCategoryByRef(String refArtisticProfessionCategory);
    void deleteArtisticProfessionCategory(String refArtisticProfessionCategory);

}
