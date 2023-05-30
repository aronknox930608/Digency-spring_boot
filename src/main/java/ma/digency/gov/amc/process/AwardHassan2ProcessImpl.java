package ma.digency.gov.amc.process;


import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.mapper.AttributionsPrixMapper;
import ma.digency.gov.amc.repository.*;
import ma.digency.gov.amc.repository.SearchFiles.DemandPage;
import ma.digency.gov.amc.repository.SearchFiles.OwnerHandWrittenPage;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.Document;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.service.AccountService;
import ma.digency.gov.amc.service.attributionsPrix1.AwardCategoriesService;
import ma.digency.gov.amc.service.attributionsPrix1.AwardTypeService;
import ma.digency.gov.amc.service.attributionsPrix1.DemandPriceService;
import ma.digency.gov.amc.service.attributionsprix.*;
import ma.digency.gov.amc.service.proposalproject.ArtistAccountService;
import ma.digency.gov.amc.service.shared.DocumentService;
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
public class AwardHassan2ProcessImpl implements AwardHassan2Process {

    private final AttributionsPrixMapper attributionsPrixMapper;

    private final DemandAHService demandAHService;

    private final OwnerHandWrittenService ownerHandWrittenService;

    private final DocumentService documentService;

    private final AwardHassan2Service awardHassan2Service;

    private final ManuscriptInformationService manuscriptInformationService;

    private final FontTypeService fontTypeService;

    private final ManuscriptInformationRepository manuscriptInformationRepository;

    private final DemandAHService demandService;

    private final ManuscriptTypeService manuscriptTypeService;

    private final PriceService priceService;

    private final AccountService accountService;

    private final ArtistAccountService artistAccountService;

    private final AwardCategoriesService awardCategoriesService;

    private final DemandPriceService demandPriceService;

    private final AwardTypeService awardTypeService;


    @Override
    public OwnerHandWritterResponse createOwner(OwnerHandWritterRequest request) {
        var ownerHandwritten = attributionsPrixMapper.ownerHandWritterRequestToOwnerHandWritten(request);
        return attributionsPrixMapper.OwnerHandWrittenToOwnerHandWritterResponse(ownerHandWrittenService.createOrUpdateownerHW(ownerHandwritten));
    }

    @Override
    public DemandAwardHassan2Response createDemand(DemandAwardHassan2Request request) {
        var demandHD = attributionsPrixMapper.DemandAwardHassan2RequestToDemand(request);
        demandHD.setStatus("PENDING");
        OwnerHandWritten owner = ownerHandWrittenService.findOwnerHandWrittenByRef(request.getRefOwner());
        demandHD.setDemandOwner(owner);
        AwardHassan2 awardHassan2 = awardHassan2Service.findAwardHassan2ByRefAwardHassan2(request.getAward());
        demandHD.setAward(awardHassan2);
        demandHD.setPersonConnected(accountService.findAccountByRefAccount(request.getRefPersonConnected()));
        DemandAwardHassan2Response demandResponse = attributionsPrixMapper.DemandToDemandAwardHassan2Response(demandAHService.createOrUpdateDemand(demandHD));
        demandResponse.setRefOwner(owner.getRefOwnerHandWritten());
        return demandResponse;
    }

    @Override
    public ManuscriptInfromationAddingResponse addManuscriptInformation(ManuscriptInformationRequest request) {
        var manuscriptInformation = attributionsPrixMapper.manuscriptInformationRequestToManuscriptInformation(request);
        OwnerHandWritten owner = ownerHandWrittenService.findOwnerHandWrittenByRef(request.getOwner());
        manuscriptInformation.setOwner(artistAccountService.findArtistAccountByRef(request.getOwner()).get());
        Demand demand = demandAHService.findDemandByRefDemand(request.getDemand());
        manuscriptInformation.setDemand(demandPriceService.findDemandPriceByRef(request.getDemand()));
        //FontType fontType = fontTypeService.findFontTypeByRefFontType(request.getFontType());
       // manuscriptInformation.setFontType(fontType);
        ManuscriptType manuscriptType = manuscriptTypeService.findManuscriptTypeByRefManuscriptType(request.getType());
        manuscriptInformation.setType(manuscriptType);
        manuscriptInformationService.createOrUpdatemanuscript(manuscriptInformation);
        ManuscriptInfromationAddingResponse manuscriptInformationResponse = new ManuscriptInfromationAddingResponse();
        manuscriptInformationResponse.setRefDemand(request.getDemand());
        return manuscriptInformationResponse;
    }


