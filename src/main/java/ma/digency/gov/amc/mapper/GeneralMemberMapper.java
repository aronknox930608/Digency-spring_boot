package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.shared.GeneralMemberRequest;
import ma.digency.gov.amc.dto.shared.GeneralMemberResponse;
import ma.digency.gov.amc.repository.entity.shared.GeneralMember;

import java.util.List;


@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface GeneralMemberMapper {

    GeneralMember GeneralMemberDtoToGeneralMember(GeneralMemberRequest GeneralMemberRequest);

    GeneralMemberResponse GeneralMemberToGeneralMemberDto(GeneralMember GeneralMember);

    List<GeneralMemberResponse> GeneralMembersToGeneralMembersDto(List<GeneralMember> GeneralMember);

    GeneralMember updateGeneralMember(GeneralMemberRequest update, GeneralMember GeneralMember);

}
