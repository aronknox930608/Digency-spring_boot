package ma.digency.gov.amc.service.publiclibrary;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.BriefcaseBooksRepository;
import ma.digency.gov.amc.repository.SpacesRepository;
import ma.digency.gov.amc.repository.entity.publiclibrary.BriefcaseBooks;
import ma.digency.gov.amc.repository.entity.publiclibrary.Spaces;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpacesServiceImpl implements SpacesService {

    private final SpacesRepository spacesRepository;
    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public Spaces findSpacesByRefSpaces(String refSpaces) {
        return spacesRepository.findSpacesByRefSpaces(refSpaces)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }


    @Override
    public Spaces createOrUpdateSpaces(Spaces spaces) {
        if (null == spaces.getRefSpaces()) {
            var ref = referenceSequenceService.generateRefSpaces();
            spaces.setRefSpaces(ref);
        }
        return spacesRepository.save(spaces);
    }

    @Override
    public void deleteSpaces(String refSpaces) {
        Spaces spaces = findSpacesByRefSpaces(refSpaces);
        spacesRepository.delete(spaces);
    }


    @Override
    public List<Spaces> findAllSpaces() {
        return spacesRepository.findAll();
    }
}
