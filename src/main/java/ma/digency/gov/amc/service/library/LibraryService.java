package ma.digency.gov.amc.service.library;


import ma.digency.gov.amc.dto.searchParticipant.AuthorSearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.LibrarySearchCriteria;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.search.AuthorPage;
import ma.digency.gov.amc.repository.entity.search.LibraryPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface LibraryService {

    Library findLibraryByRefLibrary(String refLibrary);

    Library createOrUpdateLibrary(Library library);

    void deleteLibrary(String refLibrary);

    List<Library> findAllLibraries();

    Page<Library> findLibrarysPageable(Pageable pageable);

    Page<Library> getLibraries(LibraryPage libraryPage, LibrarySearchCriteria librarySearchCriteria);

}
