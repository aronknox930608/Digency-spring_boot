package ma.digency.gov.amc.service.shared;


import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.GeneralMemberRepository;
import ma.digency.gov.amc.repository.entity.shared.GeneralMember;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneralMemberServiceImpl implements  GeneralMemberService{

    private  final GeneralMemberRepository generalMemberRepository;
    private final ReferenceSequenceService referenceSequenceService;


    @Override
    public GeneralMember createGeneralMember(GeneralMember generalMember) {
        generalMember.setRefGeneralMember(referenceSequenceService.generateRefGeneralMember());
        return generalMemberRepository.save(generalMember);
    }

    @Override
    public List<GeneralMember> findAllByRefParent(String refParent) {
        return generalMemberRepository.findByRefParent(refParent);
    }

    @Override
    public Optional<GeneralMember> findByRefGeneralMember(String refGeneralMember) {
        return generalMemberRepository.findByRefGeneralMember(refGeneralMember);
    }

    @Override
    public GeneralMember updateGeneralMember(GeneralMember generalMember) {
        return generalMemberRepository.save(generalMember);
    }

    @Override
    public Void deleteGeneralMember(GeneralMember generalMember) {
         generalMemberRepository.delete(generalMember);
         return null;
    }
}
