package ma.digency.gov.amc.service.card;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.ArtistAccountRepository;
import ma.digency.gov.amc.repository.DemandCardCriteriaAPIRepository;
import ma.digency.gov.amc.repository.DemandCardRepository;
import ma.digency.gov.amc.repository.entity.artistCard.DemandCard;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.utils.DemandPage;
import ma.digency.gov.amc.utils.searching.DemandSearchCriteria;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DemandServiceImpl implements DemandService {
    private final ReferenceSequenceService referenceSequenceService;
    private final DemandCardRepository demandCardRepository;
    private final DemandCardCriteriaAPIRepository demandCardCriteriaAPIRepository;
    private final ArtistAccountRepository artistAccountRepository;

/*
    @Override
    public DemandCard createOrUpdateDemandCard(DemandCard demandCardArtist, MultipartFile multipartFile) {
        try{
            if(demandCardArtist.getRefDemandCard()==null&& demandCardArtist.getCard().getRefCard()==null&& demandCardArtist.getArtistAccount().getRefArtistAccount()==null)
            {
                var ref1 = referenceSequenceService.generateRefDemandCard();
                demandCardArtist.setRefDemandCard(ref1);
                var ref2 = referenceSequenceService.generateRefCard();
                demandCardArtist.getCard().setRefCard(ref2);
                var ref3= referenceSequenceService.generateRefArtistAccount();
                demandCardArtist.getArtistAccount().setRefArtistAccount(ref3);
            }
            demandCardArtist.getCard().setPersonalPicture(multipartFile.getBytes());
           return demandCardRepository.ImportArtistAccountData(demandCardArtist);

        }catch (Exception e){
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);

        }

    }
*/

    @Override
    public DemandCard createDemandCard(DemandCard demandCard) {
        if(demandCard.getRefDemandCard()==null)
        {
            var ref1 = referenceSequenceService.generateRefDemandCard();
            demandCard.setRefDemandCard(ref1);
        }

        return demandCardRepository.save(demandCard);
    }


    @Override
    public void deleteDemandCard(String refDemand)  {

        demandCardRepository.delete(demandCardRepository.findDemandByRefDemandCard(refDemand));
    }

    @Override
    public DemandCard getDemandByRefDemand(String refDemand) {
        return demandCardRepository.findDemandByRefDemandCard(refDemand);

    }

    @Override
    public Page<DemandCard> allDemands(Pageable pageable) {
        return demandCardRepository.findAll(pageable);
    }


    @Override
    public Page<DemandCard> getDemandsWithCriteriaSearch(DemandPage demandPage, DemandSearchCriteria demandSearchCriteria) {
        return demandCardCriteriaAPIRepository.findAllDemandByFilter(demandPage,demandSearchCriteria);
    }


    @Override
    public List<DemandCard> getDemandsByRefArtistAccount(String refArtistAccount) {
        Optional<ArtistAccount> artistAccount=artistAccountRepository.findByRefArtistAccount(refArtistAccount);
        return demandCardRepository.findDemandByArtistAccount(artistAccount);
    }


    @Override
    public List<DemandCard> getAllDemands() {
         return demandCardRepository.findAll();

    }


}
