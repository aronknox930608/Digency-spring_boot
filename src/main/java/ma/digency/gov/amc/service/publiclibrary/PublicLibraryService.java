package ma.digency.gov.amc.service.publiclibrary;


import ma.digency.gov.amc.dto.searchParticipant.LibrarySearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.PublicLibrarySearchCriteria;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.publiclibrary.PublicLibrary;
import ma.digency.gov.amc.repository.entity.search.LibraryPage;
import ma.digency.gov.amc.repository.entity.search.PublicLibraryPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PublicLibraryService {

    PublicLibrary findPublicLibraryByRefPublicLibrary(String refPublicLibrary);

    PublicLibrary createOrUpdatePublicLibrary(PublicLibrary publicLibrary);

    PublicLibrary createOrUpdatePublicLibraryAndImage(PublicLibrary publicLibrary, MultipartFile multipartFile);

    void deletePublicLibrary(String refPublicLibrary);

    List<PublicLibrary> findAllPublicLibraries();

    Page<PublicLibrary> findPublicLibraryPageable(Pageable pageable);

    Page<PublicLibrary> getPublicLibraries(PublicLibraryPage libraryPage, PublicLibrarySearchCriteria librarySearchCriteria);
}
