package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.attributionsprix.CandidateHonoraryAward;

public interface CandidateHonoraryAwardService {

    CandidateHonoraryAward createCandidate(CandidateHonoraryAward candidateHonoraryAward);

    CandidateHonoraryAward getCandidateByRef(String refCandidate);

    CandidateHonoraryAward updateCandidate(CandidateHonoraryAward candidateHonoraryAward);

    void deleteCandidate(CandidateHonoraryAward candidateHonoraryAward);


}
