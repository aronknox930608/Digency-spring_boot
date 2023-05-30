package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.mapper.AttributionsPrixMapper;
import ma.digency.gov.amc.repository.SearchFiles.DemandPage;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.service.AccountService;
import ma.digency.gov.amc.service.attributionsPrix1.AwardCategoriesService;
import ma.digency.gov.amc.service.attributionsPrix1.DemandPriceService;
import ma.digency.gov.amc.service.attributionsprix.*;
import ma.digency.gov.amc.service.proposalproject.ArtistAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.FALSE;

@Service
@RequiredArgsConstructor
public class AwardTheaterProcessImpl implements AwardTheaterProcess{

    private final ArtistAccountService artistAccountService;

    private final DemandAHService demandService;

    private final TheaterPieceService theaterPieceService;

    private final AttributionsPrixMapper attributionsPrixMapper;

    private final AwardTheaterService awardTheaterService;

    private final RoleTheaterService roleTheaterService;

    private final ParticipantsTheaterService participantsTheaterService;

    private final PriceService priceService;

    private final AccountService accountService;

    private final AwardCategoriesService awardCategoriesService;

    private final DemandPriceService demandPriceService;


    @Override
    public OwnerPersonalInfoResponse getArtist(String refAccount) {
        Account account=accountService.findAccountByRefAccount(refAccount);
        ArtistAccount artistAccount=artistAccountService.findArtistByCin(account.getCin());
        if(artistAccount==null){
            OwnerPersonalInfoResponse ownerPersonalInfoResponse=attributionsPrixMapper.accountToOwnerPersonalInfoResponse(account);
            ownerPersonalInfoResponse.setFirstName(account.getFirstname());
            ownerPersonalInfoResponse.setLastName(account.getLastname());
            return ownerPersonalInfoResponse;
        }
        else{
            return attributionsPrixMapper.artistAccountToOwnerPersonalInfoResponse(artistAccount);
        }
    }

    @Override
    public String createArtist(ArtistAccountRequest artistAccountRequest) {
        ArtistAccount artistAccount = attributionsPrixMapper.artistAccountRequestToArtistAccount(artistAccountRequest);
        ArtistAccount artistAccountFound = artistAccountService.findArtistByCin(artistAccount.getCin());
                if (artistAccountFound != null) {
                    artistAccountFound = attributionsPrixMapper.updateArtistRequestToArtistAccount(artistAccountRequest, artistAccountFound);
                    return artistAccountService.updateArtistAccount(artistAccountFound).getRefArtistAccount();
                }
        Account account=accountService.findAccountByCin(artistAccountRequest.getCin());
        artistAccount.setAccount(account);
        return artistAccountService.createNewArtistAccount(artistAccount).getRefArtistAccount();
    }

    @Override
    public TheaterPieceResponse addDemand(DemandPriceTheaterRequest demandPriceTheaterRequest) {
        TheaterPiece theaterPiece=theaterPieceService.createOrUpdate(attributionsPrixMapper.theaterPieceRequestToTheaterPiece(demandPriceTheaterRequest.getTheaterPieceRequest()));
        DemandPrice demandPrice=attributionsPrixMapper.demandPriceTheaterRequestToDemandPrice(demandPriceTheaterRequest);
        demandPrice.setAccountOwner(artistAccountService.findArtistAccountByRef(demandPriceTheaterRequest.getAccountOwner()).get());
        demandPrice.setAwardCategories(awardCategoriesService.getAwardCategory(demandPriceTheaterRequest.getAwardCategories()));
        demandPrice.setKey("ATHEATER");
        demandPrice.setTheaterPiece(theaterPiece);
        demandPrice=demandPriceService.createOrUpdateDemandPrice(demandPrice);
        DemandTheaterPieceResponse demandTheaterPieceResponse=new DemandTheaterPieceResponse();
        demandTheaterPieceResponse.setRefTheaterPiece(theaterPiece.getRefTheaterPiece());
        return this.getTheaterPiece(theaterPiece.getRefTheaterPiece());
    }

