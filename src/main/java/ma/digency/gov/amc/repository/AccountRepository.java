package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends GenericRepository<Account, Long> {

    Optional<Account> findPersonByRefAccount(String refAccount);

    Optional<Account> findPersonByEmailOrCin(String email, String cin);

    Optional<Account> findPersonByEmail(String user);

    Optional<Account> findPersonByVkey(String vkey);

    Account findAccountByCin(String cin);
}
