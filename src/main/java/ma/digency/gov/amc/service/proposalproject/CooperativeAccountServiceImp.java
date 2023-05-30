package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.CooperativeAccountRepository;
import ma.digency.gov.amc.repository.entity.proposalproject.CompanyAccount;
import ma.digency.gov.amc.repository.entity.proposalproject.CooperativeAccount;
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
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CooperativeAccountServiceImp implements CooperativeAccountService{

    private final CooperativeAccountRepository cooperativeAccountRepository;
    private final ReferenceSequenceService referenceSequenceService;



    @Override
    public CooperativeAccount createNewCooperativeAccount(CooperativeAccount CooperativeAccount) {
        CooperativeAccount.setStatus(StatusEnum.PENDING);
        CooperativeAccount.setRefCooperativeAccount(referenceSequenceService.generateRefCooperativeAccount());
        return cooperativeAccountRepository.save(CooperativeAccount);
    }

    @Override
    public CooperativeAccount updateCooperativeAccount(CooperativeAccount CooperativeAccount) {
        return cooperativeAccountRepository.save(CooperativeAccount);
    }

    @Override
    public List<CooperativeAccount> findAllCooperativeAccounts() {
        return cooperativeAccountRepository.findAll();
    }

    @Override
    public Optional<CooperativeAccount> findCooperativeAccountByRef(String ref) {
        return cooperativeAccountRepository.findByRefCooperativeAccount(ref);
    }

    @Override
    public Page<CooperativeAccount> findPageableCooperativeAccount(Pageable pageable) {
        return cooperativeAccountRepository.findAll(pageable);
    }
    @Override
    public Optional<CooperativeAccount> findCooperativeAccountByAccount(Account account) {
        return cooperativeAccountRepository.findFirstByAccountOrderByDateCreationAsc(account);
       // return cooperativeAccountRepository.findCooperativeAccountByAccount(account);
    }

    @Override
    public Page<CooperativeAccount> findAllByAccountAndStatusNot(Account account, StatusEnum status,Pageable pageable) {
        return cooperativeAccountRepository.findAll(setCretieria(account, status), pageable);
    }


    private Specification<CooperativeAccount> setCretieria(Account account,StatusEnum status) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("account"), account));
            predicates.add(criteriaBuilder.notEqual(root.get("status"), status));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
