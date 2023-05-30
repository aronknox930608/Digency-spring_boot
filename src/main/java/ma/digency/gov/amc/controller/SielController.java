package ma.digency.gov.amc.controller;

import io.swagger.annotations.ApiParam;
import ma.digency.gov.amc.dto.NotificationResponse;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.siel.*;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RequestMapping("siel/")
public interface SielController {

    @GetMapping("exhibitors/{refExhibitor}/publications")
    ResponseEntity<PageableResponse<PublicationResponse>> getAllPublications(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @RequestParam String statusEnum ,@RequestParam Integer page, @RequestParam Integer size);

    @PutMapping("exhibitors/{refExhibitor}/publications")
    ResponseEntity<List<PublicationResponse>> updateOrCreatePublications
            (@PathVariable("refExhibitor") @NotBlank String refParent,
             @RequestBody List<PublicationResponse> publications);

    @PutMapping("exhibitors/{refExhibitor}/validate-subscription")
    ResponseEntity<Void> validatedSubscription(@PathVariable("refExhibitor") @NotBlank String refParent);

    @GetMapping("editions/open-subscription")
    ResponseEntity<Boolean> isOpenForSubscription();


    @PutMapping("exhibitors/{refExhibitor}/publications/{refPublication}")
    ResponseEntity<PublicationResponse> updatePublication(
            @PathVariable("refExhibitor") @NotBlank String refParent,
            @PathVariable("refPublication") @NotBlank String refPublication,
            @RequestBody @Validated PublicationResponse response);

    @GetMapping("exhibitors/{refExhibitor}/notifications")
    ResponseEntity<NotificationResponse> getNotification(
            @PathVariable("refExhibitor") @NotBlank String refParent);

    @DeleteMapping("exhibitors/{refExhibitor}/publications/{refPublication}")
    ResponseEntity<Void> deletePublication(
            @PathVariable("refExhibitor") @NotBlank String refParent,
            @PathVariable("refPublication") @NotBlank String refPublication);

    @GetMapping("exhibitors/{refExhibitor}/publications/{refPublication}")
    ResponseEntity<PublicationResponse> getPublicationByRefPublication(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @PathVariable("refPublication") @NotBlank String refPublication);

    @PostMapping("exhibitors/{refExhibitor}/publications")
    ResponseEntity<PublicationResponse> createPublication(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @RequestBody @Validated PublicationRequest response);
    ///////// activities proposal ////////////////////////

    @PutMapping("exhibitors/{refExhibitor}/activities-proposal")
    ResponseEntity<List<ActivityProposalResponse>> updateOrCreateActivitiesProposal(
            @PathVariable("refExhibitor") @NotBlank String refParent,
            @RequestBody List<ActivityProposalResponse> publications);

    @PostMapping("exhibitors/{refExhibitor}/activities-proposal")
    ResponseEntity<ActivityProposalResponse> createActivityProposal(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @RequestBody @Validated ActivityProposalRequest response);

    @DeleteMapping("exhibitors/{refExhibitor}/activities-proposal/{refActivityProposal}")
    ResponseEntity<Void> deleteActivityProposal(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @PathVariable("refActivityProposal") @NotBlank String refActivityProposal);

    @GetMapping("exhibitors/{refExhibitor}/activities-proposal/{refActivityProposal}")
    ResponseEntity<ActivityProposalResponse> getActivityProposal(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @PathVariable("refActivityProposal") @NotBlank String refActivityProposal);

    @GetMapping("exhibitors/{refExhibitor}/activities-proposal")
    ResponseEntity<List<ActivityProposalResponse>> getAllActivitiesProposal(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor);

    @GetMapping("exhibitors/{refExhibitor}/activities-proposal/")
    ResponseEntity<PageableResponse<ActivityProposalResponse>> getAllActivitiesProposalPage(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,@RequestParam Integer page,
            @RequestParam Integer size);


    ///////// Publishers Represented ////////////////////////

    @PutMapping("exhibitors/{refExhibitor}/publishers-represented")
    ResponseEntity<List<PublisherRepresentedResponse>> updateOrCreatePublishers(
            @PathVariable("refExhibitor") @NotBlank String refParent,
            @RequestBody @NotEmpty List<PublisherRepresentedResponse> publications);

