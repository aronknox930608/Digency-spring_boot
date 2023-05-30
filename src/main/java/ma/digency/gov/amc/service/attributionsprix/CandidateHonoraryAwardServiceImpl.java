package ma.digency.gov.amc.service.attributionsprix;


import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.CandidateHonoraryAwardRepository;
import ma.digency.gov.amc.repository.entity.BirthData;
import ma.digency.gov.amc.repository.entity.attributionsprix.CandidateHonoraryAward;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.utils.enumeration.MaritalStatus;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Service
@RequiredArgsConstructor
public class CandidateHonoraryAwardServiceImpl implements CandidateHonoraryAwardService {

    private final ReferenceSequenceService referenceSequenceService;
    private final CandidateHonoraryAwardRepository candidateHonoraryAwardRepository;

    @Override
    public CandidateHonoraryAward createCandidate(CandidateHonoraryAward candidateHonoraryAward) {
        if (candidateHonoraryAward.getRefCandidate() == null) {
            var ref = referenceSequenceService.generateRefCandidateHonoraryAward();
            candidateHonoraryAward.setRefCandidate(ref);
        }
        return candidateHonoraryAwardRepository.save(candidateHonoraryAward);
    }

    @Override
    public CandidateHonoraryAward getCandidateByRef(String refCandidate) {
        return candidateHonoraryAwardRepository.findCandidateHonoraryAwardByRefCandidate(refCandidate);
    }

    @Override
    public CandidateHonoraryAward updateCandidate(CandidateHonoraryAward candidateHonoraryAward) {
        CandidateHonoraryAward candidate = getCandidateByRef(candidateHonoraryAward.getRefCandidate());
        candidate.setRefCandidate(candidateHonoraryAward.getRefCandidate());
        candidate.setCin(candidateHonoraryAward.getCin());
        candidate.setFirstName(candidateHonoraryAward.getFirstName());
        candidate.setLastName(candidateHonoraryAward.getLastName());
        candidate.setFirstNameAR(candidateHonoraryAward.getFirstNameAR());
        candidate.setLastNameAR(candidateHonoraryAward.getLastNameAR());
        candidate.setGender(candidateHonoraryAward.getGender());
        candidate.setEmail(candidateHonoraryAward.getEmail());
        candidate.setPhoneNumber(candidateHonoraryAward.getPhoneNumber());
        candidate.setMaritalStatus(candidateHonoraryAward.getMaritalStatus());
        candidate.setDependentChildren(candidateHonoraryAward.getDependentChildren());
        candidate.setBirthdata(candidateHonoraryAward.getBirthdata());
        candidate.setRibNumber(candidateHonoraryAward.getRibNumber());
        candidate.setDomainName(candidateHonoraryAward.getDomainName());
        candidate.setCity(candidateHonoraryAward.getCity());
        candidate.setCountry(candidateHonoraryAward.getCountry());
        candidate.setAddress(candidateHonoraryAward.getAddress());
        candidate.setRegion(candidateHonoraryAward.getRegion());
        candidate.setPicture(candidateHonoraryAward.getPicture());
        candidate.setCv(candidateHonoraryAward.getCv());
        return candidateHonoraryAwardRepository.save(candidate);
    }

    @Override
    public void deleteCandidate(CandidateHonoraryAward candidateHonoraryAward) {
        candidateHonoraryAwardRepository.delete(candidateHonoraryAward);
    }


}