    @Override
    public String addParticipant(ParticipantRequest participantRequest) {
        ParticipantsTheater participantsTheater=attributionsPrixMapper.participantRequestToParticipantsTheater(participantRequest);
        participantsTheater.setRole1(roleTheaterService.findRoleTheaterByRef(participantRequest.getRole1()));
        participantsTheater.setRole2(roleTheaterService.findRoleTheaterByRef(participantRequest.getRole2()));
        participantsTheater.setRole3(roleTheaterService.findRoleTheaterByRef(participantRequest.getRole3()));
        participantsTheater.setTheaterPiece(theaterPieceService.findTheaterPieceByRef(participantRequest.getTheaterPiece()));
        participantsTheaterService.createOrUpdate(participantsTheater);
        return participantRequest.getTheaterPiece();
    }

    @Override
    public DemandPriceTheaterResponse getDemand(String refDemand) {
        DemandPrice demandPrice=demandPriceService.findDemandPriceByRef(refDemand);
        DemandPriceTheaterResponse demandPriceTheaterResponse=new DemandPriceTheaterResponse();
        demandPriceTheaterResponse.setRefDemand(refDemand);
        demandPriceTheaterResponse.setComment(demandPrice.getComment());
        demandPriceTheaterResponse.setDecision_date(demandPrice.getDecision_date());
        demandPriceTheaterResponse.setStatus(demandPrice.getStatus());
        demandPriceTheaterResponse.setAwardCategories(attributionsPrixMapper.awardCategoryToAwardCategoriesResponse(demandPrice.getAwardCategories()));
        List<AwardCategoriesResponse> awardCategoriesResponseList=new ArrayList<>();

        for (AwardCategories awardCategory:demandPrice.getAwardCategories().getAwardType().getAwardCategories()) {
            awardCategoriesResponseList.add(attributionsPrixMapper.awardCategoryToAwardCategoriesResponse(awardCategory));
        }
        demandPriceTheaterResponse.setListAwardCategories(awardCategoriesResponseList);
        demandPriceTheaterResponse.setTheaterPiece(demandPrice.getTheaterPiece());
        return demandPriceTheaterResponse;
    }

    @Override
    public String updateDemand(DemandPriceTheaterResponse request) {
        TheaterPiece theaterPiece=theaterPieceService.createOrUpdate(request.getTheaterPiece());
        DemandPrice demandPriceFound=demandPriceService.findDemandPriceByRef(request.getRefDemand());
        demandPriceFound.setStatus(request.getStatus());
        demandPriceFound.setComment(request.getComment());
        demandPriceFound.setDecision_date(request.getDecision_date());
        if(demandPriceFound.getAwardCategories().getRefAwardCategory().equals(request.getAwardCategories().getRefAwardCategory())==FALSE){
            demandPriceFound.setAwardCategories(awardCategoriesService.getAwardCategory(request.getAwardCategories().getRefAwardCategory()));
        }
        demandPriceFound.setTheaterPiece(theaterPiece);
        demandPriceService.createOrUpdateDemandPrice(demandPriceFound);
        return demandPriceFound.getTheaterPiece().getRefTheaterPiece();
    }

    @Override
    public List<ParticipantTheaterResponse> listParticipants(String refTheaterPrice) {
        TheaterPiece theaterPiece=theaterPieceService.findTheaterPieceByRef(refTheaterPrice);
        List<ParticipantsTheater> participantsTheaters=participantsTheaterService.getListParticipants(theaterPiece);
        List<ParticipantTheaterResponse> participantTheaterResponses=new ArrayList<>();
        for (ParticipantsTheater participantTheater:participantsTheaters) {
             participantTheaterResponses.add(attributionsPrixMapper.participantTheaterToParticipantTheaterResponse(participantTheater));
        }
        return participantTheaterResponses;
    }

    @Override
    public ParticipantTheaterResponse getParticipantByRef(String refParticipant) {
        return attributionsPrixMapper.participantTheaterToParticipantTheaterResponse(participantsTheaterService.getParticipantByRef(refParticipant));
    }

    @Override
    public List<ParticipantTheaterResponse> updateParticipant(ParticipantTheaterResponse request) {
        ParticipantsTheater participantsTheater=participantsTheaterService.getParticipantByRef(request.getRefParticipant());
        if(request.getRole1()!=null)
        participantsTheater.setRole1(roleTheaterService.findRoleTheaterByRef(request.getRole1().getRefRoleTheater()));
        if(request.getRole2()!=null)
        participantsTheater.setRole2(roleTheaterService.findRoleTheaterByRef(request.getRole2().getRefRoleTheater()));
        if(request.getRole3()!=null)
        participantsTheater.setRole3(roleTheaterService.findRoleTheaterByRef(request.getRole3().getRefRoleTheater()));
        participantsTheaterService.createOrUpdate(attributionsPrixMapper.updateParticipantTheaterResponseToParticipantsTheater(request,participantsTheater));
        return this.listParticipants(participantsTheater.getTheaterPiece().getRefTheaterPiece());
    }

