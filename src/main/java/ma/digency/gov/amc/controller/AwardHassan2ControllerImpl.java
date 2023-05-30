package ma.digency.gov.amc.controller;


import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptType;
import org.springframework.core.io.Resource;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.process.AwardHassan2Process;
import ma.digency.gov.amc.process.DocumentProcess;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "awardHassan2/")
public class AwardHassan2ControllerImpl implements AwardHassan2Controller {

    @Autowired
    private AwardHassan2Process awardHassan2Process;

    @Autowired
    private DocumentProcess documentProcess;


    @Override
    public OwnerHandWritterResponse createOwner(OwnerHandWritterRequest request) {
        return awardHassan2Process.createOwner(request);
    }

    @Override
    public DemandAwardHassan2Response createDemand(DemandAwardHassan2Request request) {
        return awardHassan2Process.createDemand(request);
    }

    @Override
    public ManuscriptInfromationAddingResponse addManuscriptInformation(ManuscriptInformationRequest request) {
        return awardHassan2Process.addManuscriptInformation(request);
    }


    @Override
    public ResponseEntity<Void> uploadListDocument(String documentType, String refObject, String refParent, MultipartFile[] multipartFile) {
        Arrays.asList(multipartFile)
                .stream()
                .map(file -> documentProcess.saveDocument(documentType, file, refObject, refParent))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<DemandResponse> getDemandByRef(String refDemand) {

        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getDemandByRef(refDemand));
    }

    @Override
    public ResponseEntity<DemandResponse> updateDemand(DemandResponse request) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.updateDemand(request));
    }

    @Override
    public ResponseEntity<List<DocumentDemandResponse>> updateDocument(MultipartFile multipartFile, String refDocument) {
        documentProcess.updateDocument(multipartFile, refDocument);
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getDocuments(documentProcess.findDocumentByRef(refDocument).getRefObject()));
    }

    @Override
    public ResponseEntity<Void> deleteDemand(String refDemand) {
        awardHassan2Process.deleteDemand(refDemand);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PageableResponse<DemandResponse>> getAllDemand(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.findAllDemand(SearchUtils.createPageable(page, size)));
    }

    @Override
    public ResponseEntity<List<DocumentDemandResponse>> getDocuments(String refDemand) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getDocuments(refDemand));
    }

    @Override
    public List<AwardHassan2Response> addAwardHassan2(AwardRequest awardHassan2Request) {
        return awardHassan2Process.addAwardHassan2(awardHassan2Request);
    }

    @Override
    public ResponseEntity<List<AwardHassan2Response>> updateAwardHassan2(AwardHassan2Response request) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.updateAwardHassan2(request));
    }

    @Override
    public ResponseEntity<AwardHassan2Response> getAwardHassan2ByRef(String refAward) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getAwardHassan2ByRef(refAward));
    }

    @Override
    public ResponseEntity<List<AwardHassan2Response>> deleteAwardHassan2(String refAward) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.deleteAwardHassan2(refAward));
    }

    @Override
    public ResponseEntity<List<AwardHassan2Response>> getAllAwardsHassan2() {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.findAllAwards());
    }

    @Override
    public ResponseEntity<List<Demand>> getDemandsUserLogged(String refUser) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getDemandsUserLogged(refUser));
    }

    @Override
    public ResponseEntity<Page<OwnerHandWritten>> getOwners(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy , String cin, String firstName, String lastName, String firstNameAr, String lastNameAr,
                                                            String gender, String email, String phone, String rib) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getOwners(pageNumber,pageSize,sortDirection, sortBy , cin, firstName, lastName, firstNameAr, lastNameAr,
                gender, email, phone, rib));
    }

    @Override
    public ResponseEntity<Page<Demand>> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.searchDemand(pageNumber,pageSize,sortDirection,sortBy,status,decision_date,demandOwnerFirstName,demandOwnerLastName,awardType));
    }

    @Override
    public ResponseEntity<Void> importOwner(MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.OK).body( awardHassan2Process.saveOwnerExcel(multipartFile));
    }

    @Override
    public ResponseEntity<Resource> exportArtistData() {
        String filename = "owners.xlsx";
        InputStreamResource file = new InputStreamResource(awardHassan2Process.exportArtistData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public ResponseEntity<Void> importManuscriptInformation(MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.OK).body( awardHassan2Process.saveManuscriptInformationExcel(multipartFile));
    }

    @Override
    public ResponseEntity<Resource> exportManuscriptInformation() {
        String filename = "manuscriptInformation.xlsx";
        InputStreamResource file = new InputStreamResource(awardHassan2Process.exportManuscriptData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public ResponseEntity<Resource> exportDemands() {
        String filename = "demandsAwardHassan2.xlsx";
        InputStreamResource file = new InputStreamResource(awardHassan2Process.exportDemandData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public ResponseEntity<OwnerPersonalInfoResponse> getOwnerPersonalInformation(String email) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getOwnerPersonalInformation(email));
    }

    @Override
    public String createOrUpdatePersonalInformation(OwnerPersonalInfoResponse ownerPersonalInfoResponse) {
        return awardHassan2Process.createOrUpdatePersonalInformation(ownerPersonalInfoResponse);
    }

    @Override
    public DemandAwardResponse addDemand(DemandPriceRequest request) {
       return awardHassan2Process.addDemand(request);
    }

    @Override
    public ManuscriptInfromationAddingResponse createManuscriptInformation(ManuscriptInformationRequest request) {
        return awardHassan2Process.createManuscriptInformation(request);
    }

    @Override
    public ResponseEntity<DemandPriceHassan2Response> getDemandPriceByRef(String refDemand) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getDemandPriceByRef(refDemand));
    }

    @Override
    public ResponseEntity<DemandPriceHassan2Response> updateDemandPrice(DemandPriceHassan2Response request) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.updateDemandPrice(request));
    }

    @Override
    public ResponseEntity<Void> deleteDemandPrice(String refAward) {
        awardHassan2Process.deleteDemandPrice(refAward);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PageableResponse<DemandPriceHassan2ListResponse>> getAllDemands(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.findAllDemands(SearchUtils.createPageable(page, size)));

    }

    @Override
    public ResponseEntity<List<AwardCategoriesLestingResponse>> getAwardCategoriesHassan2(String refAwardType) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getAwardCategoriesHassan2(refAwardType));
    }

    @Override
    public ResponseEntity<List<ManuscriptType>> getAllManuscriptType() {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getAllManuscriptType());
    }

    @Override
    public ResponseEntity<List<PriceHassa2Response>> getPriceDemandList() {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getPriceDemandList());
    }

}
