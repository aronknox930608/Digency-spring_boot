package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.ParticipantsTheater;
import ma.digency.gov.amc.repository.entity.attributionsprix.RoleTheater;
import ma.digency.gov.amc.repository.entity.attributionsprix.TheaterPiece;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

public interface AwardTheaterProcess {

    OwnerPersonalInfoResponse getArtist(String refAccount);

    String createArtist(ArtistAccountRequest artistAccountRequest);

    TheaterPieceResponse addDemand(DemandPriceTheaterRequest demandPriceTheaterRequest);

    String addParticipant(ParticipantRequest participantRequest);

    DemandPriceTheaterResponse getDemand(String refDemand);

    String updateDemand(DemandPriceTheaterResponse request);

    List<ParticipantTheaterResponse> listParticipants(String refTheaterPrice);

    ParticipantTheaterResponse getParticipantByRef(String refParticipant);

    List<ParticipantTheaterResponse> updateParticipant(ParticipantTheaterResponse request);

    List<ParticipantTheaterResponse> deleteParticipant(String refParticipant);

    Void deleteDemand(String refDemand);

    List<TheaterPieceResponse> getTheaterPiecesOfArtist(String cin);

    TheaterPieceResponse getTheaterPiece(String refTheaterPiece);

    TheaterPieceResponse updateTheaterPiece(TheaterPieceResponse request);

    List<AwardTheaterResponse> addAwardTheater(AwardRequest awardTheaterRequest);

    List<AwardTheaterResponse> updateAwardTheater(AwardTheaterResponse request);

    AwardTheaterResponse getAwardTheaterByRef(String refAward);

    List<AwardTheaterResponse> deleteAwardTheater(String refAward);

    List<AwardTheaterResponse> getAllAwardsTheater();

    Page<Demand> searchDemand(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String status, Date decision_date, String demandOwnerFirstName, String demandOwnerLastName, String awardType);

    PageableResponse<DemandAwardTheaterResponse> findAllDemand(Pageable pageable);

    List<Demand> getDemandsUserLogged(String refUser);

    ByteArrayInputStream exportDemandData();

    Void saveTheaterPiecesExcel(MultipartFile multipartFile);

    ByteArrayInputStream exportTheaterPiecesData();

    Void saveParticipantsExcel(MultipartFile multipartFile, String refTheaterPiece);

    ByteArrayInputStream exportParticipantsData(String refTheaterPiece);

    String createOrUpdatePersonalInformation(OwnerPersonalInfoResponse artistPersonalInfoResponse);

    PageableResponse<DemandPriceTheaterListResponse> findAllDemands(Pageable pageable);

    List<RoleTheater> getRoleTheater();
}
