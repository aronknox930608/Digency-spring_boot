package ma.digency.gov.amc.service.publiclibrary;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.searchParticipant.PublicLibrarySearchCriteria;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.AuthorRepository;
import ma.digency.gov.amc.repository.PublicLibraryRepository;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.publiclibrary.PublicLibrary;
import ma.digency.gov.amc.repository.entity.publiclibrary.PublicLibraryCriteriaRepository;
import ma.digency.gov.amc.repository.entity.search.PublicLibraryPage;
import ma.digency.gov.amc.service.library.AuthorService;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicLibraryServiceImpl implements PublicLibraryService {

    private final PublicLibraryRepository publicLibraryRepository;
    private final ReferenceSequenceService referenceSequenceService;
    private final PublicLibraryCriteriaRepository publicLibraryCriteriaRepository;
    @Override
    public PublicLibrary findPublicLibraryByRefPublicLibrary(String refPublicLibrary) {
        return publicLibraryRepository.findPublicLibraryByRefPublicLibrary(refPublicLibrary)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }


    @Override
    public PublicLibrary createOrUpdatePublicLibrary(PublicLibrary publicLibrary) {
        if (null == publicLibrary.getRefPublicLibrary()) {
            var ref = referenceSequenceService.generateRefPublicLibrary();
            publicLibrary.setRefPublicLibrary(ref);
        }
        return publicLibraryRepository.save(publicLibrary);
    }


    @Override
    public PublicLibrary createOrUpdatePublicLibraryAndImage(PublicLibrary publicLibrary, MultipartFile multipartFile) {
        try {
            if (null == publicLibrary.getRefPublicLibrary()) {
                var ref = referenceSequenceService.generateRefPublicLibrary();

                publicLibrary.setRefPublicLibrary(ref);
            }
            if(multipartFile != null){ publicLibrary.setLibraryPicture(multipartFile.getBytes());}
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }

        return publicLibraryRepository.save(publicLibrary);
    }

    @Override
    public void deletePublicLibrary(String refPublicLibrary) {
        PublicLibrary publicLibrary = findPublicLibraryByRefPublicLibrary(refPublicLibrary);
        publicLibraryRepository.delete(publicLibrary);
    }

    @Override
    public List<PublicLibrary> findAllPublicLibraries() {
        return publicLibraryRepository.findAll();
    }

    @Override
    public Page<PublicLibrary> findPublicLibraryPageable(Pageable pageable) {
        return publicLibraryRepository.findAll(pageable);
    }

    @Override
    public Page<PublicLibrary> getPublicLibraries(PublicLibraryPage libraryPage, PublicLibrarySearchCriteria librarySearchCriteria) {
        return publicLibraryCriteriaRepository.findAllWithFilters(libraryPage,librarySearchCriteria);
    }
}
