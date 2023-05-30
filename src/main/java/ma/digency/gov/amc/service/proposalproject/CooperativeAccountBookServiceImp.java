package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.CooperativeAccountBookRepository;
import ma.digency.gov.amc.repository.entity.proposalproject.CooperativeAccountBook;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CooperativeAccountBookServiceImp implements CooperativeAccountBookService {
    private final CooperativeAccountBookRepository cooperativeAccountBookRepository;
    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public CooperativeAccountBook createNewCooperativeAccountBook(CooperativeAccountBook CooperativeAccountBook) {
        CooperativeAccountBook.setStatus(StatusEnum.PENDING);
        CooperativeAccountBook.setRefCooperativeAccountBook(referenceSequenceService.generateRefCooperativeBookAccount());
        return cooperativeAccountBookRepository.save(CooperativeAccountBook);
    }

    @Override
    public CooperativeAccountBook updateCooperativeAccountBook(CooperativeAccountBook CooperativeAccountBook) {
        return cooperativeAccountBookRepository.save(CooperativeAccountBook);
    }

    @Override
    public List<CooperativeAccountBook> findAllCooperativeAccountBooks() {
        return cooperativeAccountBookRepository.findAll();
    }

    @Override
    public Optional<CooperativeAccountBook> findCooperativeAccountBookByRef(String ref) {
        return cooperativeAccountBookRepository.findByRefCooperativeAccountBook(ref);
    }

    @Override
    public Page<CooperativeAccountBook> findPageableCooperativeAccountBook(Pageable pageable) {
        return cooperativeAccountBookRepository.findAll(pageable);
    }

    @Override
    public Optional<CooperativeAccountBook> findCooperativeAccountBookByAccount(Account account) {
        return cooperativeAccountBookRepository.findCooperativeAccountBookByAccount(account);
    }

    @Override
    public Page<CooperativeAccountBook> findAllByAccountAndStatusNot(Account account, StatusEnum status, Pageable pageable) {
        return cooperativeAccountBookRepository.findAll(setCretieria(account, status), pageable);
    }

    private Specification<CooperativeAccountBook> setCretieria(Account account, StatusEnum status) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("account"), account));
            predicates.add(criteriaBuilder.notEqual(root.get("status"), status));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
