package ma.digency.gov.amc.process;


import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.shared.GeneralMemberRequest;
import ma.digency.gov.amc.dto.shared.GeneralMemberResponse;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.GeneralMemberMapper;
import ma.digency.gov.amc.repository.entity.shared.GeneralMember;
import ma.digency.gov.amc.service.shared.GeneralMemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneralMemberProcessImpl implements  GeneralMemberProcess{

    private final GeneralMemberMapper generalMemberMapper;
    private final GeneralMemberService generalMemberService;

    @Override
    public GeneralMemberResponse createGeneralMember(GeneralMemberRequest generalMemberRequest) {
        GeneralMember generalMember = generalMemberMapper.GeneralMemberDtoToGeneralMember(generalMemberRequest);
        return generalMemberMapper.GeneralMemberToGeneralMemberDto(generalMemberService.createGeneralMember(generalMember));
    }

    @Override
    public List<GeneralMemberResponse> findAllByRefParent(String refParent) {
        return generalMemberMapper.GeneralMembersToGeneralMembersDto(generalMemberService.findAllByRefParent(refParent));
    }

    @Override
    public GeneralMemberResponse updateGeneralMember(String refGeneralMember, GeneralMemberRequest generalMemberRequest) {
        Optional<GeneralMember> updateGeneralMember = generalMemberService.findByRefGeneralMember(refGeneralMember);
        GeneralMember generalMember = generalMemberMapper.updateGeneralMember(generalMemberRequest,updateGeneralMember.orElseThrow(
                ()->{
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_MEMBER_NOT_FOUND);
                }
        ));
        return generalMemberMapper.GeneralMemberToGeneralMemberDto(generalMemberService.updateGeneralMember(generalMember));
    }

    @Override
    public Void deleteGeneralMember(String refGeneralMember) {
        Optional<GeneralMember> generalMember = generalMemberService.findByRefGeneralMember(refGeneralMember);
        return generalMemberService.deleteGeneralMember(generalMember.orElseThrow(
                ()->{
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_MEMBER_NOT_FOUND);
                }
        ));
    }
}
