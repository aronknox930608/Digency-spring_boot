package ma.digency.gov.amc.service.publiclibrary;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.DocumentaryCollectionRepository;
import ma.digency.gov.amc.repository.PersonnelRepository;
import ma.digency.gov.amc.repository.entity.publiclibrary.DocumentaryCollection;
import ma.digency.gov.amc.repository.entity.publiclibrary.Personnel;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentaryCollectionServiceImpl implements DocumentaryCollectionService {

    private final DocumentaryCollectionRepository documentaryCollectionRepository;
    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public DocumentaryCollection findDocumentaryCollectionByRefDocumentaryCollection(String refDocumentaryCollection) {
        return documentaryCollectionRepository.findDocumentaryCollectionByRefDocumentaryCollection(refDocumentaryCollection)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public DocumentaryCollection createOrUpdateDocumentaryCollection(DocumentaryCollection documentaryCollection) {
        if (null == documentaryCollection.getRefDocumentaryCollection()) {
            var ref = referenceSequenceService.generateRefDocumentaryCollection();
            documentaryCollection.setRefDocumentaryCollection(ref);
        }
        return documentaryCollectionRepository.save(documentaryCollection);
    }

    @Override
    public void deleteDocumentaryCollection(String refDocumentaryCollection) {
        DocumentaryCollection documentaryCollection = findDocumentaryCollectionByRefDocumentaryCollection(refDocumentaryCollection);
        documentaryCollectionRepository.delete(documentaryCollection);
    }

    @Override
    public List<DocumentaryCollection> findAllDocumentaryCollection() {
        return documentaryCollectionRepository.findAll();
    }






}
