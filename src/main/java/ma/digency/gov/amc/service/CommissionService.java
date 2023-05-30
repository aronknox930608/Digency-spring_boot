package ma.digency.gov.amc.service;

import ma.digency.gov.amc.repository.entity.shared.Commission;
import ma.digency.gov.amc.repository.entity.shared.CommissionAccount;
import ma.digency.gov.amc.repository.entity.shared.CommissionMember;

import java.util.List;

public interface CommissionService {

    Commission createCommission(Commission newCommission);

    List<Commission> findAllCommission();

    Commission findCommissionByRefCommission(String refCommission);

    Commission updateCommission(Commission updateCommission);

    List<CommissionMember> findAllCommissionMembers();

    List<CommissionAccount> checkExistingCommissionMembers(List<String> refMembers);
}