    @Override
    public DemandResponse getDemandByRef(String refDemand) {
        Demand demand = demandService.findDemandByRef(refDemand);
        DemandResponse demandResponse = new DemandResponse();
        ManuscriptInformation manuscriptInformation = manuscriptInformationRepository.findManuscriptInformationByDemand(demand);
        ManuscriptInformationResponse manuscriptInformationResponse = attributionsPrixMapper.manuscriptInformationToManuscriptInformationResponse(manuscriptInformationRepository.findManuscriptInformationByDemand(demand));
        demandResponse.setRefDemand(refDemand);
        demandResponse.setStatus(demand.getStatus());
        demandResponse.setComment(demand.getComment());
        demandResponse.setDemandOwner(demand.getDemandOwner());
        demandResponse.setAward(demand.getAward().getRefAwardHassan2());
        demandResponse.setManuscriptInformation(manuscriptInformationResponse);
        return demandResponse;

    }

    @Override
    public DemandResponse updateDemand(DemandResponse request) {
        OwnerHandWritten owner = ownerHandWrittenService.createOrUpdateownerHW(request.getDemandOwner());
        AwardHassan2 award = awardHassan2Service.findAwardHassan2ByRefAwardHassan2(request.getAward());
        Demand demand = new Demand();
        demand.setRefDemand(request.getRefDemand());
        demand.setDemandOwner(owner);
        demand.setStatus(request.getStatus());
        demand.setComment(request.getComment());
        demand.setAward(award);
        demand.setDecision_date(request.getDecision_date());
        ManuscriptInformation manuscriptInformation = attributionsPrixMapper.manuscriptInformationResponseToManuscriptInformation(request.getManuscriptInformation());
        FontType fontType = fontTypeService.findFontTypeByRefFontType(request.getManuscriptInformation().getFontType().getRefFontType());
        //manuscriptInformation.setFontType(fontType);
        ManuscriptType manuscriptType = manuscriptTypeService.findManuscriptTypeByRefManuscriptType(request.getManuscriptInformation().getType().getRefManuscriptType());
        manuscriptInformation.setType(manuscriptType);
        manuscriptInformation = manuscriptInformationService.update(manuscriptInformation);
        demand = demandAHService.update(demand);
        DemandResponse demandResponse = new DemandResponse();
        demandResponse.setRefDemand(demand.getRefDemand());
        demandResponse.setStatus(demand.getStatus());
        demandResponse.setComment(demand.getComment());
        demandResponse.setDemandOwner(demand.getDemandOwner());
        demandResponse.setAward(demand.getAward().getRefAwardHassan2());
        demandResponse.setDecision_date(demand.getDecision_date());
        demandResponse.setManuscriptInformation(attributionsPrixMapper.manuscriptInformationToManuscriptInformationResponse(manuscriptInformation));
        return demandResponse;
    }

    @Override
    public void deleteDemand(String refDemand) {
        ManuscriptInformation manuscriptInformation = manuscriptInformationRepository.findManuscriptInformationByDemand(demandAHService.findDemandByRef(refDemand));
        manuscriptInformationService.delete(manuscriptInformation);
        documentService.deleteDocument(refDemand);
        Demand demand = demandAHService.findDemandByRef(refDemand);
        demandAHService.deleteDemand(demand);
    }

    @Override
    public PageableResponse<DemandResponse> findAllDemand(Pageable pageable) {
        Page<Demand> page;
        page = demandAHService.findDemandPageableAwardHassan2(pageable);
        var pageResponse = page.map(attributionsPrixMapper::demandToDemandResponse);
        return new PageableResponse<>(pageResponse);

    }

    @Override
    public List<DocumentDemandResponse> getDocuments(String refDemand) {
        List<DocumentDemandResponse> documentDemandResponses = new ArrayList<>();
        List<Document> documents = documentService.findByRefObject(refDemand);
        for (Document document : documents) {
            documentDemandResponses.add(attributionsPrixMapper.documentToDocumentDemandResponse(document));
        }
        return documentDemandResponses;
    }

