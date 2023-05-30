package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.CooperativeAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CooperativeAccountRepository extends GenericRepository<CooperativeAccount,Long>{
    Optional<CooperativeAccount> findByRefCooperativeAccount(String refCooperativeAccount);
    Optional<CooperativeAccount> findCooperativeAccountByAccount(Account account);
    Optional<CooperativeAccount> findCooperativeAccountByAccount_RefAccount(String refAccount);
    Optional<CooperativeAccount> findFirstByAccountOrderByDateCreationAsc(Account account);
    Page<CooperativeAccount> findAll(Specification<CooperativeAccount> spec, Pageable pageable);
}
