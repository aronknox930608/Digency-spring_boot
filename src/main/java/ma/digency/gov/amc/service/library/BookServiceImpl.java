package ma.digency.gov.amc.service.library;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.AuthorRepository;
import ma.digency.gov.amc.repository.BookRepository;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.author.Book;
import ma.digency.gov.amc.repository.entity.siel.Publication;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks(Author author) {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookByRefBook(String refAuthor, String refBook) {
    return bookRepository.findBookByRefBookAndRefAuthor(refBook, refAuthor)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PUBLICATION_NOT_FOUND));
    }

    @Override
    public Book findByRefBook(String refBook) {
            return bookRepository.findByRefBook(refBook)
                    .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PUBLICATION_NOT_FOUND));
    }

    @Override
    public void deleteBook(String refAuthor, String refBook) {
        var book = findBookByRefBook(refAuthor, refBook);
        bookRepository.delete(book);
    }

    @Override
    public void deleteBooks(List<Book> books) {
       bookRepository.deleteAll(books);
    }

    @Override
    public Book saveBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    @Override
    public Page<Book> findBooksPageable(String refAuthor, Pageable pageable) {

       return bookRepository.findBooksByRefAuthorOrderByDateCreationAsc(refAuthor,pageable);
    }

}
