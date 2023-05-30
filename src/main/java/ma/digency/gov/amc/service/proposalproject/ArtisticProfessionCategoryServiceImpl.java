package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.ArtisticProfessionCategoryRepository;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfessionCategory;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtisticProfessionCategoryServiceImpl implements ArtisticProfessionCategoryService{
    private final ArtisticProfessionCategoryRepository artisticProfessionCategoryRepository;
    private final ReferenceSequenceService referenceSequenceService;


    @Override
    public ArtisticProfessionCategory createNewArtisticProfessionCategory(ArtisticProfessionCategory artisticProfessionCategory) {
        artisticProfessionCategory.setRefArtisticProfessionCategory(referenceSequenceService.generateRefArtisticProfessionCategory());
        return artisticProfessionCategoryRepository.save(artisticProfessionCategory);
    }

    @Override
    public ArtisticProfessionCategory updateArtisticProfessionCategory(ArtisticProfessionCategory artisticProfessionCategory) {
        return artisticProfessionCategoryRepository.save(artisticProfessionCategory);
    }

    @Override
    public List<ArtisticProfessionCategory> findAllArtisticProfessionCategory() {
        return artisticProfessionCategoryRepository.findAll();
    }

    @Override
    public Optional<ArtisticProfessionCategory> findArtisticProfessionCategoryByRef(String ref) {
        return artisticProfessionCategoryRepository.findByRefArtisticProfessionCategory(ref);
    }

    @Override
    public void deleteArtisticProfessionCategory(String ref) {
        artisticProfessionCategoryRepository.delete(artisticProfessionCategoryRepository.findByRefArtisticProfessionCategory(
                ref).orElseThrow(
                ()->{throw  new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);}
        ));
    }

}
