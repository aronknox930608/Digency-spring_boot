package ma.digency.gov.amc.service.proposalproject;

import ma.digency.gov.amc.repository.entity.proposalproject.CompanyAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyAccountService {
    CompanyAccount createNewCompanyAccount(CompanyAccount companyAccount);
    CompanyAccount updateCompanyAccount(CompanyAccount companyAccount);
    List<CompanyAccount> findAllCompanyAccounts();
    Optional<CompanyAccount> findCompanyAccountByRef(String ref);
    Page<CompanyAccount> findPageableCompanyAccount(Pageable pageable);
    Optional<CompanyAccount> findCompanyAccountByAccount(Account account);
    Page<CompanyAccount> findAllByAccountAndStatusNot(Account account, StatusEnum status, Pageable pageable);

}
