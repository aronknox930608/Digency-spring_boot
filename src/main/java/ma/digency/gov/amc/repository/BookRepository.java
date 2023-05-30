package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.author.Book;
import ma.digency.gov.amc.repository.entity.siel.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends GenericRepository<Book, Long>{

    Optional<Book> findBookByRefBook(String refBook);

    List<Book> findBookByRefAuthor(Author author);

    Optional<Book> findBookByRefBookAndRefAuthor(String refBook, String refAuthor);

    Optional<Book> findByRefBook(String refBook);

    Page<Book> findBooksByRefAuthorOrderByDateCreationAsc(String refAuthor, Pageable pageable);

}
