package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.CompanyAccountRepository;
import ma.digency.gov.amc.repository.entity.proposalproject.CompanyAccount;
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
public class CompanyAccountServiceImp implements CompanyAccountService{

    private final CompanyAccountRepository companyAccountRepository;
    private final ReferenceSequenceService referenceSequenceService;



    @Override
    public CompanyAccount createNewCompanyAccount(CompanyAccount companyAccount) {
        companyAccount.setStatus(StatusEnum.PENDING);
        companyAccount.setRefCompanyAccount(referenceSequenceService.generateRefCompanyAccount());
        return companyAccountRepository.save(companyAccount);
    }

    @Override
    public CompanyAccount updateCompanyAccount(CompanyAccount companyAccount) {
        return companyAccountRepository.save(companyAccount);
    }

    @Override
    public List<CompanyAccount> findAllCompanyAccounts() {
        return companyAccountRepository.findAll();
    }

    @Override
    public Optional<CompanyAccount> findCompanyAccountByRef(String ref) {
        return companyAccountRepository.findByRefCompanyAccount(ref);
    }

    @Override
    public Page<CompanyAccount> findPageableCompanyAccount(Pageable pageable) {
        return companyAccountRepository.findAll(pageable);
    }

    @Override
    public Optional<CompanyAccount> findCompanyAccountByAccount(Account account) {
        return companyAccountRepository.findFirstByAccountOrderByDateCreationAsc(account);
        //return companyAccountRepository.findCompanyAccountByAccount(account);
    }

    @Override
    public Page<CompanyAccount> findAllByAccountAndStatusNot(Account account, StatusEnum status,Pageable pageable) {
        return companyAccountRepository.findAll(setCretieria(account, status), pageable);
    }


    private Specification<CompanyAccount> setCretieria(Account account, StatusEnum status) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("account"), account));
            predicates.add(criteriaBuilder.notEqual(root.get("status"), status));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
