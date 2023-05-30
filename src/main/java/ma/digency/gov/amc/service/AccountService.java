package ma.digency.gov.amc.service;

import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.AccountEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountService {

    void checkPersonAccountExist(String email, String mobilePhone,String cin);

    void createNewAccount(Account person);

    Account findAccountByRefAccount(String refAccount);

    void updateAccount(Account person);

    Optional<Account> findAccountByEmail(String user);

    Optional<Account> findAccountByVkey(String vkey);

    Account updateAccountStatus(Account account, AccountEnum status);

    void generateNewVkey(Account account);

    String getRefAccountInRequest();

    Account getAccountInRequest();

    String getPhoto(Account account);

    Page<Account> findAllPageable(Pageable pageable);

    Account findAccountByCin(String cin);

    void removeAllPreviousRoles(Account account);

}
