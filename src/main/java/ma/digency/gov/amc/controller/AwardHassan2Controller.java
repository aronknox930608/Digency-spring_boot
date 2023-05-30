package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptType;
import org.springframework.core.io.Resource;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Validated
@RequestMapping("awardHassan2/")
public interface AwardHassan2Controller {

    @PostMapping("create-owner/")

    OwnerHandWritterResponse createOwner(@RequestBody OwnerHandWritterRequest request);

    @PostMapping("create-demand/")

    DemandAwardHassan2Response createDemand(@RequestBody DemandAwardHassan2Request request);

    @PostMapping("create-demand/add-manuscript-information/")
    ManuscriptInfromationAddingResponse addManuscriptInformation(@RequestBody ManuscriptInformationRequest request);



    @GetMapping("demand/{refDemand}/get-Demand")
    ResponseEntity<DemandResponse> getDemandByRef (@PathVariable("refDemand")  String refDemand);

    @PutMapping("demand/")
    ResponseEntity<DemandResponse> updateDemand(@RequestBody DemandResponse request);

    @PutMapping("demand/{refDocument}")
    ResponseEntity<List<DocumentDemandResponse>> updateDocument(@RequestPart(name = "file", required = true) MultipartFile multipartFile,
                                          @PathVariable("refDocument") @NotBlank String refDocument);

    @DeleteMapping("demand/{refDemand}")
    ResponseEntity<Void> deleteDemand(@PathVariable("refDemand") @NotBlank String refDemand);

    @GetMapping("demands/")
    ResponseEntity<PageableResponse<DemandResponse>> getAllDemand(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("demand/{refDemand}")
    ResponseEntity<List<DocumentDemandResponse>> getDocuments(@PathVariable("refDemand")  String refDemand);

    @PostMapping("add-award")
    List<AwardHassan2Response> addAwardHassan2(@RequestBody AwardRequest awardHassan2Request);

    @PutMapping("update-award")
    ResponseEntity<List<AwardHassan2Response>> updateAwardHassan2(@RequestBody AwardHassan2Response request);

    @GetMapping("{refAward}")
    ResponseEntity<AwardHassan2Response> getAwardHassan2ByRef(@PathVariable("refAward")  String refAward);

    @DeleteMapping("delete-Award/{refAward}")
    ResponseEntity<List<AwardHassan2Response>> deleteAwardHassan2(@PathVariable("refAward") @NotBlank String refAward);

    @GetMapping("")
    ResponseEntity<List<AwardHassan2Response>> getAllAwardsHassan2();

    @GetMapping("demand-list")
    ResponseEntity<List<Demand>> getDemandsUserLogged(@RequestParam("refUser") String refUser);

    @GetMapping("owners")
    ResponseEntity<Page<OwnerHandWritten>> getOwners(@RequestParam(required = false) int pageNumber, int pageSize, @RequestParam(required = false) Sort.Direction sortDirection, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String cin, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String firstNameAr, @RequestParam(required = false) String lastNameAr,
                                                     @RequestParam(required = false) String gender, @RequestParam(required = false) String email, @RequestParam(required = false) String phone, @RequestParam(required = false) String rib);

    @GetMapping("search-demand")
    ResponseEntity<Page<Demand>> searchDemand(@RequestParam(required = false) int pageNumber, int pageSize, @RequestParam(required = false) Sort.Direction sortDirection, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String status, @RequestParam(required = false) Date decision_date, @RequestParam(required = false) String demandOwnerFirstName, @RequestParam(required = false) String demandOwnerLastName, @RequestParam(required = false) String awardType);

    @PostMapping("import-owner")
    ResponseEntity<Void> importOwner(@RequestPart(name = "file") MultipartFile multipartFile);

    @GetMapping("export-owner/")
    ResponseEntity<Resource> exportArtistData();

    @PostMapping("import-manuscriptInformation")
    ResponseEntity<Void> importManuscriptInformation(@RequestPart(name = "file") MultipartFile multipartFile);

    @GetMapping("export-manuscriptInformation/")
    ResponseEntity<Resource> exportManuscriptInformation();

    @GetMapping("export-demand")
    ResponseEntity<Resource> exportDemands();

    //====================================================================================

    @GetMapping("personal-information/{email}")
    ResponseEntity<OwnerPersonalInfoResponse> getOwnerPersonalInformation(@PathVariable("email") @NotBlank String refAccount);

    @PostMapping("personal-information/add-update-information")
    String createOrUpdatePersonalInformation(@RequestBody OwnerPersonalInfoResponse ownerPersonalInfoResponse);

    @PostMapping("add-demand/")
    DemandAwardResponse addDemand(@RequestBody DemandPriceRequest request);

    @PostMapping("add-demand/add-manuscript-information/")
    ManuscriptInfromationAddingResponse createManuscriptInformation(@RequestBody ManuscriptInformationRequest request);

    @PostMapping("create-demand/add-document")
    ResponseEntity<Void> uploadListDocument(@RequestParam(name = "documentType", required = true) String documentType,
                                            @RequestParam(name = "refObject", required = true) String refObject,
                                            @RequestParam(name = "refParent", required = false) String refParent,
                                            @RequestParam(name = "file", required = true) MultipartFile[] multipartFile);

    @GetMapping("{refDemand}/get-Demand")
    ResponseEntity<DemandPriceHassan2Response> getDemandPriceByRef (@PathVariable("refDemand")  String refDemand);

    @PutMapping("update-demand")
    ResponseEntity<DemandPriceHassan2Response> updateDemandPrice(@RequestBody DemandPriceHassan2Response request);

    @DeleteMapping("delete-demand")
    ResponseEntity<Void> deleteDemandPrice(@RequestParam("refAward") @NotBlank String refAward);

    @GetMapping("demands/list-demand")
    ResponseEntity<PageableResponse<DemandPriceHassan2ListResponse>> getAllDemands(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("award-categories/{refAwardType}")
    ResponseEntity<List<AwardCategoriesLestingResponse>> getAwardCategoriesHassan2(@PathVariable("refAwardType") String refAwardType);

    @GetMapping("manuscrit-type")
    ResponseEntity<List<ManuscriptType>> getAllManuscriptType();

    @GetMapping("priceDemandList")
    ResponseEntity<List<PriceHassa2Response>> getPriceDemandList();
}