    @Override
    public List<ParticipantTheaterResponse> deleteParticipant(String refParticipant) {
        ParticipantsTheater participantTheater=participantsTheaterService.getParticipantByRef(refParticipant);
        String refTheaterPiece=participantTheater.getTheaterPiece().getRefTheaterPiece();
        participantsTheaterService.deleteParticipant(participantTheater);
        return this.listParticipants(refTheaterPiece);
    }

    @Override
    public Void deleteDemand(String refDemand) {
        demandPriceService.deleteDemandPrice(demandPriceService.findDemandPriceByRef(refDemand));
        return null;
    }

    @Override
    public List<TheaterPieceResponse> getTheaterPiecesOfArtist(String cin) {
        List<TheaterPieceResponse> theaterPieces=new ArrayList<>();
        List<ParticipantsTheater> listParticipationOfArtist=participantsTheaterService.getParticipationByCin(cin);
        for (ParticipantsTheater participantTheater:listParticipationOfArtist) {
            theaterPieces.add(attributionsPrixMapper.theaterPieceToTheaterPieceResponse(participantTheater.getTheaterPiece()));
        }
        return theaterPieces;
    }

    @Override
    public TheaterPieceResponse getTheaterPiece(String refTheaterPiece) {
        return attributionsPrixMapper.theaterPieceToTheaterPieceResponse(theaterPieceService.findTheaterPieceByRef(refTheaterPiece));
    }

    @Override
    public TheaterPieceResponse updateTheaterPiece(TheaterPieceResponse request) {
        TheaterPiece theaterPiece=attributionsPrixMapper.updateTheaterPieceResponseToTheaterPiece(request,theaterPieceService.findTheaterPieceByRef(request.getRefTheaterPiece()));
        theaterPiece=theaterPieceService.createOrUpdate(theaterPiece);
        return attributionsPrixMapper.theaterPieceToTheaterPieceResponse(theaterPiece);
    }

    @Override
    public List<AwardTheaterResponse> addAwardTheater(AwardRequest awardTheaterRequest) {
        AwardTheater awardTheater=attributionsPrixMapper.awardRequestToAwardTheater(awardTheaterRequest);
        awardTheater.setPrice(priceService.getPriceByRef(awardTheaterRequest.getPrice()));
        awardTheaterService.createOrUpdate(awardTheater);
        return this.getAllAwardsTheater();
    }

    @Override
    public List<AwardTheaterResponse> updateAwardTheater(AwardTheaterResponse request) {
        AwardTheater awardTheaterFound=awardTheaterService.findAwardTheaterByRef(request.getRefAwardTheater());
        awardTheaterService.createOrUpdate(attributionsPrixMapper.updateAwardTheaterResponseToAwardTheater(request,awardTheaterFound));
        return this.getAllAwardsTheater();
    }

    @Override
    public AwardTheaterResponse getAwardTheaterByRef(String refAward) {
        return attributionsPrixMapper.awardTheaterToAwardTheaterResponse(awardTheaterService.findAwardTheaterByRef(refAward));
    }

    @Override
    public List<AwardTheaterResponse> deleteAwardTheater(String refAward) {
        awardTheaterService.deleteAwardTheater(awardTheaterService.findAwardTheaterByRef(refAward));
        return this.getAllAwardsTheater();
    }

    @Override
    public List<AwardTheaterResponse> getAllAwardsTheater() {
        List<AwardTheater> awardTheaters=awardTheaterService.getAllAwardTheater();
        List<AwardTheaterResponse> awardTheaterResponses=new ArrayList<>();
        for (AwardTheater awardTheater:awardTheaters) {
            awardTheaterResponses.add(attributionsPrixMapper.awardTheaterToAwardTheaterResponse(awardTheater));
        }
        return awardTheaterResponses;
    }

