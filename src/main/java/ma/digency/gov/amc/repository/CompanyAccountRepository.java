package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.CompanyAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyAccountRepository extends GenericRepository<CompanyAccount,Long>{
    Optional<CompanyAccount> findByRefCompanyAccount(String refCompanyAccount);
    Optional<CompanyAccount> findCompanyAccountByAccount(Account account);
    Optional<CompanyAccount> findFirstByAccountOrderByDateCreationAsc(Account account);
    Page<CompanyAccount> findAll(Specification<CompanyAccount> spec, Pageable pageable);
}
