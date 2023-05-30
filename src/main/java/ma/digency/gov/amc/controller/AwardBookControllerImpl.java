package ma.digency.gov.amc.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.process.AwardBookProcess;
import ma.digency.gov.amc.process.AwardHassan2Process;
import ma.digency.gov.amc.process.DocumentProcess;
import ma.digency.gov.amc.repository.SearchFiles.OwnerHandWrittenPage;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.utils.SearchUtils;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "awardBook/")
public class AwardBookControllerImpl implements AwardBookController {

    @Autowired
    private AwardBookProcess awardBookProcess;

    @Autowired
    private DocumentProcess documentProcess;

    @Autowired
    private AwardHassan2Process awardHassan2Process;

    @Override
    public ResponseEntity<OwnerPersonalInfoResponse> getWriter(String refAccount) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.getWriter(refAccount));
    }

    @Override
    public String createArtist(OwnerPersonalInfoResponse request) throws Exception {
        return awardBookProcess.createArtist(request);
    }

    @Override
    public List<PublicationRequest> getAllPublication(String author) {
        return awardBookProcess.getAllPublication(author);
    }

    @Override
    public List<PublicationRequest> addPublication(PublicationPriceRequest request) {
        return awardBookProcess.addPublication(request);
    }

    @Override
    public ResponseEntity<PublicationRequest> getPublication(String refBook) {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.getPublication(refBook));
    }

    @Override
    public List<AwardObtainedResponse> getAwards(String author) {
        return awardBookProcess.getAwards(author);
    }

    @Override
    public List<AwardObtainedResponse> addAward(AwardObtainedRequest request) {
        return awardBookProcess.addAward(request);
    }

    @Override
    public List<AwardObtainedResponse> updateAward(AwardObtainedResponse request) {
        return awardBookProcess.updateAward(request);
    }

    @Override
    public ResponseEntity<AwardObtainedResponse> getAward(String refAward) {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.getAward(refAward));
    }

    @Override
    public List<AwardObtainedResponse> deleteAward(String refAward) {
        return awardBookProcess.deleteAward(refAward);
    }


    @Override
    public DemandAwardBookResponse createDemand(DemandPriceBookRequest request) throws Exception {
        return awardBookProcess.createDemand(request);
    }

    @Override
    public BookPrice createBook(PublicationPriceRequest request) {
        return awardBookProcess.createBook(request);
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
    public ResponseEntity<Void> deleteDemand(String refDemand) {
         awardBookProcess.deleteDemand(refDemand);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PageableResponse<DemandAwardBookListResponse>> getAllDemand(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.findAllDemand(SearchUtils.createPageable(page, size)));
    }

    @Override
    public List<PublicationRequest> updatePublication(PublicationRequest request) {
        return awardBookProcess.updatePublication(request);
    }

    @Override
    public List<PublicationRequest> deletePublication(String refPublication) {
        return awardBookProcess.deletePublication(refPublication);
    }

    @Override
    public ResponseEntity<DemandPriceBookResponse> getDemand(String refDemand) {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.getDemand(refDemand));
    }

    @Override
    public String updateDemand(DemandPriceBookResponse request) {
        return awardBookProcess.updateDemand(request);
    }

    @Override
    public PublicationRequest getBookDemand(String refDemand) {
        return awardBookProcess.getBookDemand(refDemand);
    }

    @Override
    public String updateBook(PublicationRequest request) {
        return awardBookProcess.updateBook(request);
    }

    @Override
    public ResponseEntity<List<DocumentDemandResponse>> getDocuments(String refDemand) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getDocuments(refDemand));
    }

    @Override
    public ResponseEntity<List<DocumentDemandResponse>> updateDocument(MultipartFile multipartFile, String refDocument) {
        documentProcess.updateDocument(multipartFile, refDocument);
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getDocuments(documentProcess.findDocumentByRef(refDocument).getRefObject()));
    }

    @Override
    public ResponseEntity<PageableResponse<DemandPriceBookListResponse>> getAllDemands(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.findAllDemands(SearchUtils.createPageable(page, size)));

    }

    @Override
    public ResponseEntity<List<AwardCategoriesLestingResponse>> getAwardCategoriesBook(String refAwardType) {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.getAwardCategoriesBook(refAwardType));
    }

    @Override
    public ResponseEntity<List<DocumentDemandResponse>> getDocumentsPersonal(String refArtistAccount) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getDocumentsPersonal(refArtistAccount));
    }

    @Override
    public List<AwardBookResponse> addAwardBook(AwardRequest request) {
        return awardBookProcess.addAwardBook(request);
    }

    @Override
    public ResponseEntity<List<AwardBookResponse>> updateAwardBook(AwardBookResponse request) {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.updateAwardBook(request));
    }

    @Override
    public ResponseEntity<AwardBookResponse> getAwardBookByRef(String refAward) {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.getAwardBookByRef(refAward));
    }

    @Override
    public ResponseEntity<List<AwardBookResponse>> deleteAwardBook(String refAward) {
        return  ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.deleteAwardBook(refAward));
    }

    @Override
    public ResponseEntity<List<AwardBookResponse>> getAllAwardsBook() {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.getAllAwards());
    }

    @Override
    public ResponseEntity<List<Demand>> getDemandsUserLogged(String refUser) {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.getDemandsUserLogged(refUser));
    }

    @Override
    public ResponseEntity<Page<Demand>> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType) {
        return ResponseEntity.status(HttpStatus.OK).body(awardBookProcess.searchDemand(pageNumber,pageSize,sortDirection,sortBy,status,decision_date,demandOwnerFirstName,demandOwnerLastName,awardType));
    }

    @Override
    public ResponseEntity<Resource> exportDemands() {
        String filename = "demandsBookAward.xlsx";
        InputStreamResource file = new InputStreamResource(awardBookProcess.exportDemandData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public ResponseEntity<Void> importPublications(@RequestPart(name = "file") MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.OK).body( awardBookProcess.saveBooksExcel(multipartFile));
    }

    @Override
    public ResponseEntity<Resource> exportPublicationsData() {
        String filename = "publications.xlsx";
        InputStreamResource file = new InputStreamResource(awardBookProcess.exportPublicationsData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public ResponseEntity<Void> importAwardsObtained(MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.OK).body( awardBookProcess.saveAwardsExcel(multipartFile));

    }

    @Override
    public ResponseEntity<Resource> exportAwardObtained() {
        String filename = "awardObtained.xlsx";
        InputStreamResource file = new InputStreamResource(awardBookProcess.exportAwardObtainedData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

}
