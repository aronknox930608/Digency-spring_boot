package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.participant.*;
import ma.digency.gov.amc.process.ParticipantProcess;
import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.printer.Printer;
import ma.digency.gov.amc.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Participant")
public class participantControllerImpl implements participantController {

   @Autowired
    private ParticipantProcess participantProcess;

    @Override
    public PrinterResponse createPrinter(PrinterRequest request) {
        return participantProcess.createPrinter(request);
    }

    @Override
    public ResponseEntity<PrinterResponse> updatePrinter(PrinterResponse printerRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.updatePrinter(printerRequest));
    }
    @Override
    public ResponseEntity<Void> deletePrinter(String refPrinter) {
        participantProcess.deletePrinter(refPrinter);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PrinterResponse> getPrinter(String refPrinter) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.findPrinter(refPrinter));
    }

    @Override
    public ResponseEntity<List<PrinterResponse>> getAllPrinter() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.findAllPrinters() );
    }

    @Override
    public ResponseEntity<PageableResponse<PrinterResponse>> getAllPrinters(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.findAllPrinters(SearchUtils.createPageable(page,size)) );
    }

    @Override
    public ResponseEntity<Page<Printer>> searchPrinters(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String businessName, String lineOfBusiness, String ownerName, String businessRegisterNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.searchPrinter(pageNumber, pageSize, sortDirection, sortBy, businessName, lineOfBusiness,ownerName ,businessRegisterNumber ));
    }

    @Override
    public ResponseEntity<Resource> exportPrinterData() {
        String filename = "printers.xlsx";
        InputStreamResource file = new InputStreamResource(participantProcess.exportPrinterData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public DistributorResponse createDistributor(DistributorRequest request) {
        return participantProcess.createDistributor(request);
    }

    @Override
    public ResponseEntity<DistributorResponse> updateDistributor(DistributorResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.updateDistributor(request));
    }

    @Override
    public ResponseEntity<Void> deleteDistributor(String refDistributor) {
        participantProcess.deleteDistributor(refDistributor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<DistributorResponse> getDistributor(String refDistributor) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.findDistributor(refDistributor));
    }

    @Override
    public ResponseEntity<List<DistributorResponse>> getAllDistributor() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.findAllDistributors() );
    }

    @Override
    public ResponseEntity<PageableResponse<DistributorResponse>> getAllDistributors(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.findAllDistributors(SearchUtils.createPageable(page,size)) );
    }

    @Override
    public ResponseEntity<Page<Distributor>> searchDistributors(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String businessName, String lineOfBusiness, String ownerName, String businessRegisterNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.searchDistributors(pageNumber, pageSize, sortDirection, sortBy, businessName, lineOfBusiness,ownerName ,businessRegisterNumber ));
    }


    @Override
    public ResponseEntity<Resource> exportDistributorData() {
        String filename = "distributors.xlsx";
        InputStreamResource file = new InputStreamResource(participantProcess.exportDistributorsData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public FacilitiesServicesResponse createFacilitiesServices(FacilitiesServicesRequest request) {
        return participantProcess.createFacilitiesServices(request);
    }

    @Override
    public ResponseEntity<FacilitiesServicesResponse> updateFacilitiesServices(FacilitiesServicesResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.updateFacilitiesServices(request));
    }

    @Override
    public ResponseEntity<Void> deleteFacilitiesServices(String refFacilitiesServices) {
        participantProcess.deleteFacilitiesServices(refFacilitiesServices);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<FacilitiesServicesResponse> getFacilitiesServices(String refFacilitiesServices) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.findFacilitiesServices(refFacilitiesServices));
    }

    @Override
    public ResponseEntity<List<FacilitiesServicesResponse>> getAllFacilitiesServices() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.findAllFacilitiesServices() );
    }

    @Override
    public PublishingOtherProductResponse createPublishingOtherProduct(PublishingOtherProductRequest request) {
        return participantProcess.createPublishingOtherProduct(request);
    }

    @Override
    public ResponseEntity<PublishingOtherProductResponse> updatePublishingOtherProduct(PublishingOtherProductResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.updatePublishingOtherProduct(request));
    }

    @Override
    public ResponseEntity<Void> deletePublishingOtherProduct(String refPublishingOtherProduct) {
        participantProcess.deletePublishingOtherProduct(refPublishingOtherProduct);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PublishingOtherProductResponse> getPublishingOtherProduct(String refPublishingOtherProduct) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.findPublishingOtherProduct(refPublishingOtherProduct));
    }

    @Override
    public ResponseEntity<List<PublishingOtherProductResponse>> getAllPublishingOtherProduct() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.findAllPublishingOtherProduct() );
    }

    @Override
    public WritingLanguageResponse createWritingLanguage(WritingLanguageRequest request) {
        return participantProcess.createWritingLanguage(request);
    }

    @Override
    public ResponseEntity<WritingLanguageResponse> updateWritingLanguage(WritingLanguageResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.updateWritingLanguage(request));
    }

    @Override
    public ResponseEntity<Void> deleteWritingLanguage(String refWritingLanguage) {
        participantProcess.deleteWritingLanguage(refWritingLanguage);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<WritingLanguageResponse> getWritingLanguage(String refWritingLanguage) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.findWritingLanguage(refWritingLanguage));
    }

    @Override
    public ResponseEntity<List<WritingLanguageResponse>> getAllWritingLanguage() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.findAllWritingLanguage() );
    }

    @Override
    public FieldsOfWritingResponse createFieldsOfWriting(FieldsOfWritingRequest request) {
        return participantProcess.createFieldsOfWriting(request);
    }

    @Override
    public ResponseEntity<FieldsOfWritingResponse> updateFieldsOfWriting(FieldsOfWritingResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.updateFieldsOfWriting(request));
    }

    @Override
    public ResponseEntity<Void> deleteFieldsOfWriting(String refFieldsOfWriting) {
        participantProcess.deleteFieldsOfWriting(refFieldsOfWriting);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<FieldsOfWritingResponse> getFieldsOfWriting(String refFieldsOfWriting) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.findFieldsOfWriting(refFieldsOfWriting));
    }

    @Override
    public ResponseEntity<List<FieldsOfWritingResponse>> getAllFieldsOfWriting() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.findAllFieldsOfWriting() );
    }

    @Override
    public BookPromotionFormResponse createBookPromotionForm(BookPromotionFormRequest request) {
        return participantProcess.createBookPromotionForm(request);
    }

    @Override
    public ResponseEntity<BookPromotionFormResponse> updateBookPromotionForm(BookPromotionFormResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.updateBookPromotionForm(request));
    }

    @Override
    public ResponseEntity<Void> deleteBookPromotionForm(String refBookPromotionForm) {
        participantProcess.deleteBookPromotionForm(refBookPromotionForm);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<BookPromotionFormResponse> getBookPromotionForm(String refBookPromotionForm) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.findBookPromotionForm(refBookPromotionForm));
    }

    @Override
    public ResponseEntity<List<BookPromotionFormResponse>> getAllBookPromotionForm() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.findAllBookPromotionForm() );
    }

    @Override
    public SellingPointsResponse createSellingPoints(SellingPointsRequest request) {
        return participantProcess.createSellingPoints(request);
    }

    @Override
    public ResponseEntity<SellingPointsResponse> updateSellingPoints(SellingPointsResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.updateSellingPoints(request));
    }

    @Override
    public ResponseEntity<Void> deleteSellingPoints(String refSellingPoints) {
        participantProcess.deleteSellingPoints(refSellingPoints);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<SellingPointsResponse> getSellingPoints(String refSellingPoints) {
        return ResponseEntity.status(HttpStatus.OK).body(participantProcess.findSellingPoints(refSellingPoints));
    }

    @Override
    public ResponseEntity<List<SellingPointsResponse>> getAllSellingPoints() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(participantProcess.findAllSellingPoints() );
    }

}
