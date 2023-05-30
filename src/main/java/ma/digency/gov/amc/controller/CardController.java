package ma.digency.gov.amc.controller;


import ma.digency.gov.amc.dto.DocumentTypeResponse;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.card.*;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountResponse;
import ma.digency.gov.amc.utils.CardPage;
import ma.digency.gov.amc.utils.DemandPage;
import ma.digency.gov.amc.utils.searching.CardSearchCriteria;
import ma.digency.gov.amc.utils.searching.DemandPlanningCardCriteria;
import ma.digency.gov.amc.utils.searching.DemandSearchCriteria;
import ma.digency.gov.amc.utils.DemandPlanningPage;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("/artist/")
public interface CardController {

    @GetMapping("demand/getAccount/{email}")
    ResponseEntity<ArtistAccountCardResponse> getAuthenticatedAccount(@PathVariable @NotBlank String email);

    @GetMapping("demand/getArtistAccount/{refArtistAccount}")
    ResponseEntity<ArtistAccountCardResponse> getArtistAccount(@PathVariable @NotBlank String refArtistAccount);

    @PostMapping("demand/ArtistAccount")
    ResponseEntity<ArtistAccountCardResponse> createArtistAccount(@RequestBody ArtistAccountCardRequest artistAccountCardRequest);

    @PostMapping("demand/Card/{refArtistAccount}")
    ResponseEntity<CardResponse> createCard(@PathVariable @NotBlank String refArtistAccount,@RequestBody CardRequest cardRequest);

    @GetMapping("demand/isPeriodOpen")
    ResponseEntity<Boolean> isPeriodOpenForDemandCard();

    @PostMapping("demand/{refArtistAccount}")
    ResponseEntity<DemandCardResponse> createDemand(@PathVariable @NotBlank String refArtistAccount,
                                                    @RequestBody DemandCardRequest demandRequest);
    @DeleteMapping("demand/{refDemand}/")
    ResponseEntity<Void> deleteDemandCard(@PathVariable("refDemand") @NotBlank String refDemand);

    @PostMapping("withDraw/Card/{refCard}")
    ResponseEntity<CardResponse> retiredCard(@PathVariable(name="refCard") String refCard);

    @DeleteMapping("demand/card/{refCard}")
    ResponseEntity<Void> deleteCard(@PathVariable("refCard") @NotBlank String refCard);

    @GetMapping("demand/{refDemand}/")
    ResponseEntity<DemandCardResponse> getDemandByRefDemand(@PathVariable @NotBlank String refDemand);

    @PutMapping("/update/")
    ResponseEntity<DemandCardResponse> updateDemand(@RequestBody DemandCardRequest demandRequest);

    @PutMapping("update/artistAccount")
    ResponseEntity<ArtistAccountCardResponse> updateArtistAccount(@RequestBody ArtistAccountCardRequest artistAccountRequest);

    @GetMapping("demands/")
    ResponseEntity<PageableResponse<DemandCardResponse>> allDemands(@RequestParam Integer page, @RequestParam Integer limit);
    @GetMapping("cards/")
    ResponseEntity<PageableResponse<CardResponse>> getAllCards(@RequestParam Integer page,@RequestParam Integer limit);

    @PostMapping("addFiles/")
    void uploadListDocument(@RequestPart(name = "documentType") String documentType,
                            @RequestPart(name = "refObject") String refObject,
                            @RequestPart(name = "refParent", required = false) String refParent,
                            @RequestPart(name = "file") MultipartFile[] multipartFile);

    @PutMapping("demand/{refDemand}/document/{refDocument}")
    List<DocumentTypeResponse> updateDocumentOfDemand(
            @RequestPart(name = "file") MultipartFile multipartFile,
            @PathVariable("refDemand") @NotBlank String refDemand,
            @PathVariable("refDocument") @NotBlank String refDocument);

    @PostMapping("/artistAccount/import")
    ResponseEntity<List<ArtistAccountResponse>> ImportExcelData(@RequestParam("file") MultipartFile file);

    @PostMapping("card/import")
    ResponseEntity<List<CardResponse>> importArtistCards(@RequestParam("file") MultipartFile file);

    @GetMapping("artistAccount/export/")
    ResponseEntity<Resource> exportArtistData();

    @GetMapping("artistCard/export/")
    ResponseEntity<Resource> exportArtistCard();

    @GetMapping("searchDemand")
    ResponseEntity<Page<DemandCardResponse>> getDemandsWithSearchCriteria(DemandPage demandPage, DemandSearchCriteria demandSearchCriteria);

    @GetMapping("searchCard")
    ResponseEntity<Page<CardResponse>> getCardsWithSearchCriteria(CardPage cardPage, CardSearchCriteria cardSearchCriteria);

    @GetMapping("{refArtistAccount}/demand")
    ResponseEntity<List<DemandCardResponse>> getDemandsByRefArtistAccount(@PathVariable  @NotBlank String refArtistAccount);

    @GetMapping("allDemands")
    ResponseEntity<List<DemandCardResponse>> getAllDemands();

    @PostMapping("demandPlanning")
    ResponseEntity<DemandPlanningResponse> createDemandPlanning(@RequestBody DemandPlanningRequest demandPlanningRequest);

    @DeleteMapping("deletePeriod/{refDemandPlanning}")
    ResponseEntity<Void> deleteDemandPlanning(@PathVariable("refDemandPlanning") @NotBlank String refDemandPlanning);

    @PutMapping("updatePlanning/")
    ResponseEntity<DemandPlanningResponse> updateDemandPlanning(@RequestBody DemandPlanningRequest demandPlanningRequest);

    @GetMapping("plannings")
    ResponseEntity<PageableResponse<DemandPlanningResponse>> listPlannings(@RequestParam Integer page,@RequestParam Integer limit);

    @GetMapping("searchPlanning")
    ResponseEntity<Page<DemandPlanningResponse>> getPlanningWithSearchCriteria(DemandPlanningPage demandPlanning, DemandPlanningCardCriteria demandPlanningCardCriteria);

}
