package ma.digency.gov.amc.service;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.AccountRepository;
import ma.digency.gov.amc.repository.RoleAccountRepository;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.AmcUtilis;
import ma.digency.gov.amc.utils.enumeration.AccountEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final String PHOTO_PREFIX="data:image;base64,";

    private final AccountRepository accountRepository;

    private final RoleAccountRepository roleAccountRepository;

    @Override
    public void checkPersonAccountExist(String email, String mobilePhone, String cin) {
        Optional<Account> account = accountRepository.findPersonByEmailOrCin(email,cin);
        account.ifPresent(value -> {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_ALREADY_EXISTS);
        });
    }

    @Override
    public void createNewAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findAccountByRefAccount(String refAccount) {
        return accountRepository.findPersonByRefAccount(refAccount).orElseThrow(
                ()->{ throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);}
        );
    }

    @Override
    public void updateAccount(Account person) {
        accountRepository.save(person);
    }

    @Override
    public Optional<Account> findAccountByEmail(String user) {
        return accountRepository.findPersonByEmail(user);
    }

    @Override
    public Optional<Account> findAccountByVkey(String vkey) {
        return this.accountRepository.findPersonByVkey(vkey);
    }

    @Override
    public Account updateAccountStatus(Account account, AccountEnum status) {
        account.setStatus(status);
        return this.accountRepository.save(account);
    }

    @Override
    public void generateNewVkey(Account account)
    {
        account.setVkey(AmcUtilis.randomString(50));
        accountRepository.save(account);
    }


    @Override
    public String getRefAccountInRequest() {
        Account u =  (getAccountByRequestHelper()).orElseThrow(  ()-> {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        });
        return u.getRefAccount();
    }
    @Override
    public Account getAccountInRequest() {
        return (getAccountByRequestHelper()).orElse(null);
    }

    @Override
    public Account findAccountByCin(String cin) {
        return accountRepository.findAccountByCin(cin);
    }

    @Override
    public String getPhoto(Account account) {
        String out = new String(account.getPhoto());
        out=PHOTO_PREFIX+out;
        return out;
    }

    @Override
    public Page<Account> findAllPageable(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public void removeAllPreviousRoles(Account account) {

    }

    public Optional<Account> getAccountByRequestHelper(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails)
            username = ((UserDetails)principal).getUsername();
        else
            username = principal.toString();
        return (findAccountByEmail(username));
    }
}
