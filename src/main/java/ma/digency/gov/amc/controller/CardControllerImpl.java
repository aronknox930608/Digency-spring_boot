package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.DocumentTypeResponse;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.card.*;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountResponse;
import ma.digency.gov.amc.process.CardProcess;
import ma.digency.gov.amc.process.DocumentProcess;
import ma.digency.gov.amc.utils.CardPage;
import ma.digency.gov.amc.utils.DemandPage;
import ma.digency.gov.amc.utils.SearchUtils;
import ma.digency.gov.amc.utils.searching.CardSearchCriteria;
import ma.digency.gov.amc.utils.searching.DemandPlanningCardCriteria;
import ma.digency.gov.amc.utils.searching.DemandSearchCriteria;
import ma.digency.gov.amc.utils.DemandPlanningPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name="carteArtist")
public class CardControllerImpl implements CardController{
    @Autowired
    CardProcess cardProcess;
    @Autowired
    DocumentProcess documentProcess;

    @Override
    public ResponseEntity<ArtistAccountCardResponse> createArtistAccount(ArtistAccountCardRequest artistAccountCardRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardProcess.createArtistAccount(artistAccountCardRequest));

    }

    @Override
    public ResponseEntity<CardResponse> createCard(String refArtistAccount, CardRequest cardRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardProcess.createCard(refArtistAccount,cardRequest));
    }


    @Override
    public ResponseEntity<Boolean> isPeriodOpenForDemandCard() {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.isPeriodOpenForDemandCard());
    }


    @Override
    public ResponseEntity<DemandCardResponse> createDemand(String refArtistAccount, DemandCardRequest demandRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardProcess.createDemand(refArtistAccount,demandRequest));
    }


    @Override
    public ResponseEntity<ArtistAccountCardResponse> getAuthenticatedAccount(String email) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.getAccount(email));
    }


    @Override
    public ResponseEntity<ArtistAccountCardResponse> getArtistAccount(String refArtistAccount) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.getArtistAccount(refArtistAccount));
    }


    @Override
    public ResponseEntity<Void> deleteDemandCard(String refDemand) {

        cardProcess.deleteDemandCard(refDemand);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @Override
    public ResponseEntity<CardResponse> retiredCard(String refCard) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.retiredCard(refCard));
    }


    @Override
    public ResponseEntity<Void> deleteCard(String refCard) {
        cardProcess.deleteCard(refCard);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @Override
    public ResponseEntity<DemandCardResponse> getDemandByRefDemand(String refDemand) {

        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.getDemandByRefDemand(refDemand));
    }

    @Override
    public ResponseEntity<DemandCardResponse> updateDemand(DemandCardRequest demandRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.updateDemand(demandRequest));
    }


    @Override
    public ResponseEntity<ArtistAccountCardResponse> updateArtistAccount(ArtistAccountCardRequest artistAccountRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.updateArtistAccount(artistAccountRequest));

    }


    @Override
    public ResponseEntity<PageableResponse<DemandCardResponse>> allDemands(Integer page, Integer limit) {

        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.allDemands(SearchUtils.createPageable(page,limit)));
    }

    @Override
    public ResponseEntity<PageableResponse<CardResponse>> getAllCards(Integer page, Integer limit) {

        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.getAllCards(SearchUtils.createPageable(page,limit)));
    }

    @Override
    public void  uploadListDocument(String documentType, String refObject, String refParent, MultipartFile[] multipartFile) {

        Arrays.asList(multipartFile)
                .stream()
                .map(file -> documentProcess.saveDocument(documentType,file, refObject,refParent))
                .collect(Collectors.toList());


    }

    @Override
    public List<DocumentTypeResponse> updateDocumentOfDemand(MultipartFile multipartFile, String refDemand,String refDocument) {

        documentProcess.updateDocument(multipartFile,refDocument);
        return documentProcess.findDocuments(refDemand);

    }

    @Override
    public ResponseEntity<List<ArtistAccountResponse>> ImportExcelData(MultipartFile file) {

        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.importExcelData(file));
    }

   public ResponseEntity<List<CardResponse>> importArtistCards(MultipartFile file) {

        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.importArtistCards(file));
    }

    @Override
    public ResponseEntity<Resource> exportArtistData(){
        String filename = "artists.xlsx";
        InputStreamResource file = new InputStreamResource(cardProcess.exportArtistData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
    @Override
    public ResponseEntity<Resource> exportArtistCard(){
        String filename = "cards.xlsx";
        InputStreamResource file = new InputStreamResource(cardProcess.exportArtistCard());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }


    @Override
    public ResponseEntity<Page<DemandCardResponse>> getDemandsWithSearchCriteria(DemandPage demandPage, DemandSearchCriteria demandSearchCriteria) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.getAllDemands(demandPage,demandSearchCriteria));
    }


    @Override
    public ResponseEntity<Page<CardResponse>> getCardsWithSearchCriteria(CardPage cardPage, CardSearchCriteria cardSearchCriteria) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.getAllCards(cardPage,cardSearchCriteria));

    }


    @Override
    public ResponseEntity<List<DemandCardResponse>> getDemandsByRefArtistAccount(String refArtistAccount) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.getDemandsByRefArtistAccount(refArtistAccount));
    }


    @Override
    public ResponseEntity<List<DemandCardResponse>> getAllDemands() {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.getDemands());

    }


    @Override
    public ResponseEntity<DemandPlanningResponse> createDemandPlanning(DemandPlanningRequest demandPlanningRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.createDemandPlanning(demandPlanningRequest));
    }


    @Override
    public ResponseEntity<Void> deleteDemandPlanning(String refDemandPlanning) {
        cardProcess.deleteDemandPlanning(refDemandPlanning);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @Override
    public ResponseEntity<DemandPlanningResponse> updateDemandPlanning(DemandPlanningRequest demandPlanningRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.updateDemandPlanning(demandPlanningRequest));
    }


    @Override
    public ResponseEntity<PageableResponse<DemandPlanningResponse>> listPlannings(Integer page, Integer limit) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.getAllPlannings(SearchUtils.createPageable(page,limit)));
    }


    @Override
    public ResponseEntity<Page<DemandPlanningResponse>> getPlanningWithSearchCriteria(DemandPlanningPage demandPlanning, DemandPlanningCardCriteria demandPlanningCardCriteria) {
        return ResponseEntity.status(HttpStatus.OK).body(cardProcess.getPlanningWithSearchCriteria(demandPlanning,demandPlanningCardCriteria));

    }
}



