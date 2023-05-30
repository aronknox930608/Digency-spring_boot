package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHassan2ListResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryListResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryRequest;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryResponse;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.process.AwardHonoraryProcess;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


@RestController
@Tag(name = "honoraryAward/")
public class AwardHonoraryControllerImpl implements AwardHonoraryController {

    @Autowired
    private AwardHonoraryProcess awardHonoraryProcess;


    @Override
    public List<ArtistAccount> getListArtist() {
        return awardHonoraryProcess.getListArtists();
    }

    @Override
    public String createCandidate(CandidateHonoraryAwardRequest request, MultipartFile picture, MultipartFile cv,MultipartFile identityStatement){
        return awardHonoraryProcess.createCandidate(request,picture,cv,identityStatement);
    }

    @Override
    public String createDemand(DemandPriceHonoraryRequest request) {
        return awardHonoraryProcess.createDemand(request);
    }

    @Override
    public ResponseEntity<DemandPriceHonoraryResponse> getDemand(String refDemand) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHonoraryProcess.getDemand(refDemand));
    }

    @Override
    public DemandPriceHonoraryResponse updateDemand(DemandPriceHonoraryResponse request) {
        return awardHonoraryProcess.updateDemand(request);
    }

    @Override
    public  ResponseEntity<CandidateHonoraryAwardResponse> getCandidateByRef(String refCandidate) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHonoraryProcess.getCandidateByRef(refCandidate));
    }

    @Override
    public String updateCandidate(CandidateHonoraryAwardResponse request,MultipartFile picture,MultipartFile cv, MultipartFile identityStatement){
        return awardHonoraryProcess.updateCandidate(request,picture,cv,identityStatement);
    }

    @Override
    public ResponseEntity<Void> deleteDemand(String refDemand) {
        awardHonoraryProcess.deleteDemand(refDemand);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PageableResponse<DemandPriceHonoraryListResponse>> getAllDemands(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHonoraryProcess.findAllDemands(SearchUtils.createPageable(page, size)));

    }

    @Override
    public ResponseEntity<PageableResponse<DemandHonoraryAwardToListResponse>> getAllDemand(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHonoraryProcess.findAllDemand(SearchUtils.createPageable(page, size)));
    }

    @Override
    public List<AwardHonoraryResponse> addAwardHonorary(AwardRequest request) {
        return awardHonoraryProcess.addAwardHonorary(request);
    }

    @Override
    public ResponseEntity<List<AwardHonoraryResponse>> updateAwardHonorary(AwardHonoraryResponse request) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHonoraryProcess.updateAwardHonorary(request));
    }

    @Override
    public ResponseEntity<AwardHonoraryResponse> getAwardHonoraryByRef(String refAward) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHonoraryProcess.getAwardHonoraryByRef(refAward));
    }

    @Override
    public ResponseEntity<List<AwardHonoraryResponse>> deleteAwardHonorary(String refAward) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHonoraryProcess.deleteAwardHonorary(refAward));
    }

    @Override
    public ResponseEntity<List<AwardHonoraryResponse>> getAllAwardsHonorary() {
        return ResponseEntity.status(HttpStatus.OK).body(awardHonoraryProcess.findAllAwards());
    }

    @Override
    public ResponseEntity<List<Demand>> getDemandsUserLogged(String refUser) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHonoraryProcess.getDemandsUserLogged(refUser));
    }

    @Override
    public ResponseEntity<Page<Demand>> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHonoraryProcess.searchDemand(pageNumber,pageSize,sortDirection,sortBy,status,decision_date,demandOwnerFirstName,demandOwnerLastName,awardType));
    }

    @Override
    public ResponseEntity<Void> importArtists(MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.OK).body( awardHonoraryProcess.saveArtistsExcel(multipartFile));

    }

    @Override
    public ResponseEntity<Resource> exportArtistData() {
        String filename = "artistsAccounts.xlsx";
        InputStreamResource file = new InputStreamResource(awardHonoraryProcess.exportArtistsData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public ResponseEntity<Resource> exportDemands() {
        String filename = "demandsHonoraryAward.xlsx";
        InputStreamResource file = new InputStreamResource(awardHonoraryProcess.exportDemandData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

}
