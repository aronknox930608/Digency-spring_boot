package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.publiclibrary.BriefcaseBooks;
import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BriefcaseBooksRepository extends GenericRepository<BriefcaseBooks, Long> {

    Optional<BriefcaseBooks> findBriefcaseBooksByRefBriefcaseBooks(String refBriefcaseBooks);
}
