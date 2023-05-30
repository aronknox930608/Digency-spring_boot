package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.dto.attributionsprix.DemandAwardSearchCriteria;
import ma.digency.gov.amc.repository.SearchFiles.DemandPage;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.CandidateHonoraryAward;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.io.ByteArrayInputStream;
import java.util.List;


public interface DemandAHService {

    Demand createOrUpdateDemand(Demand demand);

    Demand findDemandByRef(String refDemand);

    Demand update(Demand demand);

    void deleteDemand(Demand demand);

    Page<Demand> findDemandPageableAwardHassan2(Pageable pageable);

    Page<Demand> findDemandPageableAwardHonorary(Pageable pageable);

    Page<Demand> findDemandPageableAwardBook(Pageable pageable);

    Page<Demand> findDemandPageableAwardTheater(Pageable pageable);

    Demand findDemandByRefDemand(String refDemand);

    List<Demand> findDemandByWriter(ArtistAccount artistAccount);

    List<Demand> findDemandByOwner(OwnerHandWritten ownerHandWritten);

    List<Demand> findDemandByCandidate(ArtistAccount candidateHonoraryAward);

    Page<Demand> getDemandsAwardHassan2(DemandPage demandPage, DemandAwardSearchCriteria demandAwardHassan2SearchCriteria);

    Page<Demand> getDemandsAwardBook(DemandPage demandPage, DemandAwardSearchCriteria demandAwardBookSearchCriteria);

    Page<Demand> getDemandsAwardHonorary(DemandPage demandPage, DemandAwardSearchCriteria demandAwardHonorarySearchCriteria);

    Page<Demand> getDemandsAwardTheater(DemandPage demandPage, DemandAwardSearchCriteria demandAwardTheaterSearchCriteria);

    List<Demand> findDemandByPersonConnected(Account account);

    List<Demand> getAllDemand();

    ByteArrayInputStream exportDemandAwardHassan2Data(List<Demand> demands);

    ByteArrayInputStream exportDemandAwardHonoraryData(List<Demand> demands);

    ByteArrayInputStream exportDemandAwardBookData(List<Demand> demands);

    ByteArrayInputStream exportDemandAwardTheaterData(List<Demand> demands);

    List<Demand> demandsAwardHassan2();

    List<Demand> demandsAwardHonorary();

    List<Demand> demandsAwardBook();

    List<Demand> demandsAwardTheater();
}