package ma.digency.gov.amc.controller;


import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import org.springframework.core.io.Resource;
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
@RequestMapping("awardBook/")
public interface AwardBookController {

    @GetMapping("demand/get-writer")
    ResponseEntity<OwnerPersonalInfoResponse> getWriter(@RequestParam("refAccount")  String refAccount) throws Exception;

    @PostMapping("demand/add-artist")
    String createArtist(@RequestBody OwnerPersonalInfoResponse artistPersonalInfoResponse) throws Exception;

    @GetMapping("demand/books")
    List<PublicationRequest> getAllPublication(@RequestParam("author") String author);

    @PostMapping("demand/add-publication")
    List<PublicationRequest> addPublication(@RequestBody PublicationPriceRequest request);

    @GetMapping("demand/publication/{refBook}")
    ResponseEntity<PublicationRequest> getPublication(@PathVariable("refBook")  String refBook);

    @PutMapping("demand/list-publication/update-publication")
    List<PublicationRequest> updatePublication(@RequestBody PublicationRequest publicationRequest);

    @DeleteMapping("demand/publication/{refPublication}")
    List<PublicationRequest> deletePublication(@PathVariable("refPublication") @NotBlank String refPublication);

    @GetMapping("demand/awards")
    List<AwardObtainedResponse> getAwards(@RequestParam("author") String author);

    @PostMapping("demand/add-award")
    List<AwardObtainedResponse> addAward(@RequestBody AwardObtainedRequest request);

    @GetMapping("demand/award/{refAward}")
    ResponseEntity<AwardObtainedResponse> getAward(@PathVariable("refAward")  String refAward);

    @PutMapping("demand/awards/update-award")
    List<AwardObtainedResponse> updateAward(@RequestBody AwardObtainedResponse request);

    @DeleteMapping("demand/awards/{refAward}")
    List<AwardObtainedResponse> deleteAward(@PathVariable("refAward") @NotBlank String refAward);

    //get all publication+ choose book else add book

    @PostMapping("add-book/")
    BookPrice createBook(@RequestBody PublicationPriceRequest request);

    @PostMapping("create-demand/")
    DemandAwardBookResponse createDemand(@RequestBody DemandPriceBookRequest request) throws Exception;



    @PostMapping("demand/add-document")
    ResponseEntity<Void> uploadListDocument(@RequestPart(name = "documentType", required = true) String documentType,
                                            @RequestPart(name = "refObject", required = true) String refObject,
                                            @RequestPart(name = "refParent", required = false) String refParent,
                                            @RequestPart(name = "file", required = true) MultipartFile[] multipartFile);


    @DeleteMapping("demand/{refDemand}")
    ResponseEntity<Void> deleteDemand(@PathVariable("refDemand") @NotBlank String refDemand);

    @GetMapping("demands/")
    ResponseEntity<PageableResponse<DemandAwardBookListResponse>> getAllDemand(@RequestParam Integer page, @RequestParam Integer size);

   /* @PutMapping("demand/update-artist")
    String updateArtist(@RequestBody ArtistAccountRequest artistAccountRequest) throws Exception;*/

    @GetMapping("demands/get-demand")
    ResponseEntity<DemandPriceBookResponse> getDemand(@RequestParam("refDemand") String refDemand);

    @PutMapping("demand/update-Demand")
    String updateDemand(@RequestBody DemandPriceBookResponse request);

    @GetMapping("demand/book-demand")
    PublicationRequest getBookDemand(@RequestParam("refDemand") String refDemand);

    @PutMapping("demand/update-book")
    String updateBook(@RequestBody PublicationRequest request);

    @GetMapping("demand/{refDemand}/documents")
    ResponseEntity<List<DocumentDemandResponse>> getDocuments(@PathVariable("refDemand")  String refDemand);

    @PutMapping("demand/documents/{refDocument}")
    ResponseEntity<List<DocumentDemandResponse>> updateDocument(@RequestPart(name = "file", required = true) MultipartFile multipartFile,
                                                                @PathVariable("refDocument") @NotBlank String refDocument);


    @GetMapping("demands/list-demand")
    ResponseEntity<PageableResponse<DemandPriceBookListResponse>> getAllDemands(@RequestParam Integer page, @RequestParam Integer size);


    @GetMapping("award-categories/{refAwardType}")
    ResponseEntity<List<AwardCategoriesLestingResponse>> getAwardCategoriesBook(@PathVariable("refAwardType") String refAwardType);



    @GetMapping("{refArtistAccount}/documentsPersonal")
    ResponseEntity<List<DocumentDemandResponse>> getDocumentsPersonal(@PathVariable("refArtistAccount")  String refArtistAccount);

    //====================================================================
    @PostMapping("add-award")
    List<AwardBookResponse> addAwardBook(@RequestBody AwardRequest request);

    @PutMapping("update-award")
    ResponseEntity<List<AwardBookResponse>> updateAwardBook(@RequestBody AwardBookResponse request);

    @GetMapping("{refAward}")
    ResponseEntity<AwardBookResponse> getAwardBookByRef(@PathVariable("refAward")  String refAward);

    @DeleteMapping("delete-Award/{refAward}")
    ResponseEntity<List<AwardBookResponse>> deleteAwardBook(@PathVariable("refAward") @NotBlank String refAward);

    @GetMapping("")
    ResponseEntity<List<AwardBookResponse>> getAllAwardsBook();

    @GetMapping("demand-list")
    ResponseEntity<List<Demand>> getDemandsUserLogged(@RequestParam("refUser") String refUser);

    @GetMapping("search-demand")
    ResponseEntity<Page<Demand>> searchDemand(@RequestParam(required = false) int pageNumber, int pageSize, @RequestParam(required = false) Sort.Direction sortDirection, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String status, @RequestParam(required = false) Date decision_date, @RequestParam(required = false) String demandOwnerFirstName, @RequestParam(required = false) String demandOwnerLastName, @RequestParam(required = false) String awardType);

    @GetMapping("export-demand")
    ResponseEntity<Resource> exportDemands();

    @PostMapping("import-Publications")
    ResponseEntity<Void> importPublications(@RequestPart(name = "file") MultipartFile multipartFile);

    @GetMapping("export-Publications")
    ResponseEntity<Resource> exportPublicationsData();

    @PostMapping("import-Award-Obtained")
    ResponseEntity<Void> importAwardsObtained(@RequestPart(name = "file") MultipartFile multipartFile);

    @GetMapping("export-Award-Obtained")
    ResponseEntity<Resource> exportAwardObtained();




}
