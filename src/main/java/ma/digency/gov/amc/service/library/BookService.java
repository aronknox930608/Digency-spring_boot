package ma.digency.gov.amc.service.library;

import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.author.Book;
import ma.digency.gov.amc.repository.entity.siel.Publication;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
   List<Book> findAllBooks(Author author);

   Book findBookByRefBook(String refAuthor, String refbook);

    Book findByRefBook(String refBook);

   void deleteBook(String refAuthor, String refBook);

    void deleteBooks(List<Book> book);

   Book saveBook(Book newBook);

    Page<Book> findBooksPageable(String refAuthor, Pageable pageable);
}
