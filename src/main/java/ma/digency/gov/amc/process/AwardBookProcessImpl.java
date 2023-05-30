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
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


@Service
@RequiredArgsConstructor
public class AwardBookProcessImpl implements AwardBookProcess {

    private final ArtistAccountService artistAccountService;

    private final AwardBookService awardBookService;

    private final DemandAHService demandService;

    private final BookPriceService bookService;

    private final AwardObtainedService awardObtainedService;

    private final DocumentService documentService;

    private final DomainService domainService;

    private final AttributionsPrixMapper attributionsPrixMapper;

    private final PriceService priceService;

    private final AccountService accountService;

    private final AwardCategoriesService awardCategoriesService;

    private final DemandPriceService demandPriceService;

    private final BookPriceService bookPriceService;

    private final AwardTypeService awardTypeService;

    @Override
    public OwnerPersonalInfoResponse getWriter(String refAccount) throws Exception {
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
    public String createArtist(OwnerPersonalInfoResponse artistPersonalInfoResponse) throws Exception {
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
    public DemandAwardBookResponse createDemand(DemandPriceBookRequest request) throws Exception {
        try{
            List<AwardObtained> awardObtaineds=awardObtainedService.findAwardObtainedByRefArtist(request.getAccountOwner());
            Boolean had=FALSE;
            Date dt=new Date();
            int year=dt.getYear()+1900;

            for (AwardObtained awardObtained:awardObtaineds) {
                if(year-1== awardObtained.getYear() || year-2== awardObtained.getYear() || year-3== awardObtained.getYear()){
                    had=TRUE;
                }
            }
            if(had==FALSE){
                DemandPrice demandPrice=new DemandPrice();
                demandPrice.setComment(request.getComment());
                demandPrice.setStatus("PENDING");
                demandPrice.setAccountOwner(artistAccountService.findArtistAccountByRef(request.getAccountOwner()).get());
                demandPrice.setAwardCategories(awardCategoriesService.getAwardCategory(request.getAwardCategories()));
                demandPrice.setKey("ABOOK");
                demandPrice.setBookPrice(bookPriceService.findBookByRef(request.getBookPrice()));
                demandPrice = demandPriceService.createOrUpdateDemandPrice(demandPrice);
                DemandAwardBookResponse demandAwardBookResponse = new DemandAwardBookResponse();
                demandAwardBookResponse.setRefDemand(demandPrice.getRefDemand());
                demandAwardBookResponse.setRefArtist(request.getAccountOwner());
                return demandAwardBookResponse;
            }
            throw new Exception();
        }catch (Exception e){
            throw new Exception("sorry, you cannot apply until 3 years from the last award you took" + e.getMessage());
        }
    }

    @Override
    public BookPrice createBook(PublicationPriceRequest request) {
        BookPrice book = attributionsPrixMapper.publicationPriceRequestToBookPrice(request);
        bookService.createOrUpdateBook(book);
        return book;
    }

    @Override
    public List<PublicationRequest> getAllPublication(String author) {
        List<BookPrice> books = bookService.findBookByAuthor(author);
        List<PublicationRequest> bookRequests = new ArrayList<>();
        for (BookPrice book : books) {
            bookRequests.add(attributionsPrixMapper.bookToPublicationRequest(book));
        }
        return bookRequests;
    }

    @Override
    public List<PublicationRequest> addPublication(PublicationPriceRequest request) {
        BookPrice book = attributionsPrixMapper.publicationPriceRequestToBookPrice(request);
        bookService.createOrUpdateBook(book);
        return this.getAllPublication(request.getAuthor());
    }

    @Override
    public List<AwardObtainedResponse> getAwards(String author) {
        List<AwardObtained> awardObtaineds = awardObtainedService.findAwardObtainedByRefArtist(author);
        List<AwardObtainedResponse> awardObtainedResponses = new ArrayList<>();
        for (AwardObtained award : awardObtaineds) {
            awardObtainedResponses.add(attributionsPrixMapper.awardToAwardObtainedResponse(award));
        }
        return awardObtainedResponses;
    }

    @Override
    public List<AwardObtainedResponse> addAward(AwardObtainedRequest request) {
        AwardObtained awardObtained = attributionsPrixMapper.awardObtainedRequestToAwardObtained(request);
        awardObtained = awardObtainedService.createOrUpdateAwardObtained(awardObtained);
        return this.getAwards(request.getArtist());
    }

    @Override
    public List<AwardObtainedResponse> deleteAward(String refAwardObtained) {
        AwardObtained awardObtained = awardObtainedService.findAwardObtainedByRef(refAwardObtained);
        awardObtainedService.deleteAwardObtained(refAwardObtained);
        return this.getAwards(awardObtained.getArtist());
    }

    @Override
    public AwardObtainedResponse getAward(String refAward) {
        return attributionsPrixMapper.awardToAwardObtainedResponse(awardObtainedService.findAwardObtainedByRef(refAward));
    }

    @Override
    public List<AwardObtainedResponse> updateAward(AwardObtainedResponse request) {
        AwardObtained awardObtainedFound = awardObtainedService.findAwardObtainedByRef(request.getReAwardObtained());
        awardObtainedService.createOrUpdateAwardObtained(attributionsPrixMapper.updateAwardObtainedResponseToAwardObtained(request, awardObtainedFound));
        return this.getAwards(request.getArtist());
    }

    @Override
    public void deleteDemand(String refDemand) {
        documentService.deleteDocument(refDemand);
        demandPriceService.deleteDemandPrice(demandPriceService.findDemandPriceByRef(refDemand));
    }

    @Override
    public PageableResponse<DemandAwardBookListResponse> findAllDemand(Pageable pageable) {
        Page<Demand> page;
        page = demandService.findDemandPageableAwardBook(pageable);
        var pageResponse = page.map(attributionsPrixMapper::demandToDemandAwardBookListResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public List<PublicationRequest> updatePublication(PublicationRequest bookRequest) {
        BookPrice book = bookService.findBookByRef(bookRequest.getRefBook());
        //book.setDomain(domainService.findDomainByRef(bookRequest.getDomain().getRefDomain()));
        bookService.createOrUpdateBook(attributionsPrixMapper.updatePublicationRequestToBook(bookRequest, book));
        return this.getAllPublication(bookRequest.getAuthor());
    }

    @Override
    public List<PublicationRequest> deletePublication(String refPublication) {
        BookPrice book = bookService.findBookByRef(refPublication);
        bookService.deleteBook(refPublication);
        return this.getAllPublication(book.getAuthor());
    }

    @Override
    public DemandPriceBookResponse getDemand(String refDemand) {
        DemandPrice demandPrice=demandPriceService.findDemandPriceByRef(refDemand);
        DemandPriceBookResponse demandPriceBookResponse=new DemandPriceBookResponse();
        demandPriceBookResponse.setRefDemand(refDemand);
        demandPriceBookResponse.setComment(demandPrice.getComment());
        demandPriceBookResponse.setDecision_date(demandPrice.getDecision_date());
        demandPriceBookResponse.setStatus(demandPrice.getStatus());
        demandPriceBookResponse.setAwardCategories(attributionsPrixMapper.awardCategoryToAwardCategoriesResponse(demandPrice.getAwardCategories()));
        List<AwardCategoriesResponse> awardCategoriesResponseList=new ArrayList<>();

        for (AwardCategories awardCategory:demandPrice.getAwardCategories().getAwardType().getAwardCategories()) {
            awardCategoriesResponseList.add(attributionsPrixMapper.awardCategoryToAwardCategoriesResponse(awardCategory));
        }
        demandPriceBookResponse.setListAwardCategories(awardCategoriesResponseList);
        demandPriceBookResponse.setBookPrice(demandPrice.getBookPrice());
        return demandPriceBookResponse;
    }

    @Override
    public String updateDemand(DemandPriceBookResponse request) {
        DemandPrice demandPriceFound=demandPriceService.findDemandPriceByRef(request.getRefDemand());
        demandPriceFound.setComment(request.getComment());
        demandPriceFound.setStatus(request.getStatus());
        demandPriceFound.setDecision_date(request.getDecision_date());
        demandPriceFound.setComment(request.getComment());
        if(request.getAwardCategories().getRefAwardCategory().equals(demandPriceFound.getAwardCategories().getRefAwardCategory())==FALSE){
            demandPriceFound.setAwardCategories(awardCategoriesService.getAwardCategory(request.getAwardCategories().getRefAwardCategory()));
        }
        demandPriceFound=demandPriceService.createOrUpdateDemandPrice(demandPriceFound);
        this.updateBook(attributionsPrixMapper.bookToPublicationRequest(request.getBookPrice()));
        return request.getRefDemand();
    }

    @Override
    public PublicationRequest getPublication(String refBook) {
        return attributionsPrixMapper.bookToPublicationRequest(bookService.findBookByRef(refBook));
    }

    @Override
    public String updateBook(PublicationRequest request) {
        BookPrice book = bookService.findBookByRef(request.getRefBook());
        //book.setDomain(domainService.findDomainByRef(request.getDomain().getRefDomain()));
        bookService.createOrUpdateBook(attributionsPrixMapper.updatePublicationRequestToBook(request, book));
        return null;
    }

    @Override
    public PublicationRequest getBookDemand(String refDemand) {
        return attributionsPrixMapper.bookToPublicationRequest(demandPriceService.findDemandPriceByRef(refDemand).getBookPrice());
    }

    @Override
    public List<AwardBookResponse> addAwardBook(AwardRequest request) {
        AwardBook awardBook=attributionsPrixMapper.awardRequestToAwardBook(request);
        awardBook.setPrice(priceService.getPriceByRef(request.getPrice()));
        awardBookService.createOrUpdate(awardBook);
        return this.getAllAwards();
    }

    @Override
    public List<AwardBookResponse> getAllAwards() {
        List<AwardBook> awardBookList=awardBookService.getAllAwards();
        List<AwardBookResponse> awardBookResponses=new ArrayList<>();
        for (AwardBook awardBook:awardBookList) {
            awardBookResponses.add(attributionsPrixMapper.awardBookToAwardBookResponse(awardBook));
        }
        return awardBookResponses;
    }

    @Override
    public AwardBookResponse getAwardBookByRef(String refAward) {
        return attributionsPrixMapper.awardBookToAwardBookResponse(awardBookService.findAwardBookByRefAwardBook(refAward));
    }

    @Override
    public List<AwardBookResponse> updateAwardBook(AwardBookResponse request) {
        AwardBook awardBook=awardBookService.findAwardBookByRefAwardBook(request.getRefAwardBook());
        awardBookService.createOrUpdate(attributionsPrixMapper.updateAwardBookResponseToAwardBook(request,awardBook));
        return this.getAllAwards();
    }

    @Override
    public List<AwardBookResponse> deleteAwardBook(String refAward) {
        awardBookService.deleteAwardBook(awardBookService.findAwardBookByRefAwardBook(refAward));
        return this.getAllAwards();
    }

    @Override
    public List<Demand> getDemandsUserLogged(String refUser) {
        Account account=accountService.findAccountByRefAccount(refUser);
        return demandService.findDemandByPersonConnected(account);
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
        return demandService.getDemandsAwardBook(demandPage,demandAwardBookSearchCriteria);

    }

    @Override
    public ByteArrayInputStream exportDemandData() {
        List<Demand> demands=demandService.demandsAwardBook();
        ByteArrayInputStream in=demandService.exportDemandAwardBookData(demands);
        return in;
    }

    @Override
    public Void saveBooksExcel(MultipartFile multipartFile) {
        bookService.save(multipartFile);
        System.out.println("uploaded the file successfully:" + multipartFile.getOriginalFilename());
        return null;
    }

    @Override
    public ByteArrayInputStream exportPublicationsData() {
        List<BookPrice> books=bookService.getAllBookPrice();
        ByteArrayInputStream in=bookService.exportBookPriceData(books);
        return in;
    }

    @Override
    public Void saveAwardsExcel(MultipartFile multipartFile) {
        awardObtainedService.save(multipartFile);
        System.out.println("uploaded the file successfully:" + multipartFile.getOriginalFilename());
        return null;
    }

    @Override
    public ByteArrayInputStream exportAwardObtainedData() {
        List<AwardObtained> awardObtaineds=awardObtainedService.getAllAwardObtained();
        ByteArrayInputStream in=awardObtainedService.exportAwardObtainedData(awardObtaineds);
        return in;
    }

    @Override
    public PageableResponse<DemandPriceBookListResponse> findAllDemands(Pageable pageable) {
        Page<DemandPrice> page;
        page = demandPriceService.findDemandPageableAwardBook(pageable);
        var pageResponse = page.map(attributionsPrixMapper::demandPriceToDemandPriceBookListResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public List<AwardCategoriesLestingResponse> getAwardCategoriesBook(String refAward) {
        List<AwardCategories> awardCategories=awardTypeService.findAwardTypeByRef(refAward).getAwardCategories();
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
}


