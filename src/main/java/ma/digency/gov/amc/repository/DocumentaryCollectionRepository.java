package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.publiclibrary.DocumentaryCollection;
import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentaryCollectionRepository extends GenericRepository<DocumentaryCollection, Long> {

    Optional<DocumentaryCollection> findDocumentaryCollectionByRefDocumentaryCollection(String refDocumentaryCollection);
}
