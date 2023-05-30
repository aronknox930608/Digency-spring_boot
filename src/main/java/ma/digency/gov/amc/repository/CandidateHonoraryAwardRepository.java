package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.CandidateHonoraryAward;
import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateHonoraryAwardRepository extends GenericRepository<CandidateHonoraryAward, Long> {

    CandidateHonoraryAward findCandidateHonoraryAwardByRefCandidate(String refCandidate);


}
