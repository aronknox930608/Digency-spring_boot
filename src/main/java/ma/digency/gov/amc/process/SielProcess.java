package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.NotificationResponse;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.siel.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SielProcess {

    void deletePublication(String refExhibitor, String refPublication);

    PublicationResponse updatePublication(String refExhibitor, String refPublication, PublicationResponse response);

    PageableResponse<PublicationResponse> findAllPublication(String refExhibitor, String statusEnum, Pageable pageable);

    List<PublicationResponse> updateOrCreatePublications(String refExhibitor, List<PublicationResponse> publications);

    PublicationResponse getPublicationByRefPublication(String refExhibitor, String refPublication);

    PublicationResponse createPublication(String refExhibitor, PublicationRequest response);

    ExhibitorResponse createExhibitor(ExhibitorRequest exhibitorRequest);

    ExhibitorResponse updateExhibitor(ExhibitorResponse request);

    ExhibitorResponse checkUpdateWithReturn(String email);

    void deleteExhibitor(String refExhibitor);


    BookingSchoolResponse getBookingSchoolByRefBookingSchool(String refBookingSchool);

    BookingSchoolResponse updateBookingSchool(String refBookingSchool, BookingSchoolRequest request);

    BookingSchoolResponse createBookingSchool(BookingSchoolRequest request);

    void deleteBookingSchool(String refBookingRequest);

    PageableResponse<BookingSchoolResponse> findAllBookingSchool(String statusEnum, Pageable pageable);


    void updateEntityStatus(String refBooking, String statusEnum, String refObject);

    ExhibitorResponse findExhibitor(String refExhibitor);

    PageableResponse<ExhibitorResponse> findAllExhibitors(String statusEnum , Pageable pageable);

    PageableResponse<ExhibitorResponse> findAllExhibitors(Pageable pageable);


    List<ActivityProposalResponse> updateOrCreateActivitiesProposal(String refExhibitor, List<ActivityProposalResponse> activities);

    ActivityProposalResponse createActivityProposal(String refExhibitor, ActivityProposalRequest response);

    void deleteActivityProposal(String refExhibitor, String refActivityProposal);

    ActivityProposalResponse getActivityProposal(String refExhibitor, String refActivityProposal);

    List<ActivityProposalResponse> getAllActivitiesProposal(String refExhibitor);

    List<PublisherRepresentedResponse> updateOrCreatePublisher(String refExhibitor, List<PublisherRepresentedResponse> reponse);

    PublisherRepresentedResponse createPublisherRepresented(String refExhibitor, PublisherRepresentedRequest request, MultipartFile multipartFile);

    List<PublisherRepresentedResponse> updateOrCreatePublisherAdapted(String refExhibitor, List<PublisherRepresentedRequest> requests);

    void deletePublisherRepresented(String refExhibitor, String refPublisherRepresented);

    PublisherRepresentedResponse getPublisherRepresented(String refExhibitor, String refPublisherRepresented);

    List<PublisherRepresentedResponse> getAllPublisherRepresented(String refExhibitor);

    BookingStandResponse createBookingStand(String refExhibitor, BookingStandRequest request);

    BookingStandResponse getBookingStand(String refExhibitor);

    BookingStandResponse updateBookingStand(String refExhibitor, BookingStandResponse request, String refBookingStand);

    NotificationResponse getAllNotification(String refParent);

    EditionResponse createEdition(EditionRequest request);

    List<EditionResponse> findALlEdition();

    EditionResponse updateEdition(String refBooking, EditionRequest request);

    void deleteEdition(String refEdition);

    Boolean isOpenForSubscription();

    void validatedSubscription(String refParent);

    PageableResponse<ExhibitorsFullData> getAllBookingStand(String statusEnum, Pageable pageable);

    List<EditionGeneralInformationResponse> findAllExhibitorsPublication(String refExhibitor);

    EditionGeneralInformationResponse findPublicationOfExhibitorByEdition(String refEdition, Pageable pageable, String reExhibitor);

    ExhibitorGeneralInformationResponse findAllExhibitorsByEdition(String refEdition, Pageable pageable, String country);

    EditionGeneralInformationResponse finPublicationByEdition(String refEdition, Pageable pageable);

    PageableResponse<ActivityProposalResponse> getAllActivitiesProposalPages(String refExhibitor, Pageable pageable);

    PageableResponse<PublisherRepresentedResponse> getAllPublisherRepresentedPage(String refExhibitor, Pageable pageable);

    PageableResponse<ExhibitorResponse> findAllExhibitorsToValidate(Pageable pageable);

    PublisherRepresentedResponse updatePublisherRepresented(String refExhibitor, String refPublisherRepresented, PublisherRepresentedRequest request, MultipartFile multipartFile);

    List<ForeignRepresentedResponse> getAllForeignRepresentedPage(String refExhibitor);

    ForeignRepresentedResponse getForeignRepresented(String refExhibitor, String refForeignRepresented);

    void deleteForeignRepresented(String refExhibitor, String refForeignRepresented);

    ForeignRepresentedResponse updateForeignRepresented(String refExhibitor, String refForeignRepresented, ForeignRepresentedRequest request);

    ForeignRepresentedResponse createForeignRepresented(String refExhibitor, ForeignRepresentedRequest request);
}
