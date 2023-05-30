package ma.digency.gov.amc.service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.CommissionAccountRepository;
import ma.digency.gov.amc.repository.CommissionMembersRepository;
import ma.digency.gov.amc.repository.CommissionRepository;
import ma.digency.gov.amc.repository.entity.shared.Commission;
import ma.digency.gov.amc.repository.entity.shared.CommissionAccount;
import ma.digency.gov.amc.repository.entity.shared.CommissionMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommissionServiceImpl implements CommissionService {

    private final CommissionRepository commissionRepository;

    private final CommissionMembersRepository commissionMembersRepository;

    private final CommissionAccountRepository commissionAccountRepository;

    @Override
    public Commission createCommission(Commission newCommission) {
        return commissionRepository.save(newCommission);
    }

    @Override
    public List<Commission> findAllCommission() {
        return commissionRepository.findAll();
    }

    @Override
    public Commission findCommissionByRefCommission(String refCommission) {
        return commissionRepository.findCommissionByRefCommission(refCommission)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_COMMISSION_NOT_FOUND));

    }

    @Override
    public Commission updateCommission(Commission updateCommission) {
        return commissionRepository.save(updateCommission);
    }

    @Override
    public List<CommissionMember> findAllCommissionMembers() {
        return commissionMembersRepository.findAll();
    }

    @Override
    public List<CommissionAccount> checkExistingCommissionMembers(List<String> refMembers) {
        List<CommissionAccount> commissionAccounts = new ArrayList<>();
        for (String ref : refMembers) {
            CommissionAccount findOne = commissionAccountRepository.findCommissionAccountByRefCommissionAccount(ref)
                    .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_COMMISSION_ACCOUNT_NOT_FOUND));
            commissionAccounts.add(findOne);
        }
        return commissionAccounts;
    }
}
