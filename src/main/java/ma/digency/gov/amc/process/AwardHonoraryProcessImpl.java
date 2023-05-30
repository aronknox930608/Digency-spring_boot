package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.DocumentTypeResponse;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.AwardCategoriesResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryListResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryRequest;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryResponse;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.mapper.AttributionsPrixMapper;
import ma.digency.gov.amc.repository.SearchFiles.DemandPage;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardHonorary;
import ma.digency.gov.amc.repository.entity.attributionsprix.TheaterPiece;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.service.AccountService;
import ma.digency.gov.amc.service.attributionsPrix1.AwardCategoriesService;
import ma.digency.gov.amc.service.attributionsPrix1.DemandPriceService;
import ma.digency.gov.amc.service.attributionsprix.*;
import ma.digency.gov.amc.service.proposalproject.ArtistAccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AwardHonoraryProcessImpl implements AwardHonoraryProcess {

    private final AttributionsPrixMapper attributionsPrixMapper;

    @Autowired
    private final DemandAHService demandAHService;

    @Autowired
    private final CandidateHonoraryAwardService candidateHonoraryAwardService;

    @Autowired
    private final AwardHonoraryService awardHonoraryService;

    private final PriceService priceService;

    private final AccountService accountService;

    private final ArtistAccountService artistAccountService;

    private final DocumentProcess documentProcess;

    private final DemandPriceService demandPriceService;

    private final AwardCategoriesService awardCategoriesService;

    @Override
    public String createCandidate(CandidateHonoraryAwardRequest request,MultipartFile picture, MultipartFile cv,MultipartFile identityStatement){
        var artistAccount=attributionsPrixMapper.candidateHonoraryAwardRequestToArtistAccount(request);
        artistAccount=artistAccountService.createNewArtistAccount(artistAccount);
        documentProcess.saveDocument("picture",picture,artistAccount.getRefArtistAccount(),artistAccount.getRefArtistAccount());
        documentProcess.saveDocument("cv",cv,artistAccount.getRefArtistAccount(),artistAccount.getRefArtistAccount());
        documentProcess.saveDocument("identityStatement",identityStatement,artistAccount.getRefArtistAccount(),artistAccount.getRefArtistAccount());
        return artistAccount.getRefArtistAccount();
    }

    @Override
    public String createDemand(DemandPriceHonoraryRequest request) {
        DemandPrice demandPrice=new DemandPrice();
        demandPrice.setComment(request.getComment());
        demandPrice.setStatus("PENDING");
        demandPrice.setKey("AHONORARY");
        demandPrice.setAccountOwner(artistAccountService.findArtistAccountByRef(request.getAccountOwner()).get());
        demandPrice.setAwardCategories(awardCategoriesService.getAwardCategory(request.getAwardCategories()));
        demandPrice=demandPriceService.createOrUpdateDemandPrice(demandPrice);
        return demandPrice.getRefDemand();
    }

    @Override
    public DemandPriceHonoraryResponse getDemand(String refDemand) {
        DemandPrice demandPrice=demandPriceService.findDemandPriceByRef(refDemand);
        DemandPriceHonoraryResponse demandPriceHonoraryResponse=new DemandPriceHonoraryResponse();
        demandPriceHonoraryResponse.setRefDemand(refDemand);
        demandPriceHonoraryResponse.setComment(demandPrice.getComment());
        demandPriceHonoraryResponse.setDecision_date(demandPrice.getDecision_date());
        demandPriceHonoraryResponse.setStatus(demandPrice.getStatus());
        demandPriceHonoraryResponse.setAwardCategories(attributionsPrixMapper.awardCategoryToAwardCategoriesResponse(demandPrice.getAwardCategories()));
        List<AwardCategoriesResponse> awardCategoriesResponseList=new ArrayList<>();

        for (AwardCategories awardCategory:demandPrice.getAwardCategories().getAwardType().getAwardCategories()) {
            awardCategoriesResponseList.add(attributionsPrixMapper.awardCategoryToAwardCategoriesResponse(awardCategory));
        }
        demandPriceHonoraryResponse.setListAwardCategories(awardCategoriesResponseList);
        return demandPriceHonoraryResponse;
    }

    @Override
    public DemandPriceHonoraryResponse updateDemand(DemandPriceHonoraryResponse request) {
        DemandPrice demandPriceFound=demandPriceService.findDemandPriceByRef(request.getRefDemand());
        demandPriceFound.setStatus(request.getStatus());
        demandPriceFound.setComment(request.getComment());
        demandPriceFound.setDecision_date(request.getDecision_date());
        if(demandPriceFound.getAwardCategories().getRefAwardCategory().equals(request.getAwardCategories().getRefAwardCategory())==FALSE){
            demandPriceFound.setAwardCategories(awardCategoriesService.getAwardCategory(request.getAwardCategories().getRefAwardCategory()));
        }
        demandPriceService.createOrUpdateDemandPrice(demandPriceFound);
        return this.getDemand(request.getRefDemand());
    }


    @Override
    public String updateCandidate(CandidateHonoraryAwardResponse request,MultipartFile picture,MultipartFile cv, MultipartFile identityStatement){
        var artistAccount=attributionsPrixMapper.candidateHonoraryAwardResponseToArtistAccount(request);
        ArtistAccount artistAccountFound=artistAccountService.findArtistAccountByRef(request.getRefArtistAccount()).get();
        artistAccountFound=attributionsPrixMapper.updateCandidateHonoraryAwardResponseToArtistAccount(request,artistAccountFound);
        artistAccountService.updateArtistAccount(artistAccountFound);
        if(picture!=null){
            documentProcess.updateDocument(picture,request.getRefpicture());
        }
        if(cv!=null){
            documentProcess.updateDocument(cv,request.getRefCv());
        }
        if(identityStatement!=null){
            documentProcess.updateDocument(identityStatement,request.getRefIdentityStatement());
        }
        return artistAccount.getRefArtistAccount();
    }

    @Override
    public void deleteDemand(String refDemand) {
        DemandPrice demandPrice = demandPriceService.findDemandPriceByRef(refDemand);
       demandPriceService.deleteDemandPrice(demandPrice);
    }

    @Override
    public PageableResponse<DemandHonoraryAwardToListResponse> findAllDemand(Pageable pageable) {
        Page<Demand> page;
        page = demandAHService.findDemandPageableAwardHonorary(pageable);

        var pageResponse = page.map(attributionsPrixMapper::demandToDemandHonoraryAwardToListResponse);
        return new PageableResponse<>(pageResponse);

    }

    @Override
    public List<AwardHonoraryResponse> addAwardHonorary(AwardRequest request) {
        AwardHonorary awardHonorary=attributionsPrixMapper.awardRequestToAwardHonorary(request);
        awardHonorary.setPrice(priceService.getPriceByRef(request.getPrice()));
        awardHonoraryService.createOrUpdate(awardHonorary);
        return this.findAllAwards();
    }

    @Override
    public List<AwardHonoraryResponse> findAllAwards() {
        List<AwardHonorary> listAwardHonorary=awardHonoraryService.findAllAwardHonorary();
        List<AwardHonoraryResponse> awardResponses=new ArrayList<>();
        for (AwardHonorary awardHonorary:listAwardHonorary) {
            awardResponses.add(attributionsPrixMapper.awardHonoraryToAwardHonoraryResponse(awardHonorary));
        }
        return awardResponses;
    }

    @Override
    public AwardHonoraryResponse getAwardHonoraryByRef(String refAward) {
        return attributionsPrixMapper.awardHonoraryToAwardHonoraryResponse(awardHonoraryService.findAwardHonoraryByRef(refAward));
    }

    @Override
    public List<AwardHonoraryResponse> updateAwardHonorary(AwardHonoraryResponse request) {
        AwardHonorary awardHonorary=awardHonoraryService.findAwardHonoraryByRef(request.getRefAwardHonorary());
        awardHonoraryService.createOrUpdate(attributionsPrixMapper.updateAwardHonoraryResponseToAwardHonorary(request,awardHonorary));
        return this.findAllAwards();
    }

    @Override
    public List<AwardHonoraryResponse> deleteAwardHonorary(String refAward) {
        awardHonoraryService.delete(awardHonoraryService.findAwardHonoraryByRef(refAward));
        return this.findAllAwards();
    }

    @Override
    public List<Demand> getDemandsUserLogged(String refUser) {
        Account account=accountService.findAccountByRefAccount(refUser);
        return demandAHService.findDemandByPersonConnected(account);
    }

    @Override
    public Page<Demand> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType) {
        DemandPage demandPage=new DemandPage();
        demandPage.setPageNumber(pageNumber);
        demandPage.setPageSize(pageSize);
        demandPage.setSortBy(sortBy);
        demandPage.setSortDirection(sortDirection);
        DemandAwardSearchCriteria demandAwardHonorarySearchCriteria=new DemandAwardSearchCriteria();
        demandAwardHonorarySearchCriteria.setStatus(status);
        demandAwardHonorarySearchCriteria.setAwardType(awardType);
        demandAwardHonorarySearchCriteria.setDemandOwnerFirstName(demandOwnerFirstName);
        demandAwardHonorarySearchCriteria.setDemandOwnerLastName(demandOwnerLastName);
        demandAwardHonorarySearchCriteria.setDecision_date(decision_date);
        return demandAHService.getDemandsAwardHonorary(demandPage,demandAwardHonorarySearchCriteria);

    }

    @Override
    public List<ArtistAccount> getListArtists() {
        return artistAccountService.findAllArtistAccounts();
    }

    @Override
    public CandidateHonoraryAwardResponse getCandidateByRef(String refCandidate) {
        ArtistAccount artistAccount=artistAccountService.findArtistAccountByRef(refCandidate).get();
        CandidateHonoraryAwardResponse candidateHonoraryAwardResponse=new CandidateHonoraryAwardResponse();
        candidateHonoraryAwardResponse=attributionsPrixMapper.artistAccountToCandidateHonoraryAwardResponse(artistAccount);
        List<DocumentTypeResponse> documents= documentProcess.findDocuments(refCandidate);
        for (DocumentTypeResponse document:documents) {
            if(document.getNature().equals("picture")){
                candidateHonoraryAwardResponse.setRefpicture(document.getRefDocument());
            }else if(document.getNature().equals("cv")){
                candidateHonoraryAwardResponse.setRefCv(document.getRefDocument());
            }else {
                candidateHonoraryAwardResponse.setRefIdentityStatement(document.getRefDocument());
            }
        }
        return candidateHonoraryAwardResponse;
    }

    @Override
    public Void saveArtistsExcel(MultipartFile file) {
        artistAccountService.save(file);
        System.out.println("uploaded the file successfully:" + file.getOriginalFilename());
        return null;
    }

    @Override
    public ByteArrayInputStream exportArtistsData() {
        List<ArtistAccount> artistAccounts=artistAccountService.findAllArtistAccounts();
        ByteArrayInputStream in=artistAccountService.exportArtistsData(artistAccounts);
        return in;
    }

    @Override
    public ByteArrayInputStream exportDemandData() {
        List<Demand> demands=demandAHService.demandsAwardHonorary();
        ByteArrayInputStream in=demandAHService.exportDemandAwardHonoraryData(demands);
        return in;
    }

    @Override
    public PageableResponse<DemandPriceHonoraryListResponse> findAllDemands(Pageable pageable) {
        Page<DemandPrice> page;
        page = demandPriceService.findDemandPageableAwardHonorary(pageable);
        var pageResponse = page.map(attributionsPrixMapper::demandPriceToDemandPriceHonoraryListResponse);
        return new PageableResponse<>(pageResponse);
    }
}


