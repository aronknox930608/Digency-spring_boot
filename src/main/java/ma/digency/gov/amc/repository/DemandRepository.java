package ma.digency.gov.amc.repository;

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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandRepository extends GenericRepository<Demand, Long> {

    Demand findDemandByRefDemand(String refDemand);

    @Query("SELECT d FROM Demand d WHERE d.demandOwner!=null")
    Page<Demand> findDemandHassan2(Pageable pageable);

    @Query("SELECT d FROM Demand d WHERE d.candidate!=null")
    Page<Demand> findDemandAwardHonorary(Pageable pageable);

    @Query("SELECT d FROM Demand d WHERE d.writer!=null")
    Page<Demand> findDemandAwardBook(Pageable pageable);

    @Query("SELECT d FROM Demand d where d.theaterPiece!=null")
    Page<Demand> findDemandAwardTheater(Pageable pageable);

    @Query("SELECT d FROM Demand d WHERE d.demandOwner!=null")
    List<Demand> demandsAwardHassan2();

    @Query("SELECT d FROM Demand d WHERE d.candidate!=null")
    List<Demand> demandsAwardHonorary();

    @Query("SELECT d FROM Demand d WHERE d.writer!=null")
    List<Demand> demandsAwardBook();

    @Query("SELECT d FROM Demand d where d.theaterPiece!=null")
    List<Demand> demandsAwardTheater();

    List<Demand> findDemandByDemandOwner(OwnerHandWritten ownerHandWritten);

    List<Demand> findDemandByWriter(ArtistAccount artistAccount);

    List<Demand> findDemandByCandidate(ArtistAccount candidateHonoraryAward);

    List<Demand> findDemandByPersonConnected(Account account);

    @Query("SELECT d FROM Demand d WHERE d.demandOwner!=null")
    List<Demand> findAllDemandHassan2();


}
