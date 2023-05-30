package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.CooperativeAccountBook;
import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface CooperativeAccountBookRepository extends GenericRepository<CooperativeAccountBook,Long>{
    Optional<CooperativeAccountBook> findByRefCooperativeAccountBook(String refCooperativeAccountBook);
    Optional<CooperativeAccountBook> findCooperativeAccountBookByAccount(Account account);
    Optional<CooperativeAccountBook> findCooperativeAccountBookByAccount_RefAccount(String refAccount);
    Optional<CooperativeAccountBook> findFirstByAccountOrderByDateCreationAsc(Account account);
    Page<CooperativeAccountBook> findAll(Specification<CooperativeAccountBook> spec, Pageable pageable);
}
