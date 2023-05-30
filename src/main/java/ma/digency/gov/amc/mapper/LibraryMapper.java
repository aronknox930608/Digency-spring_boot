package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.library.*;
import ma.digency.gov.amc.dto.siel.PublicationRequest;
import ma.digency.gov.amc.dto.siel.PublicationResponse;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.author.Book;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.siel.Publication;


import java.util.List;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface LibraryMapper {

    AuthorResponse authorToAuthorResponse(Author author);

    Author authorResponseToAuthor(AuthorResponse response);

    Author authorRequestToAuthor(AuthorRequest authorRequest);

    Author updateAuthorFromAuthorResponse(AuthorResponse response, Author author);

    List<AuthorResponse> authorToAuthorResponse(List<Author> author);

    BookResponse bookToBookResponse(Book book);

    Book bookResponseToBook(BookResponse boook);

    Book updateBookFromBookResponse(BookResponse response, Book book);

    List<BookResponse> bookToBookResponse(List<Book> book);

    Book bookRequestToBook(BookRequest response);

    LibraryResponse libraryToLibraryResponse(Library library);

    Library libraryResponseToLibrary(LibraryResponse response);

    Library libraryRequestToLibrary(LibraryRequest libraryRequest);

    Library updateLibraryFromLibraryResponse(LibraryResponse response, Library library);

    List<LibraryResponse> libraryToLibraryResponse(List<Library> library);


}
