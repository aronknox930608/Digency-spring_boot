package ma.digency.gov.amc.process;


import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.repository.SearchFiles.OwnerHandWrittenPage;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptType;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;


public interface AwardHassan2Process {

    OwnerHandWritterResponse createOwner(OwnerHandWritterRequest request);

    DemandAwardHassan2Response createDemand(DemandAwardHassan2Request request);

    ManuscriptInfromationAddingResponse addManuscriptInformation(ManuscriptInformationRequest request);

    DemandResponse getDemandByRef(String refDemand);

    DemandResponse updateDemand(DemandResponse request);

    void deleteDemand(String refDemand);

    PageableResponse<DemandResponse> findAllDemand(Pageable pageable);

    List<DocumentDemandResponse> getDocuments(String refDemand);

    List<DocumentDemandResponse> getDocumentsPersonal(String refArtistAccount);

    List<AwardHassan2Response> addAwardHassan2(AwardRequest awardHassan2Request);

    List<AwardHassan2Response> updateAwardHassan2(AwardHassan2Response request);

    AwardHassan2Response getAwardHassan2ByRef(String refAward);

    List<AwardHassan2Response> findAllAwards();

    List<AwardHassan2Response> deleteAwardHassan2(String refAward);

    List<Demand> getDemandsUserLogged(String refUser);

    Page<OwnerHandWritten> getOwners(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy , String cin, String firstName, String lastName, String firstNameAr, String lastNameAr,
                                     String gender, String email, String phone, String rib);

    Page<Demand> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType);

    Void saveOwnerExcel(MultipartFile file);

    ByteArrayInputStream exportArtistData();

    Void saveManuscriptInformationExcel(MultipartFile file);

    ByteArrayInputStream exportManuscriptData();

    ByteArrayInputStream exportDemandData();

    //==================================================================================

    OwnerPersonalInfoResponse getOwnerPersonalInformation(String email);

    String createOrUpdatePersonalInformation(OwnerPersonalInfoResponse ownerPersonalInfoResponse);

    DemandAwardResponse addDemand(DemandPriceRequest request);

    ManuscriptInfromationAddingResponse createManuscriptInformation(ManuscriptInformationRequest request);

    DemandPriceHassan2Response getDemandPriceByRef(String refDemand);

    DemandPriceHassan2Response updateDemandPrice(DemandPriceHassan2Response request);

    Void deleteDemandPrice(String refAward);

    PageableResponse<DemandPriceHassan2ListResponse> findAllDemands(Pageable pageable);

    List<AwardCategoriesLestingResponse> getAwardCategoriesHassan2(String refAwardType);

    List<ManuscriptType> getAllManuscriptType();

    List<PriceHassa2Response> getPriceDemandList();
}
