package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.library.*;

import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.library.Library;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.ByteArrayInputStream;
import java.util.List;


public interface LibraryProcess {

    AuthorResponse createAuthor(AuthorRequest authorRequest, @RequestPart(name = "picture" , required = false) MultipartFile multipartFile) ;

    AuthorResponse updateAuthor(AuthorResponse request, @RequestPart(name = "picture" , required = false) MultipartFile multipartFile);

    void deleteAuthor(String refAuthor);

    AuthorResponse findAuthor(String refAuthor);

    List<AuthorResponse> findALlAuthors();

    PageableResponse<AuthorResponse> findAllAuthors(Pageable pageable);

    Page<Author> searchAuthor(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy,String fullName, String email, String gender,String city);

    ByteArrayInputStream exportAuthorData();

    BookResponse createBook(String refAuthor, BookRequest response);

    List<BookResponse> updateOrCreateBooks(String refAuthor, List<BookResponse> books);

    BookResponse updateBook(String refAuthor, String refBook, BookResponse response);

    void deleteBook(String refAuthor, String refBook);

    BookResponse getBookByRefBook(String refAuthor, String refBook);

    PageableResponse<BookResponse> findAllBook(String refAuthor,Pageable pageable);

    LibraryResponse createLibrary(LibraryRequest libraryRequest);

    LibraryResponse updateLibrary(LibraryResponse request);

    void deleteLibrary(String refLibrary);

    LibraryResponse findLibrary(String refLibrary);

    List<LibraryResponse> findAllLibraries();

    PageableResponse<LibraryResponse> findAllLibraries(Pageable pageable);

    Page<Library> searchLibrary(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String libraryName, String libraryOwnerName, String businessRegisterNumber, String cnssNumber);

    ByteArrayInputStream exportLibraryData();


}
