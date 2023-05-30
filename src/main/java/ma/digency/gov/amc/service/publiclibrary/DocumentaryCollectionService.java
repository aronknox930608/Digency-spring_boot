package ma.digency.gov.amc.service.publiclibrary;


import ma.digency.gov.amc.repository.entity.publiclibrary.DocumentaryCollection;
import ma.digency.gov.amc.repository.entity.publiclibrary.Personnel;

import java.util.List;


public interface DocumentaryCollectionService {

    DocumentaryCollection findDocumentaryCollectionByRefDocumentaryCollection(String refDocumentaryCollection);

    DocumentaryCollection createOrUpdateDocumentaryCollection(DocumentaryCollection documentaryCollection);

    void deleteDocumentaryCollection(String refDocumentaryCollection);

    List<DocumentaryCollection> findAllDocumentaryCollection();


}
