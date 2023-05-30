package ma.digency.gov.amc.process;

import java.util.HashMap;
import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotBlank;

import ma.digency.gov.amc.repository.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import ma.digency.gov.amc.dto.DocumentResponse;
import ma.digency.gov.amc.dto.DocumentTypeResponse;

public interface DocumentProcess {

	DocumentTypeResponse saveDocument(String uploadType, MultipartFile multipartFile, String refObject,String refParent);

	DocumentResponse visualizeDocument(String refDocument);

	List<DocumentTypeResponse> findDocuments(String refObject);

	List<DocumentTypeResponse> findDocumentsByRefObjectAndRefParent(String refObject,String refParent);

	DocumentResponse generateRequestParticipation(@NotBlank String refExhibitor, @NotBlank String language) throws IOException;

	DocumentResponse generatePurchaseOrder(@NotBlank String refExhibitor, @NotBlank String language) throws IOException;

	HashMap<Integer,String> addExhibitorsFromDocument(String refExhibitor, MultipartFile multipartFile);

    String updateDocument(MultipartFile multipartFile, String refDocument);

    DocumentResponse uploadPublicationModel();
	List<Document> findDocumentByRefObject(String refObject);

	Document findDocumentByRef(String refDcument);

    DocumentResponse uploadForeignExhibitor();
}
