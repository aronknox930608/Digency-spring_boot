package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.card.*;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountResponse;
import ma.digency.gov.amc.utils.CardPage;
import ma.digency.gov.amc.utils.DemandPage;
import ma.digency.gov.amc.utils.searching.CardSearchCriteria;
import ma.digency.gov.amc.utils.searching.DemandPlanningCardCriteria;
import ma.digency.gov.amc.utils.searching.DemandSearchCriteria;
import ma.digency.gov.amc.utils.DemandPlanningPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface CardProcess {

   ArtistAccountCardResponse createArtistAccount(ArtistAccountCardRequest artistAccountRequest);
   ArtistAccountCardResponse updateArtistAccount(ArtistAccountCardRequest artistAccountRequest);
   ArtistAccountCardResponse getAccount(String email);
   CardResponse retiredCard(String refCard);
   Boolean isPeriodOpenForDemandCard();
   ArtistAccountCardResponse getArtistAccount(String refArtistAccount);
   CardResponse createCard(String refArtistAccount,CardRequest cardRequest);
   DemandCardResponse createDemand(String refArtistAccount,DemandCardRequest demandRequest);
   void deleteDemandCard(String refDemand);
   void deleteCard(String refCard);
   DemandCardResponse getDemandByRefDemand(String refDemand);
   DemandCardResponse updateDemand(DemandCardRequest demand);
   PageableResponse<DemandCardResponse> allDemands(Pageable pageable);
   PageableResponse<CardResponse> getAllCards(Pageable pageable);
   List<ArtistAccountResponse> importExcelData(MultipartFile file);
   List<CardResponse> importArtistCards(MultipartFile file);
   ByteArrayInputStream exportArtistData();
   ByteArrayInputStream exportArtistCard();
   Page<DemandCardResponse> getAllDemands(DemandPage demandPage, DemandSearchCriteria demandSearchCriteria);
   Page<CardResponse> getAllCards(CardPage cardPage, CardSearchCriteria cardSearchCriteria);
   List<DemandCardResponse> getDemandsByRefArtistAccount(String refArtistAccount);
   List<DemandCardResponse> getDemands();
   DemandPlanningResponse createDemandPlanning(DemandPlanningRequest demandPlanningRequest);
   void deleteDemandPlanning(String refDemandPlanning);
   DemandPlanningResponse updateDemandPlanning(DemandPlanningRequest demandPlanningRequest);
   PageableResponse<DemandPlanningResponse> getAllPlannings(Pageable pageable);
   Page<DemandPlanningResponse> getPlanningWithSearchCriteria(DemandPlanningPage demandPlannigPage, DemandPlanningCardCriteria demandPlannigCardCriteria);
}
