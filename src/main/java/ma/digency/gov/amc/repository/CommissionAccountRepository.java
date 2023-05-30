package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.shared.CommissionAccount;

import java.util.Optional;

public interface CommissionAccountRepository extends GenericRepository<CommissionAccount, Long> {

    Optional<CommissionAccount> findCommissionAccountByRefCommissionAccount(String refAccount);
}
