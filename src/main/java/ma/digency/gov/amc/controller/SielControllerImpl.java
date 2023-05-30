package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.DocumentResponse;
import ma.digency.gov.amc.dto.NotificationResponse;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.siel.*;
import ma.digency.gov.amc.process.DocumentProcess;
import ma.digency.gov.amc.process.SielProcess;
import ma.digency.gov.amc.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Tag(name = "SIEL")
public class SielControllerImpl implements SielController {

    @Autowired
    private SielProcess sielProcess;

    @Autowired
    private DocumentProcess documentProcess;

    @Override
    public ResponseEntity<PageableResponse<PublicationResponse>> getAllPublications(String refExhibitor, String statusEnum, Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.findAllPublication(refExhibitor,statusEnum,
                        SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<List<PublicationResponse>> updateOrCreatePublications(String refExhibitor, List<PublicationResponse> publications) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updateOrCreatePublications(refExhibitor, publications));
    }

    @Override
    public ResponseEntity<Void> validatedSubscription(String refParent) {
        sielProcess.validatedSubscription(refParent);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Boolean> isOpenForSubscription() {
        return ResponseEntity.status(HttpStatus.OK).body(sielProcess.isOpenForSubscription());
    }

    @Override
    public ResponseEntity<PublicationResponse> updatePublication(String refExhibitor, String refPublication, PublicationResponse response) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updatePublication(refExhibitor, refPublication, response));
    }

    @Override
    public ResponseEntity<NotificationResponse> getNotification(String refParent) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getAllNotification(refParent));
    }

    @Override
    public ResponseEntity<Void> deletePublication(String refExhibitor, String refPublication) {
        sielProcess.deletePublication(refExhibitor, refPublication);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PublicationResponse> getPublicationByRefPublication(String refExhibitor, String refPublication) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getPublicationByRefPublication(refExhibitor, refPublication));
    }

    @Override
    public ResponseEntity<PublicationResponse> createPublication(String refExhibitor, PublicationRequest response) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.createPublication(refExhibitor, response));
    }

    @Override
    public ResponseEntity<List<ActivityProposalResponse>> updateOrCreateActivitiesProposal(
             String refExhibitor,  List<ActivityProposalResponse> activities) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updateOrCreateActivitiesProposal(refExhibitor, activities));
    }

    @Override
    public ResponseEntity<ActivityProposalResponse> createActivityProposal(
             String refExhibitor,  ActivityProposalRequest response) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.createActivityProposal(refExhibitor, response));
    }

    @Override
    public ResponseEntity<Void> deleteActivityProposal(
             String refExhibitor,  String refActivityProposal) {
        sielProcess.deleteActivityProposal(refExhibitor, refActivityProposal);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ActivityProposalResponse> getActivityProposal(
             String refExhibitor,  String refActivityProposal) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getActivityProposal(refExhibitor, refActivityProposal));
    }

    @Override
    public ResponseEntity<List<ActivityProposalResponse>> getAllActivitiesProposal(
             String refExhibitor) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getAllActivitiesProposal(refExhibitor));
    }

    @Override
    public ResponseEntity<PageableResponse<ActivityProposalResponse>> getAllActivitiesProposalPage(@NotBlank String refExhibitor, Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getAllActivitiesProposalPages(refExhibitor,SearchUtils.createPageable(page, size)));
    }

    @Override
    public ResponseEntity<List<PublisherRepresentedResponse>> updateOrCreatePublishers( String refExhibitor,  List<PublisherRepresentedResponse> publishers) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updateOrCreatePublisher(refExhibitor, publishers));
    }

    @Override
    public ResponseEntity<List<PublisherRepresentedResponse>> updateOrCreatePublishersAdapted(String refExhibitor, List<PublisherRepresentedRequest> publishers) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updateOrCreatePublisherAdapted(refExhibitor, publishers));
    }

    @Override
    public ResponseEntity<PublisherRepresentedResponse> createPublishersRepresented( String refExhibitor,  PublisherRepresentedRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.createPublisherRepresented(refExhibitor, request,null));
    }

    @Override
    public ResponseEntity<PublisherRepresentedResponse> updatePublisherRepresented(@NotBlank String refExhibitor, @NotBlank String refPublisherRepresented, PublisherRepresentedRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updatePublisherRepresented(refExhibitor,refPublisherRepresented, request,null));
    }

    @Override
    public ResponseEntity<Void> deletePublisherRepresented( String refExhibitor, String refPublisherRepresented) {
        sielProcess.deletePublisherRepresented(refExhibitor, refPublisherRepresented);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PublisherRepresentedResponse> getPublisherRepresented( String refExhibitor,  String refPublisherRepresented) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getPublisherRepresented(refExhibitor, refPublisherRepresented));
    }

    @Override
    public ResponseEntity<List<PublisherRepresentedResponse>> getAllPublisherRepresented( String refExhibitor) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getAllPublisherRepresented(refExhibitor));
    }

    @Override
    public ResponseEntity<PageableResponse<PublisherRepresentedResponse>> getAllPublisherRepresented(String refExhibitor, Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getAllPublisherRepresentedPage(refExhibitor,SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<BookingStandResponse> createBookingStand( String refExhibitor,  BookingStandRequest response) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.createBookingStand(refExhibitor, response));
    }

    @Override
    public ResponseEntity<BookingStandResponse> getBookingStand(String refExhibitor) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getBookingStand(refExhibitor));
    }

    @Override
    public ResponseEntity<PageableResponse<ExhibitorsFullData>> getAllBookingStand(String statusEnum, Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getAllBookingStand(statusEnum,SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<BookingStandResponse> updateBookingStand( String refExhibitor, BookingStandResponse request,  String refBookingStand) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updateBookingStand(refExhibitor, request, refBookingStand));
    }

    @Override
    public ExhibitorResponse createExhibitor(ExhibitorRequest exhibitorRequest) {

        return sielProcess.createExhibitor(exhibitorRequest);

    }

    @Override
    public ExhibitorResponse checkExibitorUpdate(String email) {
        return sielProcess.checkUpdateWithReturn(email);
    }

    @Override
    public ResponseEntity<ExhibitorResponse> updateExhibitor(ExhibitorResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updateExhibitor(request));
    }

    @Override
    public ResponseEntity<Void> deleteExhibitor(String refExhibitor) {
        sielProcess.deleteExhibitor(refExhibitor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ExhibitorResponse> getExhibitor(String refExhibitor) {
        return ResponseEntity.status(HttpStatus.OK).body(sielProcess.findExhibitor(refExhibitor));
    }

    @Override
    public ResponseEntity<PageableResponse<ExhibitorResponse>> getAllExhibitor(String statusEnum, Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(sielProcess.findAllExhibitors(statusEnum,SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<PageableResponse<ExhibitorResponse>> getAllExhibitorToValidate(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(sielProcess.findAllExhibitorsToValidate(SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<BookingSchoolResponse> createBookingSchool(BookingSchoolRequest booking) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.createBookingSchool(booking));
    }

    @Override
    public ResponseEntity<BookingSchoolResponse> updateBookingSchool(BookingSchoolRequest booking, String refBooking) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updateBookingSchool(refBooking, booking));
    }

    @Override
    public ResponseEntity<Void> deleteBookingSchool(String refBooking) {
        sielProcess.deleteBookingSchool(refBooking);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @Override
    public ResponseEntity<BookingSchoolResponse> getBookingSchool(String refBooking) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getBookingSchoolByRefBookingSchool(refBooking));
    }

    @Override
    public ResponseEntity<PageableResponse<BookingSchoolResponse>> getAllBookingSchools(String statusEnum, Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.findAllBookingSchool(statusEnum, SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<Void> updateEntityStatus(String nameEntity, String refObject, String statusEnum) {
        sielProcess.updateEntityStatus(nameEntity, statusEnum, refObject);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @Override
    public ResponseEntity<EditionResponse> createEdition(EditionRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.createEdition(request));
    }

    @Override
    public ResponseEntity<List<EditionResponse>> getAllEdition() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.findALlEdition());
    }

    @Override
    public ResponseEntity<EditionResponse> updateEdition(String refBooking, EditionRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updateEdition(refBooking, request));
    }

    @Override
    public ResponseEntity<Void> deleteEdition(String refEdition) {
        sielProcess.deleteEdition(refEdition);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<EditionGeneralInformationResponse> getAllPublicationByEdition(@NotBlank String refEdition, Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.finPublicationByEdition(refEdition,SearchUtils.createPageable(page, size)));
    }

    @Override
    public ResponseEntity<ExhibitorGeneralInformationResponse> getAllExhibitorByEdition(@NotBlank String refEdition, Integer page, Integer size, String country) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.findAllExhibitorsByEdition(refEdition,SearchUtils.createPageable(page,size),country));
    }

    @Override
    public ResponseEntity<EditionGeneralInformationResponse> getAllExhibitorPublicationByEdition(@NotBlank String refEdition, @NotBlank String refExhibitor, Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.findPublicationOfExhibitorByEdition(refEdition, SearchUtils.createPageable(page,size),refExhibitor));
    }

    @Override
    public ResponseEntity<List<EditionGeneralInformationResponse>> getAllExhibitorPublication(@NotBlank String refExhibitor) {
        return ResponseEntity.status(HttpStatus.OK).body(sielProcess.findAllExhibitorsPublication(refExhibitor));
    }

    @Override
    public ResponseEntity<Resource> downloadDocument() {
        DocumentResponse file = documentProcess.uploadPublicationModel();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @Override
    public ResponseEntity<Resource> downloadForeignDocument() {
        DocumentResponse file = documentProcess.uploadForeignExhibitor();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @Override
    public ResponseEntity<ForeignRepresentedResponse> createForeignRepresented(String refExhibitor, ForeignRepresentedRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.createForeignRepresented(refExhibitor, request));    }

    @Override
    public ResponseEntity<ForeignRepresentedResponse> updateForeignRepresented( String refExhibitor, String refForeignRepresented, ForeignRepresentedRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.updateForeignRepresented(refExhibitor,refForeignRepresented, request));

    }

    @Override
    public ResponseEntity<Void> deleteForeignRepresented( String refExhibitor, String refForeignRepresented) {
        sielProcess.deleteForeignRepresented(refExhibitor, refForeignRepresented);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ForeignRepresentedResponse> getForeignRepresented( String refExhibitor, String refForeignRepresented) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getForeignRepresented(refExhibitor, refForeignRepresented));
    }

    @Override
    public ResponseEntity<List<ForeignRepresentedResponse>> getAllForeignRepresented(String refExhibitor) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sielProcess.getAllForeignRepresentedPage(refExhibitor));
    }

}
