package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHassan2ListResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryListResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryRequest;
import ma.digency.gov.amc.dto.attributionsPrix1.DemandPriceHonoraryResponse;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface AwardHonoraryProcess {

    String createCandidate(CandidateHonoraryAwardRequest request,MultipartFile picture, MultipartFile cv,MultipartFile identityStatement);

    String createDemand(DemandPriceHonoraryRequest request);

    DemandPriceHonoraryResponse getDemand(String refDemand);

    DemandPriceHonoraryResponse updateDemand(DemandPriceHonoraryResponse request);

    String updateCandidate(CandidateHonoraryAwardResponse request,MultipartFile picture,MultipartFile cv, MultipartFile identityStatement);

    void deleteDemand(String refDemand);

    PageableResponse<DemandHonoraryAwardToListResponse> findAllDemand(Pageable pageable);

    List<AwardHonoraryResponse> addAwardHonorary(AwardRequest request);

    List<AwardHonoraryResponse> findAllAwards();

    AwardHonoraryResponse getAwardHonoraryByRef(String refAward);

    List<AwardHonoraryResponse> updateAwardHonorary(AwardHonoraryResponse request);

    List<AwardHonoraryResponse> deleteAwardHonorary(String refAward);

    List<Demand> getDemandsUserLogged(String refUser);

    Page<Demand> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType);

    List<ArtistAccount> getListArtists();

    CandidateHonoraryAwardResponse getCandidateByRef(String refCandidate);

    Void saveArtistsExcel(MultipartFile file);

    ByteArrayInputStream exportArtistsData();

    ByteArrayInputStream exportDemandData();

    PageableResponse<DemandPriceHonoraryListResponse> findAllDemands(Pageable pageable);
}
