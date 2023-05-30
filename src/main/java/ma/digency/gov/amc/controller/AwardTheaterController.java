package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.RoleTheater;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
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
@RequestMapping("awardTheater/")
public interface AwardTheaterController {

    @GetMapping("demand/get-artist")
    ResponseEntity<OwnerPersonalInfoResponse> getArtistInformation(@RequestParam("refAccount")  String refAccount);

    @PostMapping("demand/add-artist")
    String createOrUpdateArtistInformation(@RequestBody OwnerPersonalInfoResponse artistPersonalInfoResponse) throws Exception;


    @PostMapping("demand")
    TheaterPieceResponse addDemand(@RequestBody DemandPriceTheaterRequest request);

    @PostMapping("demand/add-participants")
    String addParticipant(@RequestBody ParticipantRequest participantRequest);

    @PostMapping("demand/document")
    ResponseEntity<Void> uploadListDocument(@RequestPart(name = "documentType", required = true) String documentType,
                                            @RequestPart(name = "refObject", required = true) String refObject,
                                            @RequestPart(name = "refParent", required = false) String refParent,
                                            @RequestPart(name = "file", required = true) MultipartFile[] multipartFile);

    @GetMapping("demand/{refDemand}")
    ResponseEntity<DemandPriceTheaterResponse> getDemand(@PathVariable("refDemand") @NotBlank String refDemand);


    @PutMapping("demand/update-demand")
    ResponseEntity<String> updateDemand(@RequestBody DemandPriceTheaterResponse request);

    @GetMapping("{refTheaterPiece}/participants")
    ResponseEntity<List<ParticipantTheaterResponse>> listParticipants(@PathVariable("refTheaterPiece") @NotBlank String refTheaterPiece);

    @GetMapping("participants/{refParticipant}")
    ResponseEntity<ParticipantTheaterResponse> getParticipantByRef(@PathVariable("refParticipant") @NotBlank String refParticipant);

    @PutMapping("participants/participant")
    ResponseEntity<List<ParticipantTheaterResponse>> updateParticipant(@RequestBody ParticipantTheaterResponse request);

    @DeleteMapping("participants")
    ResponseEntity<List<ParticipantTheaterResponse>> deleteParticipant(@RequestParam("refParticipant") @NotBlank String refParticipant);

    @GetMapping("{refTheaterPiece}/documents")
    ResponseEntity<List<DocumentDemandResponse>> getDocuments(@PathVariable("refTheaterPiece")  String refTheaterPiece);

    @PutMapping("documents/{refDocument}")
    ResponseEntity<List<DocumentDemandResponse>> updateDocument(@RequestPart(name = "file", required = true) MultipartFile multipartFile,
                                                                @PathVariable("refDocument") @NotBlank String refDocument);

    @DeleteMapping("demand/delete-demand")
    ResponseEntity<Void> deleteDemand(@RequestParam("refDemand") @NotBlank String refDemand);

    @GetMapping("theaterPiece")
    ResponseEntity<List<TheaterPieceResponse>> getTheaterPiecesOfArtist(@RequestParam("cin") @NotBlank String cin);

    @GetMapping("theaterPiece/{refTheaterPiece}")
    ResponseEntity<TheaterPieceResponse> getTheaterPiece(@PathVariable("refTheaterPiece")  String refTheaterPiece);

    @PutMapping("theaterPiece/update-theaterPiece")
    ResponseEntity<TheaterPieceResponse> updateTheaterPiece(@RequestBody  TheaterPieceResponse request);

    @GetMapping("roles-Theater")
    ResponseEntity<List<RoleTheater>> getRoleTheater();
    //============================================================================================

    @PostMapping("add-award")
    List<AwardTheaterResponse> addAwardTheater(@RequestBody AwardRequest awardTheaterRequest);

    @PutMapping("update-award")
    ResponseEntity<List<AwardTheaterResponse>> updateAwardTheater(@RequestBody AwardTheaterResponse request);


    @GetMapping("{refAward}")
    ResponseEntity<AwardTheaterResponse> getAwardTheaterByRef(@PathVariable("refAward")  String refAward);

    @DeleteMapping("delete-Award/{refAward}")
    ResponseEntity<List<AwardTheaterResponse>> deleteAwardTheater(@PathVariable("refAward") @NotBlank String refAward);

    @GetMapping("")
    ResponseEntity<List<AwardTheaterResponse>> getAllAwardsTheater();

    @GetMapping("search-demand")
    ResponseEntity<Page<Demand>> searchDemand(@RequestParam(required = false) int pageNumber, int pageSize, @RequestParam(required = false) Sort.Direction sortDirection, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String status, @RequestParam(required = false) Date decision_date, @RequestParam(required = false) String demandOwnerFirstName, @RequestParam(required = false) String demandOwnerLastName, @RequestParam(required = false) String awardType);

    @GetMapping("demands/")
    ResponseEntity<PageableResponse<DemandAwardTheaterResponse>> getAllDemand(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("demand-list")
    ResponseEntity<List<Demand>> getDemandsUserLogged(@RequestParam("refUser") String refUser);

    @GetMapping("export-demand")
    ResponseEntity<Resource> exportDemands();

    @PostMapping("import-Theater-Pieces")
    ResponseEntity<Void> importTheaterPieces(@RequestPart(name = "file") MultipartFile multipartFile);

    @GetMapping("export-Theater-Pieces")
    ResponseEntity<Resource> exportTheaterPieces();

    @PostMapping("import-participants/{refTheaterPiece}")
    ResponseEntity<Void> importParticipants(@RequestPart(name = "file") MultipartFile multipartFile,@PathVariable("refTheaterPiece")  String refTheaterPiece);

    @GetMapping("export-participants/{refTheaterPiece}")
    ResponseEntity<Resource> exportParticipants(@PathVariable("refTheaterPiece")  String refTheaterPiece);

    @GetMapping("demands/list-demand")
    ResponseEntity<PageableResponse<DemandPriceTheaterListResponse>> getAllDemands(@RequestParam Integer page, @RequestParam Integer size);


}