    @Override
    public List<DocumentDemandResponse> getDocumentsPersonal(String refArtistAccount) {
        List<DocumentDemandResponse> documentDemandResponses = new ArrayList<>();
        List<Document> documents = documentService.findByRefObject(refArtistAccount);
        for (Document document : documents) {
            documentDemandResponses.add(attributionsPrixMapper.documentToDocumentDemandResponse(document));
        }
        return documentDemandResponses;
    }

    @Override
    public List<AwardHassan2Response> addAwardHassan2(AwardRequest awardHassan2Request) {
         AwardHassan2 awardHassan2=attributionsPrixMapper.awardRequestToAwardHassan2(awardHassan2Request);
         awardHassan2.setPrice(priceService.getPriceByRef(awardHassan2Request.getPrice()));
         awardHassan2Service.createOrUpdate(awardHassan2);
         return this.findAllAwards();
    }

    @Override
    public List<AwardHassan2Response> updateAwardHassan2(AwardHassan2Response request) {
        AwardHassan2 awardHassan2=awardHassan2Service.findAwardHassan2ByRefAwardHassan2(request.getRefAwardHassan2());
        awardHassan2=awardHassan2Service.createOrUpdate(attributionsPrixMapper.updateAwardHassan2ResponseToAwardHassan2(request,awardHassan2));
        return this.findAllAwards();
    }

    @Override
    public AwardHassan2Response getAwardHassan2ByRef(String refAward) {
        return attributionsPrixMapper.awardHassa2ToAwardHassan2Response(awardHassan2Service.findAwardHassan2ByRefAwardHassan2(refAward));
    }

    @Override
    public List<AwardHassan2Response> findAllAwards() {
        List<AwardHassan2> listAwardHassan2=awardHassan2Service.findAllAwards();
        List<AwardHassan2Response> awardHassan2ResponseList=new ArrayList<>();
        for (AwardHassan2 awardHassan2:listAwardHassan2) {
           awardHassan2ResponseList.add(attributionsPrixMapper.awardHassa2ToAwardHassan2Response(awardHassan2));
        }
        return awardHassan2ResponseList;
    }

    @Override
    public List<AwardHassan2Response> deleteAwardHassan2(String refAward) {
        awardHassan2Service.deleteAwardHassan2(awardHassan2Service.findAwardHassan2ByRefAwardHassan2(refAward));
        return this.findAllAwards();
    }

    @Override
    public List<Demand> getDemandsUserLogged(String refUser) {
        Account account=accountService.findAccountByRefAccount(refUser);
        return demandAHService.findDemandByPersonConnected(account);
    }

    @Override
    public Page<OwnerHandWritten> getOwners(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy , String cin, String firstName, String lastName, String firstNameAr, String lastNameAr,
                                            String gender, String email, String phone, String rib) {
        OwnerHandWrittenPage ownerHandWrittenPage=new OwnerHandWrittenPage();
        ownerHandWrittenPage.setPageNumber(pageNumber);
        ownerHandWrittenPage.setPageSize(pageSize);
        ownerHandWrittenPage.setSortDirection(sortDirection);
        ownerHandWrittenPage.setSortBy(sortBy);

        OwnerHandWrittenSearchCriteria ownerHandWrittenSearchCriteria=new OwnerHandWrittenSearchCriteria();
        ownerHandWrittenSearchCriteria.setCin(cin);
        ownerHandWrittenSearchCriteria.setFirstName(firstName);
        ownerHandWrittenSearchCriteria.setLastName(lastName);
        ownerHandWrittenSearchCriteria.setFirstNameAr(firstNameAr);
        ownerHandWrittenSearchCriteria.setLastNameAr(lastNameAr);
        ownerHandWrittenSearchCriteria.setEmail(email);
        ownerHandWrittenSearchCriteria.setGender(gender);
        ownerHandWrittenSearchCriteria.setPhone(phone);
        ownerHandWrittenSearchCriteria.setRib(rib);
        return ownerHandWrittenService.getOwnerHandWrittens(ownerHandWrittenPage,ownerHandWrittenSearchCriteria);
    }

