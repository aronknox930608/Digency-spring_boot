package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistAccountRepository extends GenericRepository<ArtistAccount, Long> {

    Optional<ArtistAccount> findByRefArtistAccount(String ref);
    ArtistAccount findArtistAccountByRefArtistAccount(String ref);
    ArtistAccount findByEmail(String email);
    ArtistAccount findArtistAccountByCin(String cin);

    Optional<ArtistAccount> findFirstByAccountOrderByDateCreationAsc(Account account);

    Page<ArtistAccount> findAll(Specification<ArtistAccount> spec, Pageable pageable);
}
