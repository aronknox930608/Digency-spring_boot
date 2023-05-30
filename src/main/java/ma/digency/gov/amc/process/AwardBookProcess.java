package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

public interface AwardBookProcess {

    OwnerPersonalInfoResponse getWriter(String cin) throws Exception;

    String createArtist(OwnerPersonalInfoResponse request) throws Exception;

    DemandAwardBookResponse createDemand(DemandPriceBookRequest request) throws Exception;

    BookPrice createBook(PublicationPriceRequest request);

    List<PublicationRequest> getAllPublication(String author);

    List<PublicationRequest> addPublication(PublicationPriceRequest request);

    List<AwardObtainedResponse> getAwards(String author);

    List<AwardObtainedResponse> addAward(AwardObtainedRequest request);

    List<AwardObtainedResponse> deleteAward(String refAwardObtained);

    AwardObtainedResponse getAward(String refAward);

    List<AwardObtainedResponse> updateAward(AwardObtainedResponse request);

    void deleteDemand(String refDemand);

    PageableResponse<DemandAwardBookListResponse> findAllDemand(Pageable pageable);

    List<PublicationRequest> updatePublication(PublicationRequest bookRequest);

    List<PublicationRequest> deletePublication(String refPublication);

    DemandPriceBookResponse getDemand(String refDemand);

    String updateDemand(DemandPriceBookResponse request);

    PublicationRequest getPublication(String refBook);

    String updateBook(PublicationRequest request);

    PublicationRequest getBookDemand(String refDemand);

    List<AwardBookResponse> addAwardBook(AwardRequest request);

    List<AwardBookResponse> getAllAwards();

    AwardBookResponse getAwardBookByRef(String refAward);

    List<AwardBookResponse> updateAwardBook(AwardBookResponse request);

    List<AwardBookResponse> deleteAwardBook(String refAward);

    List<Demand> getDemandsUserLogged(String refUser);

    Page<Demand> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType);

    ByteArrayInputStream exportDemandData();

    Void saveBooksExcel(MultipartFile multipartFile);

    ByteArrayInputStream exportPublicationsData();

    Void saveAwardsExcel(MultipartFile multipartFile);

    ByteArrayInputStream exportAwardObtainedData();

    PageableResponse<DemandPriceBookListResponse> findAllDemands(Pageable pageable);

    List<AwardCategoriesLestingResponse> getAwardCategoriesBook(String refAward);

}
