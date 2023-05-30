package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.library.*;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountRequest;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountResponse;
import ma.digency.gov.amc.dto.siel.ExhibitorRequest;
import ma.digency.gov.amc.dto.siel.ExhibitorResponse;
import ma.digency.gov.amc.dto.siel.PublicationResponse;
import ma.digency.gov.amc.process.LibraryProcess;
import ma.digency.gov.amc.process.SielProcess;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ma.digency.gov.amc.utils.SearchUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.List;

@RestController
@Tag(name = "Library")
public class LibraryControllerImpl implements LibraryController{

   @Autowired
    private LibraryProcess libraryProcess;

    @Override
    public ResponseEntity<AuthorResponse> createAuthor(AuthorRequest authorRequest, MultipartFile multipartFile) {

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryProcess.createAuthor(authorRequest,multipartFile));
    }

    @Override
    public ResponseEntity<AuthorResponse> updateAuthor(AuthorResponse request, MultipartFile multipartFile) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(libraryProcess.updateAuthor(request,multipartFile));
    }

    @Override
    public ResponseEntity<Void> deleteAuthor(String refAuthor) {
        libraryProcess.deleteAuthor(refAuthor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<AuthorResponse> getAuthor(String refAuthor) {
        return ResponseEntity.status(HttpStatus.OK).body(libraryProcess.findAuthor(refAuthor));
    }

    @Override
    public ResponseEntity<List<AuthorResponse>> getAllAuthor() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libraryProcess.findALlAuthors());
    }

   @Override
    public ResponseEntity<PageableResponse<AuthorResponse>> getAllAuthors(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(libraryProcess.findAllAuthors(SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<Page<Author>> searchAuthors(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String fullName, String email, String gender, String city) {
        return ResponseEntity.status(HttpStatus.OK).body(libraryProcess.searchAuthor(pageNumber, pageSize, sortDirection, sortBy, fullName, email, gender, city));
    }

    @Override
    public ResponseEntity<Resource> exportAuthorData() {
        String filename = "authors.xlsx";
        InputStreamResource file = new InputStreamResource(libraryProcess.exportAuthorData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public ResponseEntity<List<BookResponse>> updateOrCreateBooks(String refAuthor, List<BookResponse> books) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libraryProcess.updateOrCreateBooks(refAuthor, books));

    }

    @Override
    public ResponseEntity<PageableResponse<BookResponse>> getAllBooks(String refAuthor,Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libraryProcess.findAllBook(refAuthor,
                        SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<BookResponse> updateBook(String refAuthor, String refBook, BookResponse response) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libraryProcess.updateBook(refAuthor, refBook, response));
    }

    @Override
    public ResponseEntity<Void> deleteBook(String refAuthor, String refBook) {
        libraryProcess.deleteBook(refAuthor, refBook);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<BookResponse> getBookByRefBook(String refAuthor, String refBook) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libraryProcess.getBookByRefBook(refAuthor, refBook));
    }

    @Override
    public ResponseEntity<BookResponse> createBook(String refAuthor, BookRequest response) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libraryProcess.createBook(refAuthor, response));
    }

    @Override
    public LibraryResponse createLibrary(LibraryRequest libraryRequest) {
        return libraryProcess.createLibrary(libraryRequest);
    }

    @Override
    public ResponseEntity<LibraryResponse> updateLibrary(LibraryResponse libraryRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libraryProcess.updateLibrary(libraryRequest));
    }

    @Override
    public ResponseEntity<Void> deleteLibrary(String refLibrary) {
        libraryProcess.deleteLibrary(refLibrary);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<LibraryResponse> getLibrary(String refLibrary) {
        return ResponseEntity.status(HttpStatus.OK).body(libraryProcess.findLibrary(refLibrary));
    }

    @Override
    public ResponseEntity<List<LibraryResponse>> getAllLibrary() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(libraryProcess.findAllLibraries() );
    }

    @Override
    public ResponseEntity<PageableResponse<LibraryResponse>> getAllLibraries(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(libraryProcess.findAllLibraries(SearchUtils.createPageable(page,size)) );
    }

    @Override
    public ResponseEntity<Page<Library>> searchLibraries(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String libraryName, String libraryOwnerName, String businessRegisterNumber, String cnssNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(libraryProcess.searchLibrary(pageNumber, pageSize, sortDirection, sortBy, libraryName, libraryOwnerName, businessRegisterNumber, cnssNumber));
    }

    @Override
    public ResponseEntity<Resource> exportLibraryData() {
        String filename = "libraries.xlsx";
        InputStreamResource file = new InputStreamResource(libraryProcess.exportLibraryData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}