    @Override
    public Page<Demand> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType) {
        DemandPage demandPage=new DemandPage();
        demandPage.setPageNumber(pageNumber);
        demandPage.setPageSize(pageSize);
        demandPage.setSortBy(sortBy);
        demandPage.setSortDirection(sortDirection);

        DemandAwardSearchCriteria demandAwardBookSearchCriteria=new DemandAwardSearchCriteria();
        demandAwardBookSearchCriteria.setStatus(status);
        demandAwardBookSearchCriteria.setAwardType(awardType);
        demandAwardBookSearchCriteria.setDemandOwnerFirstName(demandOwnerFirstName);
        demandAwardBookSearchCriteria.setDemandOwnerLastName(demandOwnerLastName);
        demandAwardBookSearchCriteria.setDecision_date(decision_date);
        return demandService.getDemandsAwardTheater(demandPage,demandAwardBookSearchCriteria);

    }

    @Override
    public PageableResponse<DemandAwardTheaterResponse> findAllDemand(Pageable pageable) {
        Page<Demand> page;
        page = demandService.findDemandPageableAwardTheater(pageable);
        var pageResponse = page.map(attributionsPrixMapper::demandToDemandAwardTheaterResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public List<Demand> getDemandsUserLogged(String refUser) {
        Account account=accountService.findAccountByRefAccount(refUser);
        return demandService.findDemandByPersonConnected(account);
    }

    @Override
    public ByteArrayInputStream exportDemandData() {
        List<Demand> demands=demandService.demandsAwardTheater();
        ByteArrayInputStream in=demandService.exportDemandAwardTheaterData(demands);
        return in;
    }

    @Override
    public Void saveTheaterPiecesExcel(MultipartFile multipartFile) {
        theaterPieceService.save(multipartFile);
        System.out.println("uploaded the file successfully:" + multipartFile.getOriginalFilename());
        return null;
    }

    @Override
    public ByteArrayInputStream exportTheaterPiecesData() {
        List<TheaterPiece> theaterPieces=theaterPieceService.getAllTheaterPiece();
        ByteArrayInputStream in=theaterPieceService.exportTheaterPircesData(theaterPieces);
        return in;
    }

    @Override
    public Void saveParticipantsExcel(MultipartFile multipartFile, String refTheaterPiece) {
        participantsTheaterService.save(multipartFile,refTheaterPiece);
        System.out.println("uploaded the file successfully:" + multipartFile.getOriginalFilename());
        return null;
    }

    @Override
    public ByteArrayInputStream exportParticipantsData(String refTheaterPiece) {
        List<ParticipantsTheater> participantsTheaters=participantsTheaterService.getListParticipants(theaterPieceService.findTheaterPieceByRef(refTheaterPiece));
        ByteArrayInputStream in=participantsTheaterService.exportParticipantsData(participantsTheaters);
        return in;
    }

    @Override
    public String createOrUpdatePersonalInformation(OwnerPersonalInfoResponse artistPersonalInfoResponse) {
        if(artistPersonalInfoResponse.getRefArtistAccount()!=null){
            ArtistAccount artistAccountFound=artistAccountService.findArtistAccountByRef(artistPersonalInfoResponse.getRefArtistAccount()).get();
            ArtistAccount artistAccount=attributionsPrixMapper.updateOwnerPersonalInfoResponseToArtistAccount(artistPersonalInfoResponse,artistAccountFound);
            artistAccount=artistAccountService.updateArtistAccount(artistAccount);
            return artistAccount.getAccount().getRefAccount();

        }else{
            ArtistAccount artistAccount=attributionsPrixMapper.ownerPersonalInfoResponseToArtistAccount(artistPersonalInfoResponse);
            artistAccount.setAccount(accountService.findAccountByCin(artistPersonalInfoResponse.getCin()));
            artistAccount=artistAccountService.createNewArtistAccount(artistAccount);
            return artistAccount.getAccount().getRefAccount();
        }
    }

    @Override
    public PageableResponse<DemandPriceTheaterListResponse> findAllDemands(Pageable pageable) {
        Page<DemandPrice> page;
        page = demandPriceService.findDemandPageableAwardTheater(pageable);
        var pageResponse = page.map(attributionsPrixMapper::demandPriceToDemandPriceTheaterListResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public List<RoleTheater> getRoleTheater() {
        return roleTheaterService.findAllRoleTheater();
    }
}
