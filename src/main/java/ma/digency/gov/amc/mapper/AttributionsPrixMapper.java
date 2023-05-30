package ma.digency.gov.amc.mapper;


import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.Document;
import ma.digency.gov.amc.repository.entity.Price;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.Award;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardType;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;


@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)

public interface AttributionsPrixMapper {

    OwnerHandWritten ownerHandWritterRequestToOwnerHandWritten(OwnerHandWritterRequest ownerHandWritterRequest);

    OwnerHandWritterResponse OwnerHandWrittenToOwnerHandWritterResponse(OwnerHandWritten ownerHandWritten);

    Demand DemandAwardHassan2RequestToDemand(DemandAwardHassan2Request demandRequest);

    DemandAwardHassan2Response DemandToDemandAwardHassan2Response(Demand demand);

    Demand DemandHonoraryAwardRequestToDemand(DemandHonoraryAwardRequest request);

    DemandHonoraryAwardResponse DemandToDemandHonoraryAwardResponse(Demand demand);

    Demand DemandHonoraryAwardResponseToDemand(DemandHonoraryAwardResponse demandHonoraryAwardResponse);

    ManuscriptInformation manuscriptInformationRequestToManuscriptInformation(ManuscriptInformationRequest manuscriptInfromationRequest);

    ManuscriptInformation manuscriptInformationResponseToManuscriptInformation(ManuscriptInformationResponse manuscriptInformationResponse);

    ManuscriptInformationResponse manuscriptInformationToManuscriptInformationResponse(ManuscriptInformation manuscriptInformation);

    DemandResponse demandToDemandResponse(Demand demand);

    DocumentDemandResponse documentToDocumentDemandResponse(Document document);

    CandidateHonoraryAwardResponse CandidateHonoraryAwardToCandidateHonoraryAwardResponse(CandidateHonoraryAward candidateHonoraryAward);

    CandidateHonoraryAward CandidateHonoraryAwardResponseToCandidateHonoraryAward(CandidateHonoraryAwardResponse response);

    CandidateHonoraryAward CandidateHonoraryAwardRequestToCandidateHonoraryAward(CandidateHonoraryAwardRequest candidateHonoraryAwardRequest);

    DemandHonoraryAwardToListResponse demandToDemandHonoraryAwardToListResponse(Demand demand);

    ArtistAccount artistAccountRequestToArtistAccount(ArtistAccountRequest artistAccountRequest);

    ArtistAccountRequest artistAccountToArtistAccountRequest(ArtistAccount artistAccount);

    ArtistAccount updateArtistRequestToArtistAccount(ArtistAccountRequest artistAccountRequest, ArtistAccount find);

    BookPrice updatePublicationRequestToBook(PublicationRequest publicationRequest, BookPrice book);

    Demand DemandAwardBookRequestToDemand(DemandAwardBookRequest demandAwardBookRequest);

    BookPrice bookRequestToBook(BookRequest bookRequest);

    AwardObtainedResponse awardToAwardObtainedResponse(AwardObtained award);

    AwardObtained awardObtainedRequestToAwardObtained(AwardObtainedRequest awardObtainedRequest);

    DemandAwardBookListResponse demandToDemandAwardBookListResponse(Demand demand);

    PublicationRequest bookToPublicationRequest(BookPrice book);

    AwardObtained updateAwardObtainedResponseToAwardObtained(AwardObtainedResponse awardObtainedResponse,AwardObtained awardObtained);

    Demand updateAwardBookUpdatingRequestToDemand(AwardBookUpdatingRequest awardBookUpdatingRequest, Demand demand);

    AwardHassan2 awardRequestToAwardHassan2(AwardRequest awardHassan2Request);

    AwardHassan2Response awardHassa2ToAwardHassan2Response(AwardHassan2 awardHassan2);

    AwardHassan2 updateAwardHassan2ResponseToAwardHassan2(AwardHassan2Response awardResponse, AwardHassan2 awardHassan2);

    AwardHonoraryResponse awardHonoraryToAwardHonoraryResponse(AwardHonorary awardHonorary);

    AwardHonorary awardRequestToAwardHonorary(AwardRequest awardRequest);

    AwardHonorary updateAwardHonoraryResponseToAwardHonorary(AwardHonoraryResponse awardHonoraryResponse,AwardHonorary awardHonorary);

    AwardBook awardRequestToAwardBook(AwardRequest awardRequest);

    AwardBookResponse awardBookToAwardBookResponse(AwardBook awardBook);

    AwardBook updateAwardBookResponseToAwardBook(AwardBookResponse awardBookResponse, AwardBook awardBook);

    TheaterPiece theaterPieceRequestToTheaterPiece(TheaterPieceRequest theaterPieceRequest);

    ParticipantsTheater participantRequestToParticipantsTheater(ParticipantRequest participantRequest);

