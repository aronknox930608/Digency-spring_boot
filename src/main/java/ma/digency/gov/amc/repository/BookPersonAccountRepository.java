package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.BookPersonAccount;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookPersonAccountRepository extends GenericRepository<BookPersonAccount,Long> {

    Optional<BookPersonAccount> findByRefBookPerson(String refBookPerson);
}
