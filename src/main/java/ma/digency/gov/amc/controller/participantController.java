package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.participant.*;
import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.participant.FieldsOfWriting;
import ma.digency.gov.amc.repository.entity.printer.Printer;
import ma.digency.gov.amc.service.participant.PublishingOtherProductService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RequestMapping("participant/")
public interface participantController {

    @PostMapping("printers/")
    PrinterResponse createPrinter(@RequestBody @Validated PrinterRequest request);

    @PutMapping("printers/")
    ResponseEntity<PrinterResponse> updatePrinter(@RequestBody @Validated PrinterResponse request);

    @DeleteMapping("printers/{refPrinter}")
    ResponseEntity<Void> deletePrinter(@PathVariable("refPrinter") @NotBlank String refPrinter);

    @GetMapping("printers/{refPrinter}")
    ResponseEntity<PrinterResponse> getPrinter(@PathVariable("refPrinter") @NotBlank String refPrinter);

    @GetMapping("printers/")
    ResponseEntity<List<PrinterResponse>> getAllPrinter();

    @GetMapping("printers/pageable")
    ResponseEntity<PageableResponse<PrinterResponse>> getAllPrinters(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("printers/search")
    ResponseEntity<Page<Printer>> searchPrinters(@RequestParam(required = false) int pageNumber,
                                                 int pageSize,
                                                 @RequestParam(required = false) Sort.Direction sortDirection,
                                                 @RequestParam(required = false) String sortBy,
                                                 @RequestParam(required = false) String businessName ,
                                                 @RequestParam(required = false) String lineOfBusiness,
                                                 @RequestParam(required = false) String ownerName,
                                                 @RequestParam(required = false) String businessRegisterNumber);

    @GetMapping("printers/export")
    ResponseEntity<Resource> exportPrinterData();

    @PostMapping("distributors/")
    DistributorResponse createDistributor(@RequestBody @Validated DistributorRequest request);

    @PutMapping("distributors/")
    ResponseEntity<DistributorResponse> updateDistributor(@RequestBody @Validated DistributorResponse request);

    @DeleteMapping("distributors/{refDistributor}")
    ResponseEntity<Void> deleteDistributor(@PathVariable("refDistributor") @NotBlank String refDistributor);

    @GetMapping("distributors/{refDistributor}")
    ResponseEntity<DistributorResponse> getDistributor(@PathVariable("refDistributor") @NotBlank String refDistributor);

    @GetMapping("distributors/")
    ResponseEntity<List<DistributorResponse>> getAllDistributor();

    @GetMapping("distributors/pageable")
    ResponseEntity<PageableResponse<DistributorResponse>> getAllDistributors(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("distributors/search")
    ResponseEntity<Page<Distributor>> searchDistributors(@RequestParam(required = false) int pageNumber,
                                                         int pageSize,
                                                         @RequestParam(required = false) Sort.Direction sortDirection,
                                                         @RequestParam(required = false) String sortBy,
                                                         @RequestParam(required = false) String businessName ,
                                                         @RequestParam(required = false) String lineOfBusiness,
                                                         @RequestParam(required = false) String ownerName,
                                                         @RequestParam(required = false) String businessRegisterNumber);

    @GetMapping("distributors/export")
    ResponseEntity<Resource> exportDistributorData();

////////////// facilities & services //////////
    @PostMapping("facilities/")
    FacilitiesServicesResponse createFacilitiesServices(@RequestBody @Validated FacilitiesServicesRequest request);

    @PutMapping("facilities/")
    ResponseEntity<FacilitiesServicesResponse> updateFacilitiesServices(@RequestBody @Validated FacilitiesServicesResponse request);

    @DeleteMapping("facilities/{refFacilitiesServices}")
    ResponseEntity<Void> deleteFacilitiesServices(@PathVariable("refFacilitiesServices") @NotBlank String refFacilitiesServices);

    @GetMapping("facilities/{refFacilitiesServices}")
    ResponseEntity<FacilitiesServicesResponse> getFacilitiesServices(@PathVariable("refFacilitiesServices") @NotBlank String refFacilitiesServices);

    @GetMapping("facilities/")
    ResponseEntity<List<FacilitiesServicesResponse>> getAllFacilitiesServices();

    //////////Publishing Other Product//////////

    @PostMapping("otherProduct/")
    PublishingOtherProductResponse createPublishingOtherProduct(@RequestBody @Validated PublishingOtherProductRequest request);

    @PutMapping("otherProduct/")
    ResponseEntity<PublishingOtherProductResponse> updatePublishingOtherProduct(@RequestBody @Validated PublishingOtherProductResponse request);

    @DeleteMapping("otherProduct/{refPublishingOtherProduct}")
    ResponseEntity<Void> deletePublishingOtherProduct(@PathVariable("refPublishingOtherProduct") @NotBlank String refPublishingOtherProduct);

    @GetMapping("otherProduct/{refPublishingOtherProduct}")
    ResponseEntity<PublishingOtherProductResponse> getPublishingOtherProduct(@PathVariable("refPublishingOtherProduct") @NotBlank String refPublishingOtherProduct);

    @GetMapping("otherProduct/")
    ResponseEntity<List<PublishingOtherProductResponse>> getAllPublishingOtherProduct();

    ///////// Writing Language/////////////

    @PostMapping("writingLanguage/")
    WritingLanguageResponse createWritingLanguage(@RequestBody @Validated WritingLanguageRequest request);

    @PutMapping("writingLanguage/")
    ResponseEntity<WritingLanguageResponse> updateWritingLanguage(@RequestBody @Validated WritingLanguageResponse request);

    @DeleteMapping("writingLanguage/{refPublishingOtherProduct}")
    ResponseEntity<Void> deleteWritingLanguage(@PathVariable("refWritingLanguage") @NotBlank String refWritingLanguage);

    @GetMapping("writingLanguage/{refPublishingOtherProduct}")
    ResponseEntity<WritingLanguageResponse> getWritingLanguage(@PathVariable("refWritingLanguage") @NotBlank String refWritingLanguage);

    @GetMapping("writingLanguage/")
    ResponseEntity<List<WritingLanguageResponse>> getAllWritingLanguage();

    ///////// Fields of writing////////////

    @PostMapping("fieldsOfWriting/")
    FieldsOfWritingResponse createFieldsOfWriting(@RequestBody @Validated FieldsOfWritingRequest request);

    @PutMapping("fieldsOfWriting/")
    ResponseEntity<FieldsOfWritingResponse> updateFieldsOfWriting(@RequestBody @Validated FieldsOfWritingResponse request);

    @DeleteMapping("fieldsOfWriting/{refFieldsOfWriting}")
    ResponseEntity<Void> deleteFieldsOfWriting(@PathVariable("refFieldsOfWriting") @NotBlank String refFieldsOfWriting);

    @GetMapping("fieldsOfWriting/{refFieldsOfWriting}")
    ResponseEntity<FieldsOfWritingResponse> getFieldsOfWriting(@PathVariable("refFieldsOfWriting") @NotBlank String refFieldsOfWriting);

    @GetMapping("fieldsOfWriting/")
    ResponseEntity<List<FieldsOfWritingResponse>> getAllFieldsOfWriting();

    //////// Book promotion form ///////////

    @PostMapping("promotionForm/")
    BookPromotionFormResponse createBookPromotionForm(@RequestBody @Validated BookPromotionFormRequest request);

    @PutMapping("promotionForm/")
    ResponseEntity<BookPromotionFormResponse> updateBookPromotionForm(@RequestBody @Validated BookPromotionFormResponse request);

    @DeleteMapping("promotionForm/{refBookPromotionForm}")
    ResponseEntity<Void> deleteBookPromotionForm(@PathVariable("refBookPromotionForm") @NotBlank String refBookPromotionForm);

    @GetMapping("promotionForm/{refBookPromotionForm}")
    ResponseEntity<BookPromotionFormResponse> getBookPromotionForm(@PathVariable("refBookPromotionForm") @NotBlank String refBookPromotionForm);

    @GetMapping("promotionForm/")
    ResponseEntity<List<BookPromotionFormResponse>> getAllBookPromotionForm();

    ///////// Selling Points ////////////

    @PostMapping("sellingPoints/")
    SellingPointsResponse createSellingPoints(@RequestBody @Validated SellingPointsRequest request);

    @PutMapping("sellingPoints/")
    ResponseEntity<SellingPointsResponse> updateSellingPoints(@RequestBody @Validated SellingPointsResponse request);

    @DeleteMapping("sellingPoints/{refSellingPoints}")
    ResponseEntity<Void> deleteSellingPoints(@PathVariable("refSellingPoints") @NotBlank String refSellingPoints);

    @GetMapping("sellingPoints/{refSellingPoints}")
    ResponseEntity<SellingPointsResponse> getSellingPoints(@PathVariable("refSellingPoints") @NotBlank String refSellingPoints);

    @GetMapping("sellingPoints/")
    ResponseEntity<List<SellingPointsResponse>> getAllSellingPoints();

}
