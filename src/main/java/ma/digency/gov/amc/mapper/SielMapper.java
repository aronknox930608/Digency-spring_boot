package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.*;
import ma.digency.gov.amc.dto.siel.*;
import ma.digency.gov.amc.repository.entity.siel.*;

import java.util.List;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface SielMapper {

    PublicationResponse publicationToPublicationResponse(Publication publication);

    Publication publicationResponseToPublication(PublicationResponse response);

    Publication updatePublicationFromPublicationResponse(PublicationResponse response, Publication publication);

    List<PublicationResponse> publicationToPublicationResponse(List<Publication> publication);

    List<ActivityProposalResponse> activitiesToActivitiesResponse(List<ActivityProposal> activities);

    List<Publication> publicationResponseToPublication(List<PublicationResponse> publication);

    Exhibitor exhibitorRequestToExhibitor(ExhibitorRequest exhibitorRequest);

    Exhibitor exhibitorResponseToExhibitor(ExhibitorResponse exhibitorRequest);


    ExhibitorResponse exhibitorToExhibitorResponse(Exhibitor exhibitor);

    @Maps(withCustomFields = {
            @Field(value = {"timeVisit"}, withCustom = CustomDateMapper.class)
    })
    BookingSchoolResponse bookingSchoolToBookingSchoolResponse(BookingSchool bookingSchool);

    @Maps(withCustomFields = {
            @Field(value = {"timeVisit"}, withCustom = CustomDateMapper.class)
    })
    BookingSchool bookingSchoolRequestToBookingSchool(BookingSchoolRequest request);

    @Maps(withCustomFields = {
            @Field(value = {"timeVisit"}, withCustom = CustomDateMapper.class)
    })
    BookingSchool updateBookingSchoolFromBookingSchoolRequest(BookingSchoolRequest request, BookingSchool bookingSchool);

    Publication publicationRequestToPublication(PublicationRequest response);

    ActivityProposal updateActivityFromActivityMapResponse(ActivityProposalResponse activityDto, ActivityProposal activityProposal);

    ActivityProposal activityResponseToActivity(ActivityProposalResponse activityDto);

    PublisherRepresented updatePublisherFromPublisherResponse(PublisherRepresentedResponse publisherDto, PublisherRepresented publisherRepresented);

    PublisherRepresented updatePublisherFromPublisherRequest(PublisherRepresentedRequest publisherDto, PublisherRepresented publisherRepresented);


    PublisherRepresented publisherRequestToPublisher(PublisherRepresentedRequest publisherDto);

    List<PublisherRepresentedRequest> publishersToPublisherRequests(List<PublisherRepresented> publishers);

    PublisherRepresentedRequest publisherToPublisherRequest(PublisherRepresented publisher);

    PublisherRepresentedResponse publisherRequestToPublisherResponse(PublisherRepresentedRequest publisher);

    PublisherRepresented publisherResponseToPublisher(PublisherRepresentedResponse publisherDto);


    ActivityProposal activityRequestToActivity(ActivityProposalRequest response);

    ActivityProposalResponse activityToActivityResponse(ActivityProposal activityProposal);

    List<PublisherRepresentedResponse> publishersToPublishersResponse(List<PublisherRepresented> publishers);

    PublisherRepresentedResponse publisherToPublisherResponse(PublisherRepresented publisherRepresented);

    BookingStandResponse bookingStandToBookingStandResponse(BookingStand bookingStand);

    BookingStand bookingStandRequestToBookingStand(BookingStandRequest request);

    BookingStand updateBookingStandFromBookingStandRequest(BookingStandRequest request, BookingStand bookingStand);

    Exhibitor updatexhibitorResponseToExhibitor(ExhibitorResponse request, Exhibitor find);

    Edition editionRequestToEdition(EditionRequest request);

    EditionResponse editionToEditionResponse(Edition saveEdition);

    Edition updateEditionFromEditionRequest(EditionRequest request, Edition findEdition);

    BookingStand updateBookingStandFromBookingStandResponse(BookingStandResponse response, BookingStand bookingStand);

    ForeignRepresented updateForeignFromForeignRequest(ForeignRepresentedRequest request, ForeignRepresented publisher);

    ForeignRepresentedResponse foreignToForeignResponse(ForeignRepresented update);

    ForeignRepresented foreignRequestToForeign(ForeignRepresentedRequest request);
}
