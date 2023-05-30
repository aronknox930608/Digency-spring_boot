package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.library.LibraryResponse;
import ma.digency.gov.amc.dto.participant.*;
import ma.digency.gov.amc.dto.searchParticipant.DistributorSearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.LibrarySearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.PrinterSearchCriteria;
import ma.digency.gov.amc.mapper.ParticipantMapper;
import ma.digency.gov.amc.mapper.PrinterMapper;
import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.participant.*;
import ma.digency.gov.amc.repository.entity.printer.Printer;
import ma.digency.gov.amc.repository.entity.search.DistributorPage;
import ma.digency.gov.amc.repository.entity.search.LibraryPage;
import ma.digency.gov.amc.repository.entity.search.PrinterPage;
import ma.digency.gov.amc.service.ExcelDistributorService;
import ma.digency.gov.amc.service.ExcelPrinterService;
import ma.digency.gov.amc.service.participant.*;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantProcessImpl implements ParticipantProcess {

    private final PrinterService printerService;
    private final DistributorService distributorService;
    private final PrinterMapper printerMapper;
    private final ParticipantMapper participantMapper;
    private final NotificationProcess notificationProcess;
    private final ReferenceSequenceService referenceSequenceService;
    private final FacilitiesServicesService facilitiesServicesService;
    private final PublishingOtherProductService publishingOtherProductService;
    private final WritingLanguageService writingLanguageService;
    private final FieldsOfWritingService fieldsOfWritingService;
    private final BookPromotionFormService bookPromotionFormService;
    private final SellingPointsService sellingPointsService;
    private final ExcelPrinterService excelPrinterService;
    private final ExcelDistributorService excelDistributorService;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;


    @Override
    public PrinterResponse createPrinter(PrinterRequest printerRequest) {
        var printer = printerMapper.printerRequestToPrinter(printerRequest);
        return printerMapper.printerToPrinterResponse(printerService.createOrUpdatePrinter(printer));
    }

    @Override
    public PrinterResponse updatePrinter(PrinterResponse request) {
        Printer find = printerService.findPrinterByRefPrinter(request.getRefPrinter());
        printerMapper.updatePrinterFromPrinterResponse(request,find);
        return printerMapper.printerToPrinterResponse(printerService.createOrUpdatePrinter(find));
    }

    @Override
    public void deletePrinter(String refPrinter) {
       printerService.deletePrinter(refPrinter);
    }

    @Override
    public PrinterResponse findPrinter(String refPrinter) {
        Printer printer = printerService.findPrinterByRefPrinter(refPrinter);
        return printerMapper.printerToPrinterResponse(printer);
    }

    @Override
    public List<PrinterResponse> findAllPrinters() {
        return printerService.findAllPrinters().stream()
                .map(printerMapper::printerToPrinterResponse).collect(Collectors.toList());
    }

    @Override
    public PageableResponse<PrinterResponse> findAllPrinters(Pageable pageable) {
        Page<Printer> page ;
        page = printerService.findPrintersPageable(pageable);

        var pageResponse = page.map(printerMapper::printerToPrinterResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public Page<Printer> searchPrinter(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String businessName, String lineOfBusiness, String ownerName, String businessRegisterNumber) {

        PrinterPage printerPage=new PrinterPage();

        printerPage.setPageNumber(pageNumber);

        printerPage.setPageSize(pageSize);

        printerPage.setSortBy(sortBy);

        printerPage.setSortDirection(sortDirection);


        PrinterSearchCriteria printerSearchCriteria=new PrinterSearchCriteria();


        printerSearchCriteria.setBusinessName(businessName);

        printerSearchCriteria.setLineOfBusiness(lineOfBusiness);

        printerSearchCriteria.setOwnerName(ownerName);

        printerSearchCriteria.setBusinessRegisterNumber(businessRegisterNumber);



        return printerService.getPrinters(printerPage,printerSearchCriteria);
    }

    @Override
    public ByteArrayInputStream exportPrinterData() {
        List<PrinterResponse> printer= excelPrinterService.getAllPrinters();

        ByteArrayInputStream inputStream=excelPrinterService.exportPrinterData(printer);

        return inputStream;
    }

    @Override
    public DistributorResponse createDistributor(DistributorRequest distributorRequest) {
        var distributor = participantMapper.distributorRequestToDistributor(distributorRequest);
        return participantMapper.distributorToDistributorResponse(distributorService.createOrUpdateDistributor(distributor));
    }

    @Override
    public DistributorResponse updateDistributor(DistributorResponse request) {
        Distributor find = distributorService.findDistributorByRefDistributor(request.getRefDistributor());
        participantMapper.updateDistributorFromDistributorResponse(request,find);
        return participantMapper.distributorToDistributorResponse(distributorService.createOrUpdateDistributor(find));
    }

    @Override
    public void deleteDistributor(String refDistributor) {
        distributorService.deleteDistributor(refDistributor);
    }

    @Override
    public DistributorResponse findDistributor(String refDistributor) {
        Distributor distributor = distributorService.findDistributorByRefDistributor(refDistributor);
        return participantMapper.distributorToDistributorResponse(distributor);
    }

    @Override
    public List<DistributorResponse> findAllDistributors() {
        return distributorService.findAllDistributors().stream()
                .map(participantMapper::distributorToDistributorResponse).collect(Collectors.toList());
    }

    @Override
    public PageableResponse<DistributorResponse> findAllDistributors(Pageable pageable) {
        Page<Distributor> page ;
        page = distributorService.findDistributorsPageable(pageable);

        var pageResponse = page.map(participantMapper::distributorToDistributorResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public Page<Distributor> searchDistributors(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String businessName, String lineOfBusiness, String ownerName, String businessRegisterNumber) {
        DistributorPage distributorPage=new DistributorPage();

        distributorPage.setPageNumber(pageNumber);

        distributorPage.setPageSize(pageSize);

        distributorPage.setSortBy(sortBy);

        distributorPage.setSortDirection(sortDirection);


        DistributorSearchCriteria distributorSearchCriteria=new DistributorSearchCriteria();


        distributorSearchCriteria.setBusinessName(businessName);

        distributorSearchCriteria.setLineOfBusiness(lineOfBusiness);

        distributorSearchCriteria.setOwnerName(ownerName);

        distributorSearchCriteria.setBusinessRegisterNumber(businessRegisterNumber);



        return distributorService.getDistributors(distributorPage,distributorSearchCriteria);
    }

    @Override
    public ByteArrayInputStream exportDistributorsData() {
        List<DistributorResponse> distributor= excelDistributorService.getAllDistributors();

        ByteArrayInputStream inputStream=excelDistributorService.exportDistributorsData(distributor);

        return inputStream;
    }

    @Override
    public FacilitiesServicesResponse createFacilitiesServices(FacilitiesServicesRequest facilitiesServicesRequest) {
        var facilitiesServices = participantMapper.facilitiesServicesRequestToFacilitiesServices(facilitiesServicesRequest);
        return participantMapper.facilitiesServicesToFacilitiesServicesResponse(facilitiesServicesService.createOrUpdateFacilitiesServices(facilitiesServices));
    }

    @Override
    public FacilitiesServicesResponse updateFacilitiesServices(FacilitiesServicesResponse request) {
        FacilitiesServices find = facilitiesServicesService.findFacilitiesServicesByRefFacilitiesServices(request.getRefFacilitiesServices());
        participantMapper.updateFacilitiesServicesFromFacilitiesServicesResponse(request,find);
        return participantMapper.facilitiesServicesToFacilitiesServicesResponse(facilitiesServicesService.createOrUpdateFacilitiesServices(find));
    }

    @Override
    public void deleteFacilitiesServices(String refFacilitiesServices) {
        facilitiesServicesService.deleteFacilitiesServices(refFacilitiesServices);
    }

    @Override
    public FacilitiesServicesResponse findFacilitiesServices(String refFacilitiesServices) {
        FacilitiesServices  facilitiesServices =  facilitiesServicesService.findFacilitiesServicesByRefFacilitiesServices(refFacilitiesServices);
        return participantMapper.facilitiesServicesToFacilitiesServicesResponse(facilitiesServices);
    }

    @Override
    public List<FacilitiesServicesResponse> findAllFacilitiesServices() {
        return facilitiesServicesService.findAllFacilitiesServices().stream()
                .map(participantMapper::facilitiesServicesToFacilitiesServicesResponse).collect(Collectors.toList());
    }

    @Override
    public PublishingOtherProductResponse createPublishingOtherProduct(PublishingOtherProductRequest publishingOtherProductRequest) {
        var publishingOtherProduct = participantMapper.publishingOtherProductRequestToPublishingOtherProduct(publishingOtherProductRequest);
        return participantMapper.publishingOtherProductToPublishingOtherProductResponse(publishingOtherProductService.createOrUpdatePublishingOtherProduct(publishingOtherProduct));
    }

    @Override
    public PublishingOtherProductResponse updatePublishingOtherProduct(PublishingOtherProductResponse request) {
        PublishingOtherProduct find = publishingOtherProductService.findPublishingOtherProductByRefPublishingOtherProduct(request.getRefPublishingOtherProduct());
        participantMapper.updatePublishingOtherProductFromPublishingOtherProductResponse(request,find);
        return participantMapper.publishingOtherProductToPublishingOtherProductResponse(publishingOtherProductService.createOrUpdatePublishingOtherProduct(find));
    }

    @Override
    public void deletePublishingOtherProduct(String refPublishingOtherProduct) {
        publishingOtherProductService.deletePublishingOtherProduct(refPublishingOtherProduct);
    }

    @Override
    public PublishingOtherProductResponse findPublishingOtherProduct(String refPublishingOtherProduct) {
        PublishingOtherProduct  publishingOtherProduct =  publishingOtherProductService.findPublishingOtherProductByRefPublishingOtherProduct(refPublishingOtherProduct);
        return participantMapper.publishingOtherProductToPublishingOtherProductResponse(publishingOtherProduct);
    }

    @Override
    public List<PublishingOtherProductResponse> findAllPublishingOtherProduct() {
        return publishingOtherProductService.findAllPublishingOtherProduct().stream()
                .map(participantMapper::publishingOtherProductToPublishingOtherProductResponse).collect(Collectors.toList());
    }

    @Override
    public WritingLanguageResponse createWritingLanguage(WritingLanguageRequest writingLanguageRequest) {
        var writingLanguage = participantMapper.writingLanguageRequestToWritingLanguage(writingLanguageRequest);
        return participantMapper.writingLanguageToWritingLanguageResponse(writingLanguageService.createOrUpdateWritingLanguage(writingLanguage));
    }

    @Override
    public WritingLanguageResponse updateWritingLanguage(WritingLanguageResponse request) {
        WritingLanguage find = writingLanguageService.findWritingLanguageByRefWritingLanguage(request.getRefWritingLanguage());
        participantMapper.updateWritingLanguageFromWritingLanguageResponse(request,find);
        return participantMapper.writingLanguageToWritingLanguageResponse(writingLanguageService.createOrUpdateWritingLanguage(find));
    }

    @Override
    public void deleteWritingLanguage(String refWritingLanguage) {
        writingLanguageService.deleteWritingLanguage(refWritingLanguage);
    }

    @Override
    public WritingLanguageResponse findWritingLanguage(String refWritingLanguage) {
        WritingLanguage writingLanguage =  writingLanguageService.findWritingLanguageByRefWritingLanguage(refWritingLanguage);
        return participantMapper.writingLanguageToWritingLanguageResponse(writingLanguage);
    }

    @Override
    public List<WritingLanguageResponse> findAllWritingLanguage() {
        return writingLanguageService.findAllWritingLanguage().stream()
                .map(participantMapper::writingLanguageToWritingLanguageResponse).collect(Collectors.toList());
    }

    @Override
    public FieldsOfWritingResponse createFieldsOfWriting(FieldsOfWritingRequest fieldsOfWritingRequest) {
        var fieldsOfWriting = participantMapper.fieldsOfWritingRequestToFieldsOfWriting(fieldsOfWritingRequest);
        return participantMapper.fieldsOfWritingToFieldsOfWritingResponse(fieldsOfWritingService.createOrUpdateFieldsOfWriting(fieldsOfWriting));
    }

    @Override
    public FieldsOfWritingResponse updateFieldsOfWriting(FieldsOfWritingResponse request) {
        FieldsOfWriting find = fieldsOfWritingService.findFieldsOfWritingByRefFieldsOfWriting(request.getRefFieldsOfWriting());
        participantMapper.updateFieldsOfWritingFromFieldsOfWritingResponse(request,find);
        return participantMapper.fieldsOfWritingToFieldsOfWritingResponse(fieldsOfWritingService.createOrUpdateFieldsOfWriting(find));
    }

    @Override
    public void deleteFieldsOfWriting(String refFieldsOfWriting) {
        fieldsOfWritingService.deleteFieldsOfWriting(refFieldsOfWriting);
    }

    @Override
    public FieldsOfWritingResponse findFieldsOfWriting(String refFieldsOfWriting) {
        FieldsOfWriting fieldsOfWriting =  fieldsOfWritingService.findFieldsOfWritingByRefFieldsOfWriting(refFieldsOfWriting);
        return participantMapper.fieldsOfWritingToFieldsOfWritingResponse(fieldsOfWriting);
    }

    @Override
    public List<FieldsOfWritingResponse> findAllFieldsOfWriting() {
        return fieldsOfWritingService.findAllFieldsOfWriting().stream()
                .map(participantMapper::fieldsOfWritingToFieldsOfWritingResponse).collect(Collectors.toList());
    }

    @Override
    public BookPromotionFormResponse createBookPromotionForm(BookPromotionFormRequest bookPromotionFormRequest) {
        var bookPromotionForm = participantMapper.bookPromotionFormRequestToBookPromotionForm(bookPromotionFormRequest);
        return participantMapper.bookPromotionFormToBookPromotionFormResponse(bookPromotionFormService.createOrUpdateBookPromotionForm(bookPromotionForm));
    }

    @Override
    public BookPromotionFormResponse updateBookPromotionForm(BookPromotionFormResponse request) {
        BookPromotionForm find = bookPromotionFormService.findBookPromotionFormByRefBookPromotionForm(request.getRefBookPromotionForm());
        participantMapper.updateBookPromotionFormFromBookPromotionFormResponse(request,find);
        return participantMapper.bookPromotionFormToBookPromotionFormResponse(bookPromotionFormService.createOrUpdateBookPromotionForm(find));
    }

    @Override
    public void deleteBookPromotionForm(String refBookPromotionForm) {
        bookPromotionFormService.deleteBookPromotionForm(refBookPromotionForm);
    }

    @Override
    public BookPromotionFormResponse findBookPromotionForm(String refBookPromotionForm) {
        BookPromotionForm bookPromotionForm =  bookPromotionFormService.findBookPromotionFormByRefBookPromotionForm(refBookPromotionForm);
        return participantMapper.bookPromotionFormToBookPromotionFormResponse(bookPromotionForm);
    }

    @Override
    public List<BookPromotionFormResponse> findAllBookPromotionForm() {
        return bookPromotionFormService.findAllBookPromotionForm().stream()
                .map(participantMapper::bookPromotionFormToBookPromotionFormResponse).collect(Collectors.toList());
    }

    @Override
    public SellingPointsResponse createSellingPoints(SellingPointsRequest sellingPointsRequest) {
        var sellingPoints = participantMapper.sellingPointsRequestToSellingPoints(sellingPointsRequest);
        return participantMapper.sellingPointsToSellingPointsResponse(sellingPointsService.createOrUpdateSellingPoints(sellingPoints));
    }

    @Override
    public SellingPointsResponse updateSellingPoints(SellingPointsResponse request) {
        SellingPoints find = sellingPointsService.findSellingPointsByRefSellingPoints(request.getRefSellingPoints());
        participantMapper.updateSellingPointsFromSellingPointsResponse(request,find);
        return participantMapper.sellingPointsToSellingPointsResponse(sellingPointsService.createOrUpdateSellingPoints(find));
    }

    @Override
    public void deleteSellingPoints(String refSellingPoints) {
        sellingPointsService.deleteSellingPoints(refSellingPoints);
    }

    @Override
    public SellingPointsResponse findSellingPoints(String refSellingPoints) {
        SellingPoints sellingPoints =  sellingPointsService.findSellingPointsByRefSellingPoints(refSellingPoints);
        return participantMapper.sellingPointsToSellingPointsResponse(sellingPoints);
    }

    @Override
    public List<SellingPointsResponse> findAllSellingPoints() {
        return sellingPointsService.findAllSellingPoints().stream()
                .map(participantMapper::sellingPointsToSellingPointsResponse).collect(Collectors.toList());
    }

}
