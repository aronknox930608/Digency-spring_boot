package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.Document;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends GenericRepository<Document, Long> {
    Optional<Document> findByRefDocument(String refDocument);

    List<Document> findByRefObject(String refObject);

    List<Document> findByRefObjectAndRefParent(String refObject,String refParent);
}