    @Override
    public Page<Demand> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType) {
        DemandPage demandPage=new DemandPage();
        demandPage.setPageNumber(pageNumber);
        demandPage.setPageSize(pageSize);
        demandPage.setSortBy(sortBy);
        demandPage.setSortDirection(sortDirection);
        DemandAwardSearchCriteria demandAwardHassan2SearchCriteria=new DemandAwardSearchCriteria();
        demandAwardHassan2SearchCriteria.setStatus(status);
        demandAwardHassan2SearchCriteria.setAwardType(awardType);
        demandAwardHassan2SearchCriteria.setDemandOwnerFirstName(demandOwnerFirstName);
        demandAwardHassan2SearchCriteria.setDemandOwnerLastName(demandOwnerLastName);
        demandAwardHassan2SearchCriteria.setDecision_date(decision_date);
        return demandService.getDemandsAwardHassan2(demandPage,demandAwardHassan2SearchCriteria);
    }

    @Override
    public Void saveOwnerExcel(MultipartFile file) {
        ownerHandWrittenService.save(file);
        System.out.println("uploaded the file successfully:" + file.getOriginalFilename());
        return null;
    }

    @Override
    public ByteArrayInputStream exportArtistData() {
        List<OwnerHandWritten> owner= ownerHandWrittenService.getAllOwners();
        ByteArrayInputStream in=ownerHandWrittenService.exportArtistData(owner);
        return in;
    }

    @Override
    public Void saveManuscriptInformationExcel(MultipartFile file) {
        manuscriptInformationService.save(file);
        System.out.println("uploaded the file successfully:" + file.getOriginalFilename());
        return null;
    }

    @Override
    public ByteArrayInputStream exportManuscriptData() {
        List<ManuscriptInformation> manuscriptInformations=manuscriptInformationService.getAllManuscriptInformation();
        ByteArrayInputStream in=manuscriptInformationService.exportManuscriptData(manuscriptInformations);
        return in;
    }

    @Override
    public ByteArrayInputStream exportDemandData() {
        List<Demand> demands=demandAHService.demandsAwardHassan2();
        ByteArrayInputStream in=demandAHService.exportDemandAwardHassan2Data(demands);
        return in;
    }

    @Override
    public OwnerPersonalInfoResponse getOwnerPersonalInformation(String email) {
        Account ownerAccount=accountService.findAccountByEmail(email).get();
        ArtistAccount owner=artistAccountService.findArtistByCin(ownerAccount.getCin());
        if(owner==null){
            OwnerPersonalInfoResponse ownerPersonalInfoResponse=attributionsPrixMapper.accountToOwnerPersonalInfoResponse(ownerAccount);
            ownerPersonalInfoResponse.setFirstName(ownerAccount.getFirstname());
            ownerPersonalInfoResponse.setLastName(ownerAccount.getLastname());
            return ownerPersonalInfoResponse;
        }
        else{
            return attributionsPrixMapper.artistAccountToOwnerPersonalInfoResponse(owner);
        }

    }

    @Override
    public String createOrUpdatePersonalInformation(OwnerPersonalInfoResponse ownerPersonalInfoResponse) {

        if(ownerPersonalInfoResponse.getRefArtistAccount()!=null){
            ArtistAccount artistAccountFound=artistAccountService.findArtistAccountByRef(ownerPersonalInfoResponse.getRefArtistAccount()).get();
            ArtistAccount artistAccount=attributionsPrixMapper.updateOwnerPersonalInfoResponseToArtistAccount(ownerPersonalInfoResponse,artistAccountFound);
            artistAccount=artistAccountService.updateArtistAccount(artistAccount);
            return artistAccount.getAccount().getRefAccount();

       }else{
           ArtistAccount artistAccount=attributionsPrixMapper.ownerPersonalInfoResponseToArtistAccount(ownerPersonalInfoResponse);
            artistAccount.setAccount(accountService.findAccountByCin(ownerPersonalInfoResponse.getCin()));
           artistAccount=artistAccountService.createNewArtistAccount(artistAccount);
           return artistAccount.getAccount().getRefAccount();
       }

    }

    @Override
    public DemandAwardResponse addDemand(DemandPriceRequest request) {
        DemandPrice demandPrice=attributionsPrixMapper.demandPriceRequestToDemandPrice(request);
        demandPrice.setAccountOwner(artistAccountService.findArtistAccountByRef(request.getAccountOwner()).get());
        demandPrice.setAwardCategories(awardCategoriesService.getAwardCategory(request.getAwardCategories()));
        demandPrice.setKey("AHASSAN2");
        demandPrice=demandPriceService.createOrUpdateDemandPrice(demandPrice);
        DemandAwardResponse demandAwardResponse=new DemandAwardResponse();
        demandAwardResponse.setRefDemand(demandPrice.getRefDemand());
        demandAwardResponse.setRefOwner(request.getAccountOwner());
        return demandAwardResponse;
    }

    @Override
    public ManuscriptInfromationAddingResponse createManuscriptInformation(ManuscriptInformationRequest request) {
        var manuscriptInformation = attributionsPrixMapper.manuscriptInformationRequestToManuscriptInformation(request);
        manuscriptInformation.setOwner(artistAccountService.findArtistAccountByRef(request.getOwner()).get());
        manuscriptInformation.setDemand(demandPriceService.findDemandPriceByRef(request.getDemand()));
        FontType fontType = fontTypeService.findFontTypeByRefFontType(request.getFontType());
       // manuscriptInformation.setFontType(fontType);
        ManuscriptType manuscriptType = manuscriptTypeService.findManuscriptTypeByRefManuscriptType(request.getType());
        manuscriptInformation.setType(manuscriptType);
        manuscriptInformationService.createOrUpdatemanuscript(manuscriptInformation);
        ManuscriptInfromationAddingResponse manuscriptInformationResponse = new ManuscriptInfromationAddingResponse();
        manuscriptInformationResponse.setRefDemand(request.getDemand());
        return manuscriptInformationResponse;
    }

    @Override
    public DemandPriceHassan2Response getDemandPriceByRef(String refDemand) {
        DemandPrice demandPrice=demandPriceService.findDemandPriceByRef(refDemand);
        ManuscriptInformation manuscriptInformation=manuscriptInformationService.findManuscriptInformationByDemand(demandPrice);
        DemandPriceHassan2Response demandPriceHassan2Response=new DemandPriceHassan2Response();
        demandPriceHassan2Response.setRefDemand(refDemand);
        demandPriceHassan2Response.setComment(demandPrice.getComment());
        demandPriceHassan2Response.setDecision_date(demandPrice.getDecision_date());
        demandPriceHassan2Response.setStatus(demandPrice.getStatus());
        demandPriceHassan2Response.setAwardCategories(attributionsPrixMapper.awardCategoryToAwardCategoriesResponse(demandPrice.getAwardCategories()));
        List<AwardCategoriesResponse> awardCategoriesResponseList=new ArrayList<>();

        for (AwardCategories awardCategory:demandPrice.getAwardCategories().getAwardType().getAwardCategories()) {
            awardCategoriesResponseList.add(attributionsPrixMapper.awardCategoryToAwardCategoriesResponse(awardCategory));
        }
        demandPriceHassan2Response.setListAwardCategories(awardCategoriesResponseList);
        demandPriceHassan2Response.setManuscriptInformation(attributionsPrixMapper.manuscriptInformationToManuscriptInformationResponse(manuscriptInformation));
        return demandPriceHassan2Response;
    }



    @Override
    public DemandPriceHassan2Response updateDemandPrice(DemandPriceHassan2Response request) {

        DemandPrice demandPriceFound=demandPriceService.findDemandPriceByRef(request.getRefDemand());
        demandPriceFound.setComment(request.getComment());
        demandPriceFound.setStatus(request.getStatus());
        demandPriceFound.setDecision_date(request.getDecision_date());
        demandPriceFound.setComment(request.getComment());

        if(request.getAwardCategories().getRefAwardCategory().equals(demandPriceFound.getAwardCategories().getRefAwardCategory())==FALSE){
            demandPriceFound.setAwardCategories(awardCategoriesService.getAwardCategory(request.getAwardCategories().getRefAwardCategory()));
        }
        demandPriceFound=demandPriceService.createOrUpdateDemandPrice(demandPriceFound);

        ManuscriptInformation manuscriptInformationFound=manuscriptInformationService.findManuscriptInformationByRef(request.getManuscriptInformation().getRefManuscriptInformation());
        ManuscriptInformation manuscriptInformation=attributionsPrixMapper.updateManuscriptInformationResponseToManuscriptInformation(request.getManuscriptInformation(),manuscriptInformationFound);
        FontType fontType = fontTypeService.findFontTypeByRefFontType(request.getManuscriptInformation().getFontType().getRefFontType());
        //manuscriptInformation.setFontType(fontType);
        ManuscriptType manuscriptType = manuscriptTypeService.findManuscriptTypeByRefManuscriptType(request.getManuscriptInformation().getType().getRefManuscriptType());
        manuscriptInformation.setType(manuscriptType);
        manuscriptInformation.setDemand(demandPriceFound);
        manuscriptInformation.setOwner(demandPriceFound.getAccountOwner());
        manuscriptInformation = manuscriptInformationService.update(manuscriptInformation);
        return this.getDemandPriceByRef(request.getRefDemand());
    }

    @Override
    public Void deleteDemandPrice(String refAward) {
        DemandPrice demandPrice=demandPriceService.findDemandPriceByRef(refAward);
        demandPriceService.deleteDemandPrice(demandPrice);
        return null;
    }

    @Override
    public PageableResponse<DemandPriceHassan2ListResponse> findAllDemands(Pageable pageable) {
        Page<DemandPrice> page;
        page = demandPriceService.findDemandPageableAwardHassan2(pageable);
        var pageResponse = page.map(attributionsPrixMapper::demandPriceToDemandPriceHassan2ListResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public List<AwardCategoriesLestingResponse> getAwardCategoriesHassan2(String refAwardType) {

        List<AwardCategories> awardCategories=awardTypeService.findAwardTypeByRef(refAwardType).getAwardCategories();
        List<AwardCategoriesLestingResponse> awardCategoriesLestingResponses=new ArrayList<>();
        for (AwardCategories awardCategory:awardCategories) {
            AwardCategoriesLestingResponse awardCategoriesLestingResponse=new AwardCategoriesLestingResponse();
            awardCategoriesLestingResponse.setRefAwardCategory(awardCategory.getRefAwardCategory());
            awardCategoriesLestingResponse.setTitleAr(awardCategory.getTitleAr());
            awardCategoriesLestingResponse.setTitleFr(awardCategory.getTitleFr());
            awardCategoriesLestingResponse.setAwardType(attributionsPrixMapper.awardTypeToAwardTypeResponse(awardCategory.getAwardType()));
            awardCategoriesLestingResponses.add(awardCategoriesLestingResponse);
        }
        return awardCategoriesLestingResponses;
    }

    @Override
    public List<ManuscriptType> getAllManuscriptType() {
        return manuscriptTypeService.findAllManuscriptType();
    }

    @Override
    public List<PriceHassa2Response> getPriceDemandList() {

        List<DemandPrice> demandPriceList=demandPriceService.findAllDemandAwardHassa2();
        List<PriceHassa2Response> priceHassa2ResponseList=new ArrayList<>();

        for (DemandPrice demandPrice:demandPriceList) {
            PriceHassa2Response priceHassa2Response=new PriceHassa2Response();
            priceHassa2Response.setRefDemand(demandPrice.getRefDemand());
            priceHassa2Response.setComment(demandPrice.getComment());
            priceHassa2Response.setStatus(demandPrice.getStatus());
            priceHassa2Response.setDecision_date(demandPrice.getDecision_date());
            priceHassa2Response.setAwardCategories(attributionsPrixMapper.awardCategoryToAwardCategoriesResponse(demandPrice.getAwardCategories()));
            priceHassa2Response.setOwnerPersonalInfoResponse(attributionsPrixMapper.artistAccountToOwnerPersonalInfoResponse(demandPrice.getAccountOwner()));
            priceHassa2ResponseList.add(priceHassa2Response);
        }
        return priceHassa2ResponseList;
    }

}


