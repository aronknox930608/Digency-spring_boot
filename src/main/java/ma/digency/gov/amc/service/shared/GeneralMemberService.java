package ma.digency.gov.amc.service.shared;

import ma.digency.gov.amc.repository.entity.shared.GeneralMember;

import java.util.List;
import java.util.Optional;

public interface GeneralMemberService {

    GeneralMember createGeneralMember(GeneralMember generalMember);

    List<GeneralMember> findAllByRefParent(String refParent);

    Optional<GeneralMember> findByRefGeneralMember(String refGeneralMember);

    GeneralMember updateGeneralMember(GeneralMember generalMember);

    Void deleteGeneralMember(GeneralMember generalMember);
}
