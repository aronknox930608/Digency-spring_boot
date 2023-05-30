package ma.digency.gov.amc.service.publiclibrary;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.BriefcaseBooksRepository;
import ma.digency.gov.amc.repository.PersonnelRepository;
import ma.digency.gov.amc.repository.entity.publiclibrary.BriefcaseBooks;
import ma.digency.gov.amc.repository.entity.publiclibrary.Personnel;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BriefcaseBooksServiceImpl implements BriefcaseBooksService {

    private final BriefcaseBooksRepository briefcaseBooksRepository;
    private final ReferenceSequenceService referenceSequenceService;


    @Override
    public BriefcaseBooks findBriefcaseBooksByRefBriefcaseBooks(String refBriefcaseBooks) {
        return briefcaseBooksRepository.findBriefcaseBooksByRefBriefcaseBooks(refBriefcaseBooks)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }


    @Override
    public BriefcaseBooks createOrUpdateBriefcaseBooks(BriefcaseBooks briefcaseBooks) {
        if (null == briefcaseBooks.getRefBriefcaseBooks()) {
           var ref = referenceSequenceService.generateRefBriefcaseBooks();
            briefcaseBooks.setRefBriefcaseBooks(ref);
        }
        return briefcaseBooksRepository.save(briefcaseBooks);
    }

    @Override
    public void deleteBriefcaseBooks(String refBriefcaseBooks) {
        BriefcaseBooks briefcaseBooks = findBriefcaseBooksByRefBriefcaseBooks(refBriefcaseBooks);
        briefcaseBooksRepository.delete(briefcaseBooks);
    }

    @Override
    public List<BriefcaseBooks> findAllBriefcaseBooks() {
        return briefcaseBooksRepository.findAll();
    }
}
