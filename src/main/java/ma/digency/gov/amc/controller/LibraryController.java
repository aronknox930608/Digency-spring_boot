package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.NotificationResponse;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.library.*;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountRequest;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountResponse;
import ma.digency.gov.amc.dto.siel.*;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.library.Library;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import javax.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.core.io.Resource;

@Validated
@RequestMapping("library/")
public interface LibraryController {

    @PostMapping("authors/")
    ResponseEntity<AuthorResponse> createAuthor(@RequestPart (name = "author" , required = true) AuthorRequest response,
                                @RequestPart(name = "picture" , required = false) MultipartFile multipartFile);

    @PutMapping("authors/")
    ResponseEntity<AuthorResponse> updateAuthor(@RequestPart (name = "author" , required = true) AuthorResponse request,
                                                @RequestPart(name = "picture" , required = false) MultipartFile multipartFile);

    @DeleteMapping("authors/{refAuthor}")
    ResponseEntity<Void> deleteAuthor(@PathVariable("refAuthor") @NotBlank String refAuthor);

    @GetMapping("authors/{refAuthor}")
    ResponseEntity<AuthorResponse> getAuthor(@PathVariable("refAuthor") @NotBlank String refAuthor);

    @GetMapping("authors/")
    ResponseEntity<List<AuthorResponse>> getAllAuthor();

    @GetMapping("authors/pageable")
    ResponseEntity<PageableResponse<AuthorResponse>> getAllAuthors(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("authors/search")
    ResponseEntity<Page<Author>> searchAuthors(@RequestParam(required = false) int pageNumber,
                                               int pageSize,
                                               @RequestParam(required = false) Sort.Direction sortDirection,
                                               @RequestParam(required = false) String sortBy,
                                               @RequestParam(required = false) String fullName,
                                               @RequestParam(required = false) String email,
                                               @RequestParam(required = false) String gender,
                                               @RequestParam(required = false) String city);

    @GetMapping("authors/export")
    ResponseEntity<Resource> exportAuthorData();

    ///////////////// BOOKS //////////////////

    @PutMapping("authors/{refAuthor}/books")
    ResponseEntity<List<BookResponse>> updateOrCreateBooks
            (@PathVariable("refAuthor") @NotBlank String refParent,
             @RequestBody List<BookResponse> books);

    @GetMapping("authors/{refAuthor}/books")
    ResponseEntity<PageableResponse<BookResponse>> getAllBooks(
            @PathVariable("refAuthor") @NotBlank String refAuthor,
            @RequestParam Integer page, @RequestParam Integer size);

    @PutMapping("authors/{refAuthor}/books/{refBook}")
    ResponseEntity<BookResponse> updateBook(
            @PathVariable("refAuthor") @NotBlank String refParent,
            @PathVariable("refBook") @NotBlank String refBook,
            @RequestBody @Validated BookResponse response);

    @DeleteMapping("authors/{refAuthor}/books/{refBook}")
    ResponseEntity<Void> deleteBook(
            @PathVariable("refAuthor") @NotBlank String refParent,
            @PathVariable("refBook") @NotBlank String refBook);

    @GetMapping("authors/{refAuthor}/books/{refBook}")
    ResponseEntity<BookResponse> getBookByRefBook(
            @PathVariable("refAuthor") @NotBlank String refAuthor,
            @PathVariable("refBook") @NotBlank String refBook);

    @PostMapping("authors/{refAuthor}/books")
    ResponseEntity<BookResponse> createBook(
            @PathVariable("refAuthor") @NotBlank String refAuthor,
            @RequestBody @Validated BookRequest response);

    /////////////////LIBRARY////////////////////////////

    @PostMapping("libraries/")
    LibraryResponse createLibrary(@RequestBody @Validated LibraryRequest response);

    @PutMapping("libraries/")
    ResponseEntity<LibraryResponse> updateLibrary(@RequestBody @Validated LibraryResponse request);

    @DeleteMapping("libraries/{refLibrary}")
    ResponseEntity<Void> deleteLibrary(@PathVariable("refLibrary") @NotBlank String refLibrary);

    @GetMapping("libraries/{refLibrary}")
    ResponseEntity<LibraryResponse> getLibrary(@PathVariable("refLibrary") @NotBlank String refLibrary);

    @GetMapping("libraries/")
    ResponseEntity<List<LibraryResponse>> getAllLibrary();

    @GetMapping("libraries/pageable")
    ResponseEntity<PageableResponse<LibraryResponse>> getAllLibraries(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("libraries/search")
    ResponseEntity<Page<Library>> searchLibraries(@RequestParam(required = false) int pageNumber,
                                                  int pageSize,
                                                  @RequestParam(required = false) Sort.Direction sortDirection,
                                                  @RequestParam(required = false) String sortBy,
                                                  @RequestParam(required = false) String libraryName,
                                                  @RequestParam(required = false) String libraryOwnerName,
                                                  @RequestParam(required = false) String businessRegisterNumber,
                                                  @RequestParam(required = false) String cnssNumber);

    @GetMapping("libraries/export")
    ResponseEntity<Resource> exportLibraryData();
}
