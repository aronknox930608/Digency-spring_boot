package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.participant.*;
import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.participant.WritingLanguage;
import ma.digency.gov.amc.repository.entity.printer.Printer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.ByteArrayInputStream;
import java.util.List;


public interface ParticipantProcess {

///////////// Printer///////////////

    PrinterResponse createPrinter(PrinterRequest printerRequest);

    PrinterResponse updatePrinter(PrinterResponse request);

    void deletePrinter(String refPrinter);

    PrinterResponse findPrinter(String refPrinter);

    List<PrinterResponse> findAllPrinters();

    PageableResponse<PrinterResponse> findAllPrinters(Pageable pageable);

    Page<Printer> searchPrinter(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy,String businessName, String lineOfBusiness, String ownerName, String businessRegisterNumber );

    ByteArrayInputStream exportPrinterData();

////////////// Distributor////////////////

    DistributorResponse createDistributor(DistributorRequest distributorRequest);

    DistributorResponse updateDistributor(DistributorResponse request);

    void deleteDistributor(String refDistributor);

    DistributorResponse findDistributor(String refDistributor);

    List<DistributorResponse> findAllDistributors();

    PageableResponse<DistributorResponse> findAllDistributors(Pageable pageable);

    Page<Distributor> searchDistributors(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String businessName, String lineOfBusiness, String ownerName, String businessRegisterNumber );

    ByteArrayInputStream exportDistributorsData();

////////////////////Facilities & Services//////////////

    FacilitiesServicesResponse createFacilitiesServices( FacilitiesServicesRequest  facilitiesServicesRequest);

    FacilitiesServicesResponse updateFacilitiesServices( FacilitiesServicesResponse request);

    void deleteFacilitiesServices(String refFacilitiesServices);

    FacilitiesServicesResponse findFacilitiesServices(String refFacilitiesServices);

    List<FacilitiesServicesResponse> findAllFacilitiesServices();

///////////////PublishingOtherProduct//////////

    PublishingOtherProductResponse createPublishingOtherProduct( PublishingOtherProductRequest  publishingOtherProductRequest);

    PublishingOtherProductResponse updatePublishingOtherProduct( PublishingOtherProductResponse request);

    void deletePublishingOtherProduct(String refPublishingOtherProduct);

    PublishingOtherProductResponse findPublishingOtherProduct(String refPublishingOtherProduct);

    List<PublishingOtherProductResponse> findAllPublishingOtherProduct();

/////////////Writing Language///////////

    WritingLanguageResponse createWritingLanguage(WritingLanguageRequest  writingLanguageRequest);

    WritingLanguageResponse updateWritingLanguage(WritingLanguageResponse request);

    void deleteWritingLanguage(String refWritingLanguage);

    WritingLanguageResponse findWritingLanguage(String refWritingLanguage);

    List<WritingLanguageResponse> findAllWritingLanguage();

    /////////////Fields Of Writing///////////

    FieldsOfWritingResponse createFieldsOfWriting(FieldsOfWritingRequest  fieldsOfWritingRequest);

    FieldsOfWritingResponse updateFieldsOfWriting(FieldsOfWritingResponse request);

    void deleteFieldsOfWriting(String refFieldsOfWriting);

    FieldsOfWritingResponse findFieldsOfWriting(String refFieldsOfWriting);

    List<FieldsOfWritingResponse> findAllFieldsOfWriting();

    /////////// Book promotion forms/////////////

    BookPromotionFormResponse createBookPromotionForm(BookPromotionFormRequest  bookPromotionFormRequest);

    BookPromotionFormResponse updateBookPromotionForm(BookPromotionFormResponse request);

    void deleteBookPromotionForm(String refBookPromotionForm);

    BookPromotionFormResponse findBookPromotionForm(String refBookPromotionForm);

    List<BookPromotionFormResponse> findAllBookPromotionForm();

    //////////Selling Points/////////////////////

    SellingPointsResponse createSellingPoints(SellingPointsRequest  sellingPointsRequest);

    SellingPointsResponse updateSellingPoints(SellingPointsResponse request);

    void deleteSellingPoints(String refSellingPoints);

    SellingPointsResponse findSellingPoints(String refSellingPoints);

    List<SellingPointsResponse> findAllSellingPoints();

}
