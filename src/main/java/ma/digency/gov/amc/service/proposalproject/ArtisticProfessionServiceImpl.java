package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.ArtisticProfessionRepository;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfession;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtisticProfessionServiceImpl implements ArtisticProfessionService{
    private final ArtisticProfessionRepository artisticProfessionRepository;
    private final ReferenceSequenceService referenceSequenceService;


    @Override
    public ArtisticProfession createNewArtisticProfession(ArtisticProfession artisticProfession) {
        artisticProfession.setRefArtisticProfession(referenceSequenceService.generateRefArtisticProfession());
        return artisticProfessionRepository.save(artisticProfession);
    }

    @Override
    public ArtisticProfession updateArtisticProfession(ArtisticProfession artisticProfession) {
        return artisticProfessionRepository.save(artisticProfession);
    }

    @Override
    public List<ArtisticProfession> findAllArtisticProfessions() {
        return artisticProfessionRepository.findAll();
    }

    @Override
    public Optional<ArtisticProfession> findArtisticProfessionByRef(String ref) {
        return artisticProfessionRepository.findByRefArtisticProfession(ref);
    }

    @Override
    public List<ArtisticProfession> findAllArtisticProfessionsByDomain(String refArtisticProfessionDomain) {
        return artisticProfessionRepository.findArtisticProfessionByRefArtisticProfessionDomain(refArtisticProfessionDomain);
    }

    @Override
    public void deleteArtisticProfession(String refArtisticProfession) {
         artisticProfessionRepository.delete(artisticProfessionRepository.findByRefArtisticProfession(
                refArtisticProfession).orElseThrow(
                        ()->{throw  new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);}
        ));
    }


}
