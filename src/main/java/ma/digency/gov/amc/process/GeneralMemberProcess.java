package ma.digency.gov.amc.process;


import ma.digency.gov.amc.dto.shared.GeneralMemberRequest;
import ma.digency.gov.amc.dto.shared.GeneralMemberResponse;

import java.util.List;

public interface GeneralMemberProcess {

    GeneralMemberResponse createGeneralMember(GeneralMemberRequest generalMemberRequest);

    List<GeneralMemberResponse> findAllByRefParent(String refParent);

    GeneralMemberResponse updateGeneralMember(String refGeneralMember, GeneralMemberRequest generalMemberRequest);

    Void deleteGeneralMember(String refGeneralMember);
}
