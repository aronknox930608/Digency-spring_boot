package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.CommissionVoteRequest;
import ma.digency.gov.amc.dto.shared.*;

import java.util.List;

public interface CommissionProcess {

    CommissionResponse createCommission(CommissionRequest request);

    List<CommissionResponse> findAllCommission();

    CommissionResponse updateCommission(String refCommission, CommissionResponse request);

    List<CommissionMemberResponse> createCommissionMembers(String refCommission, List<CommissionMemberRequest> members);

    List<CommissionMemberResponse> findAllCommissionMembers();

    void addCommissionMembers(String refCommission, List<String> refMembers);

    List<CommissionMemberResponse> findCommissionMembers(String refCommission);

    void addPublicationsToTreat(String refCommission, String refPlanning, ReferenceRequest refs);

    CommissionResponse findCommission(String refCommission);

    void addVotePublication(String refCommission, CommissionVoteRequest voteRequest, String refPlanning);
}
