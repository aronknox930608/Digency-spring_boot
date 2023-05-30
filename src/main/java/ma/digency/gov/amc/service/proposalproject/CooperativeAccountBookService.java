package ma.digency.gov.amc.service.proposalproject;

import ma.digency.gov.amc.repository.entity.proposalproject.CooperativeAccountBook;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CooperativeAccountBookService {
    CooperativeAccountBook createNewCooperativeAccountBook(CooperativeAccountBook CooperativeAccountBook);
    CooperativeAccountBook updateCooperativeAccountBook(CooperativeAccountBook CooperativeAccountBook);
    List<CooperativeAccountBook> findAllCooperativeAccountBooks();
    Optional<CooperativeAccountBook> findCooperativeAccountBookByRef(String ref);
    Page<CooperativeAccountBook> findPageableCooperativeAccountBook(Pageable pageable);
    Optional<CooperativeAccountBook> findCooperativeAccountBookByAccount(Account account);
    Page<CooperativeAccountBook> findAllByAccountAndStatusNot(Account account, StatusEnum status, Pageable pageable);
}
