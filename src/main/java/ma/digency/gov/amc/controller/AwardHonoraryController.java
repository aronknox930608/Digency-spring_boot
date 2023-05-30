package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHassan2ListResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryListResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryRequest;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryResponse;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
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
@RequestMapping("honoraryAward/")
public interface AwardHonoraryController {

    @PostMapping("Artists")
    List<ArtistAccount> getListArtist();

    @PostMapping("create-candidate/")
    String createCandidate(@RequestPart("json") CandidateHonoraryAwardRequest request,@RequestPart("picture") MultipartFile picture,@RequestPart("cv")  MultipartFile cv,@RequestPart("identityStatement") MultipartFile identityStatement);

    @PostMapping("demand/")
    String createDemand(@RequestBody DemandPriceHonoraryRequest request);

    @GetMapping("get-demand")
    ResponseEntity<DemandPriceHonoraryResponse> getDemand(@RequestParam String refDemand);

    @PutMapping("update-demand")
    DemandPriceHonoraryResponse updateDemand(@RequestBody DemandPriceHonoraryResponse request);

    @DeleteMapping("demand/{refDemand}")
    ResponseEntity<Void> deleteDemand(@PathVariable("refDemand") @NotBlank String refDemand);

    @GetMapping("demands/list-demand")
    ResponseEntity<PageableResponse<DemandPriceHonoraryListResponse>> getAllDemands(@RequestParam Integer page, @RequestParam Integer size);

    //=============================================================================================

    @GetMapping("Candidate")
    ResponseEntity<CandidateHonoraryAwardResponse> getCandidateByRef(@RequestParam String refCandidate);

    @PutMapping("update-Candidate")
    String updateCandidate(@RequestPart("json") CandidateHonoraryAwardResponse request,@RequestPart("picture") MultipartFile picture,@RequestPart("cv")  MultipartFile cv,@RequestPart("identityStatement") MultipartFile identityStatement);


    @GetMapping("demands/")
    ResponseEntity<PageableResponse<DemandHonoraryAwardToListResponse>> getAllDemand(@RequestParam Integer page, @RequestParam Integer size);

    @PostMapping("add-award")
    List<AwardHonoraryResponse> addAwardHonorary(@RequestBody AwardRequest request);

    @PutMapping("update-award")
    ResponseEntity<List<AwardHonoraryResponse>> updateAwardHonorary(@RequestBody AwardHonoraryResponse request);

    @GetMapping("{refAward}")
    ResponseEntity<AwardHonoraryResponse> getAwardHonoraryByRef(@PathVariable("refAward")  String refAward);

    @DeleteMapping("delete-Award/{refAward}")
    ResponseEntity<List<AwardHonoraryResponse>> deleteAwardHonorary(@PathVariable("refAward") @NotBlank String refAward);

    @GetMapping("")
    ResponseEntity<List<AwardHonoraryResponse>> getAllAwardsHonorary();

    @GetMapping("demand-list")
    ResponseEntity<List<Demand>> getDemandsUserLogged(@RequestParam("refUser") String refUser);

    @GetMapping("search-demand")
    ResponseEntity<Page<Demand>> searchDemand(@RequestParam(required = false) int pageNumber, int pageSize, @RequestParam(required = false) Sort.Direction sortDirection, @RequestParam(required = false) String sortBy, @RequestParam(required = false) String status, @RequestParam(required = false) Date decision_date, @RequestParam(required = false) String demandOwnerFirstName, @RequestParam(required = false) String demandOwnerLastName, @RequestParam(required = false) String awardType);

    @PostMapping("import-artists")
    ResponseEntity<Void> importArtists(@RequestPart(name = "file") MultipartFile multipartFile);

    @GetMapping("export-artits")
    ResponseEntity<Resource> exportArtistData();

    @GetMapping("export-demand")
    ResponseEntity<Resource> exportDemands();

}
