package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.participant.*;
import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.participant.*;


import java.util.List;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface ParticipantMapper {

    DistributorResponse distributorToDistributorResponse(Distributor  distributor);

    Distributor  distributorResponseToDistributor ( DistributorResponse response);

    Distributor  distributorRequestToDistributor( DistributorRequest distributorRequest);

    Distributor updateDistributorFromDistributorResponse( DistributorResponse response,  Distributor  distributor);

    List< DistributorResponse>  distributorToDistributorResponse(List< Distributor>  distributor);

    FacilitiesServicesResponse facilitiesServicesToFacilitiesServicesResponse(FacilitiesServices  facilitiesServices);

    FacilitiesServices facilitiesServicesRequestToFacilitiesServices(FacilitiesServicesRequest facilitiesServicesRequest);

    FacilitiesServices updateFacilitiesServicesFromFacilitiesServicesResponse(FacilitiesServicesResponse response,FacilitiesServices facilitiesServices);

    PublishingOtherProductResponse publishingOtherProductToPublishingOtherProductResponse(PublishingOtherProduct publishingOtherProduct);

    PublishingOtherProduct publishingOtherProductRequestToPublishingOtherProduct(PublishingOtherProductRequest publishingOtherProductRequest);

    PublishingOtherProduct updatePublishingOtherProductFromPublishingOtherProductResponse(PublishingOtherProductResponse response,PublishingOtherProduct publishingOtherProduct);

    WritingLanguageResponse writingLanguageToWritingLanguageResponse(WritingLanguage writingLanguage);

    WritingLanguage writingLanguageRequestToWritingLanguage(WritingLanguageRequest writingLanguageRequest);

    WritingLanguage updateWritingLanguageFromWritingLanguageResponse(WritingLanguageResponse response,WritingLanguage writingLanguage);

    FieldsOfWritingResponse fieldsOfWritingToFieldsOfWritingResponse(FieldsOfWriting fieldsOfWriting);

    FieldsOfWriting fieldsOfWritingRequestToFieldsOfWriting(FieldsOfWritingRequest fieldsOfWritingRequest);

    FieldsOfWriting updateFieldsOfWritingFromFieldsOfWritingResponse(FieldsOfWritingResponse response,FieldsOfWriting fieldsOfWriting);

    BookPromotionFormResponse bookPromotionFormToBookPromotionFormResponse(BookPromotionForm bookPromotionForm);

    BookPromotionForm bookPromotionFormRequestToBookPromotionForm(BookPromotionFormRequest bookPromotionFormRequest);

    BookPromotionForm updateBookPromotionFormFromBookPromotionFormResponse(BookPromotionFormResponse response,BookPromotionForm bookPromotionForm);

    SellingPointsResponse sellingPointsToSellingPointsResponse(SellingPoints sellingPoints);

    SellingPoints sellingPointsRequestToSellingPoints(SellingPointsRequest sellingPointsRequest);

    SellingPoints updateSellingPointsFromSellingPointsResponse(SellingPointsResponse response,SellingPoints sellingPoints);




}
