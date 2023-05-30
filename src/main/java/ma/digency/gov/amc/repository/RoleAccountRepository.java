package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.shared.AccountRoleId;
import ma.digency.gov.amc.repository.entity.shared.RoleAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleAccountRepository extends GenericRepository<RoleAccount, AccountRoleId>{
    void deleteAllByAccountRoleId_RefAccount(String refAccount);
    @Transactional
    @Modifying
    void deleteByAccountRoleId(AccountRoleId accountRoleId);
}
