package ma.digency.gov.amc.service.proposalproject;

import ma.digency.gov.amc.repository.entity.proposalproject.CooperativeAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CooperativeAccountService {
    CooperativeAccount createNewCooperativeAccount(CooperativeAccount CooperativeAccount);
    CooperativeAccount updateCooperativeAccount(CooperativeAccount CooperativeAccount);
    List<CooperativeAccount> findAllCooperativeAccounts();
    Optional<CooperativeAccount> findCooperativeAccountByRef(String ref);
    Page<CooperativeAccount> findPageableCooperativeAccount(Pageable pageable);
    Optional<CooperativeAccount> findCooperativeAccountByAccount(Account account);
    Page<CooperativeAccount> findAllByAccountAndStatusNot(Account account, StatusEnum status, Pageable pageable);

}
