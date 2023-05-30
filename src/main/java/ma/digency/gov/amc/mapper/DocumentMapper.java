package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.DocumentResponse;
import ma.digency.gov.amc.dto.DocumentTypeResponse;
import ma.digency.gov.amc.repository.entity.Document;

import java.util.List;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface DocumentMapper {

    List<DocumentTypeResponse> documentsToDocumentsType(List<Document> documents);

    DocumentResponse documentToDocumentResponse(Document document);

    DocumentTypeResponse documentToDocumentTypeResponse(Document document);
}