    @PutMapping("exhibitors/{refExhibitor}/publishers-represented/adapted")
    ResponseEntity<List<PublisherRepresentedResponse>> updateOrCreatePublishersAdapted(
            @PathVariable("refExhibitor") @NotBlank String refParent,
            @RequestBody @NotEmpty List<PublisherRepresentedRequest> publications);

    @PostMapping("exhibitors/{refExhibitor}/publishers-represented")
    ResponseEntity<PublisherRepresentedResponse> createPublishersRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @RequestBody  PublisherRepresentedRequest response);

    @PutMapping("exhibitors/{refExhibitor}/publishers-represented/{refPublisherRepresented}")
    ResponseEntity<PublisherRepresentedResponse> updatePublisherRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @PathVariable("refPublisherRepresented") @NotBlank String refPublisherRepresented,
            @RequestBody @Validated PublisherRepresentedRequest response);

    @DeleteMapping("exhibitors/{refExhibitor}/publishers-represented/{refPublisherRepresented}")
    ResponseEntity<Void> deletePublisherRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @PathVariable("refPublisherRepresented") @NotBlank String refPublisherRepresented);

    @GetMapping("exhibitors/{refExhibitor}/publishers-represented/{refPublisherRepresented}")
    ResponseEntity<PublisherRepresentedResponse> getPublisherRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @PathVariable("refPublisherRepresented") @NotBlank String refPublisherRepresented);

    @GetMapping("exhibitors/{refExhibitor}/publishers-represented")
    ResponseEntity<List<PublisherRepresentedResponse>> getAllPublisherRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor);

    @GetMapping("exhibitors/{refExhibitor}/publishers-represented/")
    ResponseEntity<PageableResponse<PublisherRepresentedResponse>> getAllPublisherRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,@RequestParam Integer page,
            @RequestParam Integer size);


    //////////////////// Booking Stand ///////////////////////////////////

    @PostMapping("exhibitors/{refExhibitor}/booking-stand")
    ResponseEntity<BookingStandResponse> createBookingStand(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @RequestBody @Validated BookingStandRequest response);


    @GetMapping("exhibitors/{refExhibitor}/booking-stand")
    ResponseEntity<BookingStandResponse> getBookingStand(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor);

    @GetMapping("exhibitors/bookings-stand")
    ResponseEntity<PageableResponse<ExhibitorsFullData>> getAllBookingStand(@RequestParam String statusEnum ,
                                                                              @RequestParam Integer page,
                                                                            @RequestParam Integer size);

    @PutMapping("exhibitors/{refExhibitor}/booking-stand/{refBookingStand}")
    ResponseEntity<BookingStandResponse> updateBookingStand(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @RequestBody @Validated BookingStandResponse request,
            @PathVariable("refBookingStand") @NotBlank String refBookingStand);


    @PostMapping("exhibitors/")
    ExhibitorResponse createExhibitor(@RequestBody @Validated ExhibitorRequest response);

    @GetMapping("exhibitors/check/{email}")
    ExhibitorResponse checkExibitorUpdate(@PathVariable("email") @NotBlank String email);

    @PutMapping("exhibitors/")
    ResponseEntity<ExhibitorResponse> updateExhibitor(@RequestBody @Validated ExhibitorResponse request);

    @DeleteMapping("exhibitors/{refExhibitor}")
    ResponseEntity<Void> deleteExhibitor(@PathVariable("refExhibitor") @NotBlank String refExhibitor);

    @GetMapping("exhibitors/{refExhibitor}")
    ResponseEntity<ExhibitorResponse> getExhibitor(@PathVariable("refExhibitor") @NotBlank String refExhibitor);

    @GetMapping("exhibitors/")
    ResponseEntity<PageableResponse<ExhibitorResponse>> getAllExhibitor(@RequestParam String statusEnum ,@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("exhibitors/validated")
    ResponseEntity<PageableResponse<ExhibitorResponse>> getAllExhibitorToValidate(@RequestParam Integer page, @RequestParam Integer size);

    @PostMapping("booking-schools/")
    ResponseEntity<BookingSchoolResponse> createBookingSchool(@RequestBody @Validated BookingSchoolRequest booking);

    @PutMapping("booking-schools/{refBookingSchool}")
    ResponseEntity<BookingSchoolResponse> updateBookingSchool(@RequestBody @Validated BookingSchoolRequest booking,
                                                              @PathVariable("refBookingSchool") String refBooking);

    @DeleteMapping("booking-schools/{refBookingSchool}")
    ResponseEntity<Void> deleteBookingSchool(@PathVariable("refBookingSchool") @NotBlank String refBooking);

    @GetMapping("booking-schools/{refBookingSchool}")
    ResponseEntity<BookingSchoolResponse> getBookingSchool(@PathVariable("refBookingSchool") @NotBlank String refBooking);

    @GetMapping("booking-schools/")
    ResponseEntity<PageableResponse<BookingSchoolResponse>> getAllBookingSchools(@RequestParam String statusEnum ,@RequestParam Integer page, @RequestParam Integer size);

    @PutMapping("{nameEntity}/update-status/{refObject}")
    ResponseEntity<Void> updateEntityStatus(
            @ApiParam(example = "les valeurs possible : booking_school /publication / exhibitor")
                @PathVariable("nameEntity") @NotBlank String nameEntity,
            @PathVariable("refObject")  @NotBlank String refObject,
            @ApiParam(example = "les valeurs possible :pending/ rejected/ accepted") @RequestParam @NotBlank String statusEnum);

    @PostMapping("editions/")
    ResponseEntity<EditionResponse> createEdition(@RequestBody @Validated EditionRequest request);

    @GetMapping("editions/")
    ResponseEntity<List<EditionResponse>> getAllEdition();

    @PutMapping("editions/{refEdition}")
    ResponseEntity<EditionResponse> updateEdition(@PathVariable("refEdition") @NotBlank String refEdition,
                                                        @RequestBody @Validated EditionRequest request);

    @DeleteMapping("editions/{refEdition}")
    ResponseEntity<Void> deleteEdition(@PathVariable("refEdition") @NotBlank String refEdition);


    @GetMapping("editions/{refEdition}/publications")
    ResponseEntity<EditionGeneralInformationResponse> getAllPublicationByEdition(@PathVariable("refEdition") @NotBlank String refEdition
            , @RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("editions/{refEdition}/exhibitors")
    ResponseEntity<ExhibitorGeneralInformationResponse> getAllExhibitorByEdition(@PathVariable("refEdition") @NotBlank String refEdition
            , @RequestParam Integer page, @RequestParam Integer size,@RequestParam String country);

    //user
    @GetMapping("editions/{refEdition}/exhibitors/{refExhibitor}")
    ResponseEntity<EditionGeneralInformationResponse> getAllExhibitorPublicationByEdition(@PathVariable("refEdition") @NotBlank String refEdition
            , @PathVariable("refExhibitor") @NotBlank String refExhibitor, @RequestParam Integer page, @RequestParam Integer size);


    @GetMapping("exhibitors/{refExhibitor}/edition-publications")
    ResponseEntity<List<EditionGeneralInformationResponse>> getAllExhibitorPublication(@PathVariable("refExhibitor") @NotBlank String refExhibitor);


    @GetMapping(path = "download/publication-model", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Resource> downloadDocument();

    @GetMapping(path = "download/list-foreign-exhibitor", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Resource> downloadForeignDocument();



    //////////////////////////////////Foriegn Publisher////////////////////////////
    @PostMapping("exhibitors/{refExhibitor}/foreign-represented")
    ResponseEntity<ForeignRepresentedResponse> createForeignRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @RequestBody  ForeignRepresentedRequest response);

    @PutMapping("exhibitors/{refExhibitor}/foreign-represented/{refForeignRepresented}")
    ResponseEntity<ForeignRepresentedResponse> updateForeignRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @PathVariable("refForeignRepresented") @NotBlank String refForeignRepresented,
            @RequestBody @Validated ForeignRepresentedRequest response);

    @DeleteMapping("exhibitors/{refExhibitor}/foreign-represented/{refForeignRepresented}")
    ResponseEntity<Void> deleteForeignRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @PathVariable("refForeignRepresented") @NotBlank String refForeignRepresented);

    @GetMapping("exhibitors/{refExhibitor}/foreign-represented/{refForeignRepresente}")
    ResponseEntity<ForeignRepresentedResponse> getForeignRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor,
            @PathVariable("refForeignRepresented") @NotBlank String refForeignRepresented);

    @GetMapping("exhibitors/{refExhibitor}/foreign-represented/")
    ResponseEntity<List<ForeignRepresentedResponse>> getAllForeignRepresented(
            @PathVariable("refExhibitor") @NotBlank String refExhibitor);

}
