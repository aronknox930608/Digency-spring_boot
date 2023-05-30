package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.library.*;
import ma.digency.gov.amc.dto.searchParticipant.AuthorSearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.LibrarySearchCriteria;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.LibraryMapper;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.author.Book;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.search.AuthorPage;
import ma.digency.gov.amc.repository.entity.search.LibraryPage;
import ma.digency.gov.amc.repository.entity.siel.*;
import ma.digency.gov.amc.service.ExcelLibraryService;
import ma.digency.gov.amc.service.ExcelParticipantService;
import ma.digency.gov.amc.service.library.AuthorService;
import ma.digency.gov.amc.service.library.BookService;
import ma.digency.gov.amc.service.library.LibraryService;
import ma.digency.gov.amc.utils.PatchUtils;
import ma.digency.gov.amc.utils.enumeration.NotificationNature;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryProcessImpl implements LibraryProcess{

    private final AuthorService authorService;
    private final LibraryMapper libraryMapper;
    private final NotificationProcess notificationProcess;
    private final ReferenceSequenceService referenceSequenceService;
    private final BookService bookService;
    private final LibraryService libraryService;
    private  final ExcelParticipantService excelParticipantService;
    private  final ExcelLibraryService excelLibraryService;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Override
    public AuthorResponse createAuthor(AuthorRequest authorRequest,MultipartFile multipartFile) {

        try{
            var author = libraryMapper.authorRequestToAuthor(authorRequest);
            if(multipartFile != null){author.setPicture(multipartFile.getBytes());}

            return libraryMapper.authorToAuthorResponse(authorService.createOrUpdateAuthorAndImage(author,multipartFile));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
    }

    @Override
    public AuthorResponse updateAuthor(AuthorResponse request,MultipartFile multipartFile) {
        try{

            Author find = authorService.findAuthorByRefAuthor(request.getRefAuthor());
            if(multipartFile != null){find.setPicture(multipartFile.getBytes());}
            libraryMapper.updateAuthorFromAuthorResponse(request, find);
            return libraryMapper.authorToAuthorResponse(authorService.createOrUpdateAuthorAndImage(find,multipartFile));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
    }

    @Override
    public void deleteAuthor(String refAuthor) {
        authorService.deleteAuthor(refAuthor);
    }

    @Override
    public AuthorResponse findAuthor(String refAuthor) {
        Author author = authorService.findAuthorByRefAuthor(refAuthor);
        return libraryMapper.authorToAuthorResponse(author);
    }

    @Override
    public List<AuthorResponse> findALlAuthors() {
        return authorService.findAllAuthors().stream()
                .map(libraryMapper::authorToAuthorResponse).collect(Collectors.toList());
    }

    @Override
    public PageableResponse<AuthorResponse> findAllAuthors(Pageable pageable) {
        Page<Author> page ;
        page = authorService.findAuthorsPageable(pageable);

        var pageResponse = page.map(libraryMapper::authorToAuthorResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public Page<Author> searchAuthor(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String fullName, String email, String gender, String city) {
        AuthorPage authorPage=new AuthorPage();

        authorPage.setPageNumber(pageNumber);

        authorPage.setPageSize(pageSize);

        authorPage.setSortBy(sortBy);

        authorPage.setSortDirection(sortDirection);


        AuthorSearchCriteria authorSearchCriteria=new AuthorSearchCriteria();


        authorSearchCriteria.setFullName(fullName);

        authorSearchCriteria.setEmail(email);

        authorSearchCriteria.setGender(gender);

        authorSearchCriteria.setCity(city);

        return authorService.getAuthor(authorPage,authorSearchCriteria);
    }

    @Override
    public ByteArrayInputStream exportAuthorData() {
        List<AuthorResponse> author= excelParticipantService.getAllAuthors();

        ByteArrayInputStream inputStream=excelParticipantService.exportAuthorData(author);

        return inputStream;
    }

    @Override
    public BookResponse createBook(String refAuthor, BookRequest response) {
        Author author = authorService.findAuthorByRefAuthor(refAuthor);
        Book newBook = libraryMapper.bookRequestToBook(response);
        String refBook = referenceSequenceService.generateRefBook();
        newBook.setRefBook(refBook);
        newBook.setRefAuthor(author.getRefAuthor());
        author.getBooks().add(newBook);
        authorService.createOrUpdateAuthor(author);
        return libraryMapper.bookToBookResponse(newBook);
    }

    @Override
    public List<BookResponse> updateOrCreateBooks(String refAuthor, List<BookResponse> booksDto) {
        Author author = authorService.findAuthorByRefAuthor(refAuthor);
        List<BookResponse> repoBook =
                libraryMapper.bookToBookResponse(author.getBooks());
        List<BookResponse> publicationAdd = PatchUtils.getObjectToBeAdd(repoBook, booksDto);
        prepareBookToBeAdd(author, publicationAdd);
        List<BookResponse> bookUpdate = PatchUtils.getObjectToBeUpdated(repoBook, booksDto);
        prepareBookToBeUpdated(author, bookUpdate);
        Author updateAuthor = authorService.createOrUpdateAuthor(author);
        return libraryMapper.bookToBookResponse(updateAuthor.getBooks());
    }

    @Override
    public BookResponse updateBook(String refAuthor, String refBook, BookResponse response) {
        Book book = bookService.findBookByRefBook(refAuthor, refBook);
        Book newBook = libraryMapper.updateBookFromBookResponse(response, book);
        return libraryMapper.bookToBookResponse(bookService.saveBook(newBook));
    }

    @Override
    public void deleteBook(String refAuthor, String refBook) {
        bookService.deleteBook(refAuthor, refBook);
    }

    @Override
    public BookResponse getBookByRefBook(String refAuthor, String refBook) {
        Book book = bookService.findBookByRefBook(refAuthor, refBook);
        return libraryMapper.bookToBookResponse(book);
    }

    @Override
    public PageableResponse<BookResponse> findAllBook(String refAuthor, Pageable pageable) {
        Page<BookResponse> page = bookService.findBooksPageable(refAuthor,pageable)
                .map(libraryMapper::bookToBookResponse);
        return new PageableResponse<>(page);
    }

    @Override
    public LibraryResponse createLibrary(LibraryRequest libraryRequest) {
        var library = libraryMapper.libraryRequestToLibrary(libraryRequest);
        try{
            notificationProcess.sendEmail(prepareParameters(library), NotificationNature.VALID_INSCRIPTION);}
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return libraryMapper.libraryToLibraryResponse(libraryService.createOrUpdateLibrary(library));
    }

    @Override
    public LibraryResponse updateLibrary(LibraryResponse request) {
        Library find = libraryService.findLibraryByRefLibrary(request.getRefLibrary());
        libraryMapper.updateLibraryFromLibraryResponse(request, find);
        return libraryMapper.libraryToLibraryResponse(libraryService.createOrUpdateLibrary(find));
    }

    @Override
    public void deleteLibrary(String refLibrary) {
        libraryService.deleteLibrary(refLibrary);
    }

    @Override
    public LibraryResponse findLibrary(String refLibrary) {
        Library library = libraryService.findLibraryByRefLibrary(refLibrary);
        return libraryMapper.libraryToLibraryResponse(library);
    }

    @Override
    public List<LibraryResponse> findAllLibraries() {
        return libraryService.findAllLibraries().stream()
                .map(libraryMapper::libraryToLibraryResponse).collect(Collectors.toList());
    }

    @Override
    public PageableResponse<LibraryResponse> findAllLibraries(Pageable pageable) {
        Page<Library> page ;
        page = libraryService.findLibrarysPageable(pageable);

        var pageResponse = page.map(libraryMapper::libraryToLibraryResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public Page<Library> searchLibrary(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String libraryName, String libraryOwnerName, String businessRegisterNumber, String cnssNumber) {


        LibraryPage libraryPage=new LibraryPage();

        libraryPage.setPageNumber(pageNumber);

        libraryPage.setPageSize(pageSize);

        libraryPage.setSortBy(sortBy);

        libraryPage.setSortDirection(sortDirection);


        LibrarySearchCriteria librarySearchCriteria=new LibrarySearchCriteria();


        librarySearchCriteria.setLibraryName(libraryName);

        librarySearchCriteria.setLibraryOwnerName(libraryOwnerName);

        librarySearchCriteria.setBusinessRegisterNumber(businessRegisterNumber);

        librarySearchCriteria.setCnssNumber(cnssNumber);

        return libraryService.getLibraries(libraryPage,librarySearchCriteria);
    }

    @Override
    public ByteArrayInputStream exportLibraryData() {
        List<LibraryResponse> library= excelLibraryService.getAllLibraries();

        ByteArrayInputStream inputStream=excelLibraryService.exportLibraryData(library);

        return inputStream;
    }

    protected void prepareBookToBeAdd(Author author, List<BookResponse> booksDto) {
        if (!CollectionUtils.isEmpty(booksDto)) {
            booksDto.forEach(bookDto -> {
                String refBook = referenceSequenceService.generateRefBook();
                bookDto.setRefBook(refBook);
                Book bookToAdd = libraryMapper.bookResponseToBook(bookDto);
                bookToAdd.setRefAuthor(author.getRefAuthor());
                author.getBooks().add(bookToAdd);
            });
        }
    }

    protected void prepareBookToBeUpdated(Author author, List<BookResponse> booksDto) {
        if (!CollectionUtils.isEmpty(booksDto) && !CollectionUtils.isEmpty(author.getBooks())) {
            Map<String, Book> bookMap = author.getBooks().stream()
                    .collect(Collectors.toMap(Book::getRefBook, book -> book));
            booksDto.forEach(bookDto -> {
                if (bookMap.get(bookDto.getRefBook()) != null) {
                    libraryMapper.updateBookFromBookResponse(bookDto,
                          bookMap.get(bookDto.getRefBook()));
                }
            });
        }
    }

    protected HashMap<String, String> prepareParameters(Author auth) {
        HashMap<String, String> pars = new HashMap<>();
        if (auth != null) {
            Author find = authorService.findAuthorByRefAuthor(auth.getRefAuthor());
            pars.put("refObject", auth.getRefAuthor());
            pars.put("to", find.getEmail());
            pars.put("#fullName", find.getFullName());
            pars.put("#salutation", "Mr/Mme");
        }
        return pars;
    }

    protected HashMap<String, String> prepareParameters(Library lib) {
        HashMap<String, String> pars = new HashMap<>();
        if (lib != null) {
            Library find = libraryService.findLibraryByRefLibrary(lib.getRefLibrary());
            pars.put("refObject",lib.getRefLibrary());
            pars.put("to", find.getEmail());
            pars.put("#fullName", find.getLibraryName());
            pars.put("#salutation", "Hi");
        }
        return pars;
    }
}
