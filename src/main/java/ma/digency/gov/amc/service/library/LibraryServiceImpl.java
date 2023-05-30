package ma.digency.gov.amc.service.library;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.searchParticipant.LibrarySearchCriteria;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.AuthorRepository;
import ma.digency.gov.amc.repository.LibraryRepository;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.author.AuthorCriteriaRepository;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.library.LibraryCriteriaRepository;
import ma.digency.gov.amc.repository.entity.search.LibraryPage;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final ReferenceSequenceService referenceSequenceService;
    private final LibraryCriteriaRepository libraryCriteriaRepository;
    @Override
    public Library findLibraryByRefLibrary(String refLibrary) {
        return libraryRepository.findLibraryByRefLibrary(refLibrary)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public Library createOrUpdateLibrary(Library library) {
        if (null == library.getRefLibrary()) {
            var ref = referenceSequenceService.generateRefLibrary();
            library.setRefLibrary(ref);
        }
        return libraryRepository.save(library);
    }

        @Override
    public void deleteLibrary(String refLibrary) {
            Library library = findLibraryByRefLibrary(refLibrary);
            libraryRepository.delete(library);
    }

    @Override
    public List<Library> findAllLibraries() {
        return libraryRepository.findAll();
    }

    @Override
    public Page<Library> findLibrarysPageable(Pageable pageable) {
        return libraryRepository.findAll(pageable);
    }

    @Override
    public Page<Library> getLibraries(LibraryPage libraryPage, LibrarySearchCriteria librarySearchCriteria) {
        return libraryCriteriaRepository.findAllWithFilters(libraryPage,librarySearchCriteria);
    }

}