    DemandAwardTheaterResponse demandToDemandAwardTheaterResponse(Demand demand);

    Demand demandAwardTheaterResponseToDemand(DemandAwardTheaterResponse demandAwardTheaterResponse);

    Demand updateDemandAwardTheaterResponseToDemand(DemandAwardTheaterResponse demandAwardTheaterResponse,Demand demand);

    TheaterPieceResponse theaterPieceToTheaterPieceResponse(TheaterPiece theaterPiece);

    TheaterPiece updateTheaterPieceResponseToTheaterPiece(TheaterPieceResponse theaterPieceResponse,TheaterPiece theaterPiece);

    AwardTheaterResponse awardTheaterToAwardTheaterResponse(AwardTheater awardTheater);

    AwardTheater awardRequestToAwardTheater(AwardRequest awardRequest);

    AwardTheater updateAwardTheaterResponseToAwardTheater(AwardTheaterResponse awardTheaterResponse,AwardTheater awardTheater);

    ParticipantTheaterResponse participantTheaterToParticipantTheaterResponse(ParticipantsTheater participantsTheater);

    ParticipantsTheater updateParticipantTheaterResponseToParticipantsTheater(ParticipantTheaterResponse participantTheaterResponse,ParticipantsTheater participantsTheater);

    Price priceRequestToPrice(PriceRequest priceRequest);

    PriceResponse priceToPriceResponse(Price price);

    Price updatePriceResponseToPrice(PriceResponse priceResponse,Price price);

    ArtistAccount candidateHonoraryAwardRequestToArtistAccount(CandidateHonoraryAwardRequest candidateHonoraryAwardRequest);

    CandidateHonoraryAwardResponse artistAccountToCandidateHonoraryAwardResponse(ArtistAccount artistAccount);

    ArtistAccount candidateHonoraryAwardResponseToArtistAccount(CandidateHonoraryAwardResponse candidateHonoraryAwardResponse);

    ArtistAccount updateCandidateHonoraryAwardResponseToArtistAccount(CandidateHonoraryAwardResponse candidateHonoraryAwardResponse,ArtistAccount artistAccount);

    //=============================================================================
    AwardType awardRequestToAwardType(AwardTypeRequest awardRequest);

    AwardsResponse awardToAwardsResponse(Award award);

    AwardCategories awardCategoryRequestToAwardCategories(AwardCategoryRequest awardCategoryRequest);

    Award updateAwardResponseToAward(AwardsResponse awardsResponse,Award award);

    AwardCategoryResponse awardCategoryToAwardCategoryResponse(AwardCategories awardCategories);

    AwardCategories updateAwardCategoryResponseToAwardCategories(AwardCategoryResponse awardCategoryResponse,AwardCategories awardCategories);

    AwardTypeResponse awardTypeToAwardTypeResponse(AwardType awardType);

    OwnerPersonalInfoResponse accountToOwnerPersonalInfoResponse(Account account);

    OwnerPersonalInfoResponse artistAccountToOwnerPersonalInfoResponse(ArtistAccount artistAccount);

    ArtistAccount updateOwnerPersonalInfoResponseToArtistAccount(OwnerPersonalInfoResponse ownerPersonalInfoResponse,ArtistAccount artistAccount);

    ArtistAccount ownerPersonalInfoResponseToArtistAccount(OwnerPersonalInfoResponse ownerPersonalInfoResponse);

    DemandPrice demandPriceRequestToDemandPrice(DemandPriceRequest demandPriceRequest);

    DemandPriceResponse demandPriceToDemandPriceResponse(DemandPrice demandPrice);

    DemandPriceHassan2Response demandPriceToDemandPriceHassan2Response(DemandPrice demandPrice);

    AwardCategoriesResponse awardCategoryToAwardCategoriesResponse(AwardCategories awardCategories);

    ManuscriptInformation updateManuscriptInformationResponseToManuscriptInformation(ManuscriptInformationResponse manuscriptInformationResponse,ManuscriptInformation manuscriptInformation);

    DemandPrice demandPriceTheaterRequestToDemandPrice(DemandPriceTheaterRequest demandPriceTheaterRequest);

    BookPrice publicationPriceRequestToBookPrice(PublicationPriceRequest publicationPriceRequest);

    DemandPriceHonoraryResponse demandPriceToDemandPriceHonoraryResponse(DemandPrice demandPrice);

    DemandPriceHassan2ListResponse demandPriceToDemandPriceHassan2ListResponse(DemandPrice demandPrice);

    DemandPriceHonoraryListResponse demandPriceToDemandPriceHonoraryListResponse(DemandPrice demandPrice);

    DemandPriceTheaterListResponse demandPriceToDemandPriceTheaterListResponse(DemandPrice demandPrice);

    DemandPriceBookListResponse demandPriceToDemandPriceBookListResponse(DemandPrice demandPrice);
}
