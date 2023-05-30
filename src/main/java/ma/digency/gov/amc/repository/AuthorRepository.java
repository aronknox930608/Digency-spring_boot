package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends GenericRepository<Author, Long>, JpaSpecificationExecutor<Author>, CrudRepository<Author, Long> {

    Optional<Author> findAuthorByRefAuthor(String refAuthor);


}
