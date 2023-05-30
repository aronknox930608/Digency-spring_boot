package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.card.*;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountResponse;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.*;
import ma.digency.gov.amc.repository.entity.artistCard.Card;
import ma.digency.gov.amc.repository.entity.artistCard.DemandCard;
import ma.digency.gov.amc.repository.entity.artistCard.DemandPlanning;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.service.AccountService;
import ma.digency.gov.amc.service.card.CardService;
import ma.digency.gov.amc.service.card.DemandPlanningService;
import ma.digency.gov.amc.service.card.DemandService;
import ma.digency.gov.amc.service.card.ExcelService;
import ma.digency.gov.amc.service.proposalproject.ArtistAccountService;
import ma.digency.gov.amc.service.shared.DocumentService;
import ma.digency.gov.amc.utils.CardPage;
import ma.digency.gov.amc.utils.DemandPage;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.searching.CardSearchCriteria;
import ma.digency.gov.amc.utils.searching.DemandPlanningCardCriteria;
import ma.digency.gov.amc.utils.searching.DemandSearchCriteria;
import ma.digency.gov.amc.utils.DemandPlanningPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardProcessImpl implements CardProcess {

    private final DemandService demandService;
    private final ArtistAccountService artistAccountService;
    private final AccountService accountService;
    private  final CardService cardService;
    private final DemandCardMapper demandMapper;
    private final CardMapper cardMapper;
    private final DocumentService documentService;
    private  final ExcelService excelService;
    private final ArtistAccountCardMapper artistAccountMapper;
    private final DemandPlanningMapper demandPlanningMapper;
    private final DemandPlanningService demandPlanningService;
    private final  AccountMapper accountMapper;

    @Override
    public ArtistAccountCardResponse createArtistAccount(ArtistAccountCardRequest artistAccountRequest) {
        String ifEmailExist=artistAccountRequest.getEmail();
        ArtistAccount artist=artistAccountService.findArtistAccountByEmail(ifEmailExist);
        if(artist==null){
            Optional<Account> account=accountService.findAccountByEmail(ifEmailExist);
            ArtistAccount artistAccountToPersist=artistAccountMapper.artistAccountRequestToArtistAccount(artistAccountRequest);
            artistAccountToPersist.setAccount(account.get());
            ArtistAccount artistAccount=artistAccountService.createNewArtistAccount(artistAccountToPersist);
            return artistAccountMapper.artistAccountToArtistAccountResponse(artistAccount);
        }
        else{

            artistAccountRequest.setRefArtistAccount(artist.getRefArtistAccount());
            return updateArtistAccount(artistAccountRequest);
        }

    }


    @Override
    public ArtistAccountCardResponse getAccount(String email) {

        Account findAccount=accountService.findAccountByEmail(email).get();
        ArtistAccount artistAccount=artistAccountService.findArtistByCin(findAccount.getCin());
        if(artistAccount==null){
            artistAccount.setFirstName(findAccount.getFirstname());
            artistAccount.setLastName(findAccount.getLastname());
            artistAccount.setEmail(findAccount.getEmail());
            artistAccount.setCin(findAccount.getCin());
            artistAccount.setPhoneNumber(findAccount.getPhoneNumber());

            return artistAccountMapper.artistAccountToArtistAccountResponse(artistAccount);
        }else{
            return artistAccountMapper.artistAccountToArtistAccountResponse(artistAccount);

        }

           //return accountMapper.accountToAccountResponse(accountService.findAccountByEmail(email).get());


    }


    @Override
    public CardResponse retiredCard(String refCard) {
        Card find=cardService.findCardByRefCard(refCard);
        return cardMapper.cardToCardResponse(cardService.retiredCard(find));
    }


    @Override
    public Boolean isPeriodOpenForDemandCard() {
        DemandPlanning demandPlanning=demandPlanningService.findDemandPlanningByStatus(StatusEnum.OPEN);
        if(demandPlanning==null)
        {
            return false;
        }
        return true;
    }


    @Override
    public ArtistAccountCardResponse getArtistAccount(String refArtistAccount) {
        return artistAccountMapper.artistAccountToArtistAccountResponse(artistAccountService.findArtistAccountByRef(refArtistAccount).get());
    }


    @Override
    public CardResponse createCard(String refArtistAccount, CardRequest cardRequest) {
        Card card=cardMapper.cardRequestToCard(cardRequest);
        card.setRefArtistAccount(refArtistAccount);
        return cardMapper.cardToCardResponse(cardService.createCard(card));

    }


    @Override
    public DemandCardResponse createDemand(String refArtistAccount, DemandCardRequest demandRequest) {
        DemandCard demandCard= demandMapper.demandRequestToDemand(demandRequest);
        Optional<ArtistAccount> artistAccount=artistAccountService.findArtistAccountByRef(refArtistAccount);
        demandCard.setArtistAccount(artistAccount.get());
        demandCard.setStatus("EN COURS DE TRAITEMENT");
        demandCard.setDateDemand(LocalDate.now());
        return demandMapper.demandToDemandResponse(demandService.createDemandCard(demandCard));
    }


    @Override
    public void deleteDemandCard(String refDemand) {

        documentService.deleteByRefObject(refDemand);
        demandService.deleteDemandCard(refDemand);
    }


    @Override
    public void deleteCard(String refCard) {
        cardService.deleteCard(refCard);
    }


    @Override
    public DemandCardResponse getDemandByRefDemand(String refDemand) {

        return demandMapper.demandToDemandResponse(demandService.getDemandByRefDemand(refDemand));
    }

    @Override
    public DemandCardResponse updateDemand(DemandCardRequest demandRequest) {

        DemandCard oldDemandCard = demandService.getDemandByRefDemand(demandRequest.getRefDemandCard());
        demandRequest.setRefArtistAccount(oldDemandCard.getArtistAccount().getRefArtistAccount());
        DemandCardResponse demandResponseToUpdate=demandMapper.demandToDemandResponse(demandMapper.demandRequestToDemand(demandRequest));
        DemandCard newDemandCard =demandMapper.updateDemandResponseToDemand( demandResponseToUpdate, oldDemandCard);
        return demandMapper.demandToDemandResponse(demandService.createDemandCard(newDemandCard));
    }

    @Override
    public ArtistAccountCardResponse updateArtistAccount(ArtistAccountCardRequest artistAccountRequest) {
        ArtistAccount artistAccount =artistAccountService.findArtistAccountByRefArtist(artistAccountRequest.getRefArtistAccount());
        ArtistAccount artistAccountToUpdate=artistAccountMapper.updateArtistRequestToArtistAccount(artistAccountRequest,artistAccount);
        return artistAccountMapper.artistAccountToArtistAccountResponse(artistAccountService.updateArtistAccount(artistAccountToUpdate));
    }

    @Override
    public PageableResponse<DemandCardResponse> allDemands(Pageable pageable) {

        Page<DemandCardResponse> page= demandService.allDemands(pageable).map(demandMapper::demandToDemandResponse);
        return new PageableResponse<>(page);
    }

    @Override
    public PageableResponse<CardResponse> getAllCards(Pageable pageable) {

        Page<CardResponse> page= cardService.findCardsPageable(pageable).map(cardMapper::cardToCardResponse);
        return new PageableResponse<>(page);
    }


    @Override
    public List<ArtistAccountResponse> importExcelData(MultipartFile file) {
        try {
            excelService.save(file);
            System.out.println("Uploaded the file successfully: " + file.getOriginalFilename());
            return null;
        } catch (Exception e) {
            System.out.println("Could not upload the file: " + file.getOriginalFilename() + "!");
            e.printStackTrace();
        }
        return null;
    }

   @Override
    public List<CardResponse> importArtistCards(MultipartFile file) {
        try {
            excelService.saveCardExcelFile(file);
            System.out.println("Uploaded the file successfully: " + file.getOriginalFilename());
            return null;
        } catch (Exception e) {
            System.out.println("Could not upload the file: " + file.getOriginalFilename() + "!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ByteArrayInputStream exportArtistData() {
        List<ArtistAccountResponse> artist= excelService.getAllArtists();
        ByteArrayInputStream in=excelService.exportArtistData(artist);
        return in;
    }
    @Override
    public ByteArrayInputStream exportArtistCard() {
        List<CardResponse> cards= cardService.getAllCards();
        return excelService.exportArtistCard(cards);

    }


    @Override
    public Page<DemandCardResponse> getAllDemands(DemandPage demandPage, DemandSearchCriteria demandSearchCriteria) {
        return demandService.getDemandsWithCriteriaSearch(demandPage,demandSearchCriteria).map(demandMapper::demandToDemandResponse);
    }

    @Override
    public Page<CardResponse> getAllCards(CardPage cardPage, CardSearchCriteria cardSearchCriteria) {

        return cardService.getCardsWithCriteriaSearch(cardPage,cardSearchCriteria);
    }


    @Override
    public List<DemandCardResponse> getDemandsByRefArtistAccount(String refArtistAccount) {
        List<DemandCard> demands=demandService.getDemandsByRefArtistAccount(refArtistAccount);
        List<DemandCardResponse> demandRes=new ArrayList<>();
        for(DemandCard d:demands){
            demandRes.add(demandMapper.demandToDemandResponse(d));
        }
        return demandRes;
    }


    @Override
    public List<DemandCardResponse> getDemands() {
        List<DemandCard> demands=demandService.getAllDemands();
        List<DemandCardResponse> demandRes=new ArrayList<>();
        for(DemandCard d:demands){
            demandRes.add(demandMapper.demandToDemandResponse(d));
        }
        return demandRes;
    }


    @Override
    public DemandPlanningResponse createDemandPlanning(DemandPlanningRequest demandPlanningRequest) {
        LocalDate currentDate=LocalDate.now();
        if(demandPlanningRequest.getEndDate().isBefore(demandPlanningRequest.getStartedDate()))
        {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_INVALID_DATE_FOUND);
        }
        DemandPlanning demandP=demandPlanningService.findDemandPlanningByStatus(StatusEnum.OPEN);

        if(demandP!=null && demandP.getEndDate().isAfter(currentDate)){
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_OPEN_EDITION_ALREADY_EXIST);
        }
        if(demandP!=null && demandP.getEndDate().isBefore(currentDate)){
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_OPEN_EDITION_MUST_BE_CLOSE);
        }
        DemandPlanning demandPlan=demandPlanningMapper.demandPlanningRequestToDemandPlanning(demandPlanningRequest);

        if(currentDate.isEqual(demandPlanningRequest.getStartedDate()) ||
                currentDate.isBefore(demandPlanningRequest.getEndDate()) && currentDate.isAfter(demandPlanningRequest.getStartedDate()))
        {
            demandPlan.setStatus(StatusEnum.OPEN);
        }

        return demandPlanningMapper.demandPlanningToDemandPlanningResponse(demandPlanningService.createOrUpdateDemandPlanning(demandPlan));
    }


    @Override
    public void deleteDemandPlanning(String refDemandPlanning) {
        try {
            demandPlanningService.deleteDemandPlanning(refDemandPlanning);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public DemandPlanningResponse updateDemandPlanning(DemandPlanningRequest demandPlanningRequest) {

        DemandPlanning oldDemandP=demandPlanningService.getByDemandPlanningRef(demandPlanningRequest.getRefDemandPlanning());
        DemandPlanning newDemandPlanningR=demandPlanningMapper.demandPlanningRequestToDemandPlanning(demandPlanningRequest);
        DemandPlanningResponse newDemandPlanning=demandPlanningMapper.demandPlanningToDemandPlanningResponse(newDemandPlanningR);
        DemandPlanning demandToSave= demandPlanningMapper.updateDemandPlanningResponseToDemandPlanning(newDemandPlanning,oldDemandP);
        return demandPlanningMapper.demandPlanningToDemandPlanningResponse(demandPlanningService.createOrUpdateDemandPlanning(demandToSave));

    }


    @Override
    public PageableResponse<DemandPlanningResponse> getAllPlannings(Pageable pageable) {

        Page<DemandPlanningResponse> page= demandPlanningService.getAllPlannings(pageable).map(demandPlanningMapper::demandPlanningToDemandPlanningResponse);
        return new PageableResponse<>(page);
    }


    @Override
    public Page<DemandPlanningResponse> getPlanningWithSearchCriteria(DemandPlanningPage demandPlannigPage, DemandPlanningCardCriteria demandPlannigCardCriteria) {
        return demandPlanningService.getDemandPlanningBySearchCriteria(demandPlannigPage,demandPlannigCardCriteria).map(demandPlanningMapper::demandPlanningToDemandPlanningResponse);
    }


}
