package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.ArtisticProfessionDomainRepository;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfessionDomain;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtisticProfessionDomainServiceImpl implements ArtisticProfessionDomainService{
    private final ArtisticProfessionDomainRepository artisticProfessionDomainRepository;
    private final ReferenceSequenceService referenceSequenceService;


    @Override
    public ArtisticProfessionDomain createNewArtisticProfessionDomain(ArtisticProfessionDomain artisticProfessionDomain) {
        artisticProfessionDomain.setRefArtisticProfessionDomain(referenceSequenceService.generateRefArtisticProfessionDomain());
        return artisticProfessionDomainRepository.save(artisticProfessionDomain);
    }

    @Override
    public ArtisticProfessionDomain updateArtisticProfessionDomain(ArtisticProfessionDomain artisticProfessionDomain) {
        return artisticProfessionDomainRepository.save(artisticProfessionDomain);
    }

    @Override
    public List<ArtisticProfessionDomain> findAllArtisticProfessionDomains() {
        return artisticProfessionDomainRepository.findAll();
    }

    @Override
    public Optional<ArtisticProfessionDomain> findArtisticProfessionDomainByRef(String ref) {
        return artisticProfessionDomainRepository.findByRefArtisticProfessionDomain(ref);
    }

    @Override
    public List<ArtisticProfessionDomain> findAllArtisticProfessionDomainsByCategory(String refArtisticProfessionCategory) {
        return artisticProfessionDomainRepository.findArtisticProfessionDomainByRefArtisticProfessionCategory(refArtisticProfessionCategory);
    }

    @Override
    public void deleteArtisticProfessionDomain(String refArtisticProfessionDomain) {
        artisticProfessionDomainRepository.delete(artisticProfessionDomainRepository.findByRefArtisticProfessionDomain(
                refArtisticProfessionDomain).orElseThrow(
                ()->{throw  new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);}
        ));
    }
}
