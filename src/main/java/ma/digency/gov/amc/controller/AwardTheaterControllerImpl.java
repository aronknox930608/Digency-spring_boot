package ma.digency.gov.amc.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.process.AwardBookProcess;
import ma.digency.gov.amc.process.AwardHassan2Process;
import ma.digency.gov.amc.process.AwardTheaterProcess;
import ma.digency.gov.amc.process.DocumentProcess;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.RoleTheater;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "awardTheater/")
public class AwardTheaterControllerImpl implements AwardTheaterController{

    @Autowired
    private AwardTheaterProcess awardTheaterProcess;

    @Autowired
    private AwardBookProcess awardBookProcess;

    @Autowired
    private DocumentProcess documentProcess;

    @Autowired
    private AwardHassan2Process awardHassan2Process;

    @Override
    public ResponseEntity<OwnerPersonalInfoResponse> getArtistInformation(String refAccount) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.getArtist(refAccount));
    }

    @Override
    public String createOrUpdateArtistInformation(OwnerPersonalInfoResponse artistPersonalInfoResponse){
        return awardTheaterProcess.createOrUpdatePersonalInformation(artistPersonalInfoResponse);
    }

    @Override
    public TheaterPieceResponse addDemand(DemandPriceTheaterRequest request) {
        return awardTheaterProcess.addDemand(request);
    }

    @Override
    public String addParticipant(ParticipantRequest participantRequest) {
        return awardTheaterProcess.addParticipant(participantRequest);
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
    public ResponseEntity<DemandPriceTheaterResponse> getDemand(String refDemand) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.getDemand(refDemand));
    }

    @Override
    public ResponseEntity<String> updateDemand(DemandPriceTheaterResponse request) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.updateDemand(request));
    }

    @Override
    public ResponseEntity<List<ParticipantTheaterResponse>> listParticipants(String refTheaterPiece) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.listParticipants(refTheaterPiece));
    }

    @Override
    public ResponseEntity<ParticipantTheaterResponse> getParticipantByRef(String refParticipant) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.getParticipantByRef(refParticipant));
    }

    @Override
    public ResponseEntity<List<ParticipantTheaterResponse>> updateParticipant(ParticipantTheaterResponse request) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.updateParticipant(request));
    }

    @Override
    public ResponseEntity<List<ParticipantTheaterResponse>> deleteParticipant(String refParticipant) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.deleteParticipant(refParticipant));
    }

    @Override
    public ResponseEntity<List<DocumentDemandResponse>> getDocuments(String refTheaterPiece) {
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getDocuments(refTheaterPiece));
    }

    @Override
    public ResponseEntity<List<DocumentDemandResponse>> updateDocument(MultipartFile multipartFile, String refDocument) {
        documentProcess.updateDocument(multipartFile, refDocument);
        return ResponseEntity.status(HttpStatus.OK).body(awardHassan2Process.getDocuments(documentProcess.findDocumentByRef(refDocument).getRefObject()));
    }

    @Override
    public ResponseEntity<Void> deleteDemand(String refDemand) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.deleteDemand(refDemand));
    }

    @Override
    public ResponseEntity<List<TheaterPieceResponse>> getTheaterPiecesOfArtist(String cin) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.getTheaterPiecesOfArtist(cin));
    }

    @Override
    public ResponseEntity<TheaterPieceResponse> getTheaterPiece(String refTheaterPiece) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.getTheaterPiece(refTheaterPiece));
    }

    @Override
    public ResponseEntity<TheaterPieceResponse> updateTheaterPiece(TheaterPieceResponse request) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.updateTheaterPiece(request));
    }

    @Override
    public ResponseEntity<List<RoleTheater>> getRoleTheater() {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.getRoleTheater());
    }

    @Override
    public List<AwardTheaterResponse> addAwardTheater(AwardRequest awardTheaterRequest) {
        return awardTheaterProcess.addAwardTheater(awardTheaterRequest);
    }

    @Override
    public ResponseEntity<List<AwardTheaterResponse>> updateAwardTheater(AwardTheaterResponse request) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.updateAwardTheater(request));
    }

    @Override
    public ResponseEntity<AwardTheaterResponse> getAwardTheaterByRef(String refAward) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.getAwardTheaterByRef(refAward));
    }

    @Override
    public ResponseEntity<List<AwardTheaterResponse>> deleteAwardTheater(String refAward) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.deleteAwardTheater(refAward));
    }

    @Override
    public ResponseEntity<List<AwardTheaterResponse>> getAllAwardsTheater() {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.getAllAwardsTheater());
    }

    @Override
    public ResponseEntity<Page<Demand>> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.searchDemand(pageNumber,pageSize,sortDirection,sortBy,status,decision_date,demandOwnerFirstName,demandOwnerLastName,awardType));
    }

    @Override
    public ResponseEntity<PageableResponse<DemandAwardTheaterResponse>> getAllDemand(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.findAllDemand(SearchUtils.createPageable(page, size)));
    }

    @Override
    public ResponseEntity<List<Demand>> getDemandsUserLogged(String refUser) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.getDemandsUserLogged(refUser));
    }

    @Override
    public ResponseEntity<Resource> exportDemands() {
        String filename = "demandsTheaterAward.xlsx";
        InputStreamResource file = new InputStreamResource(awardTheaterProcess.exportDemandData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public ResponseEntity<Void> importTheaterPieces(MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.OK).body( awardTheaterProcess.saveTheaterPiecesExcel(multipartFile));

    }

    @Override
    public ResponseEntity<Resource> exportTheaterPieces() {
        String filename = "theaterPieces.xlsx";
        InputStreamResource file = new InputStreamResource(awardTheaterProcess.exportTheaterPiecesData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public ResponseEntity<Void> importParticipants(MultipartFile multipartFile, String refTheaterPiece) {
        return ResponseEntity.status(HttpStatus.OK).body( awardTheaterProcess.saveParticipantsExcel(multipartFile, refTheaterPiece));

    }

    @Override
    public ResponseEntity<Resource> exportParticipants(String refTheaterPiece) {
        String filename = "participants.xlsx";
        InputStreamResource file = new InputStreamResource(awardTheaterProcess.exportParticipantsData(refTheaterPiece));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public ResponseEntity<PageableResponse<DemandPriceTheaterListResponse>> getAllDemands(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(awardTheaterProcess.findAllDemands(SearchUtils.createPageable(page, size)));
    }

}
