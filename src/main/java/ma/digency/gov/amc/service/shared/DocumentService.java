package ma.digency.gov.amc.service.shared;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import ma.digency.gov.amc.repository.entity.Document;
import ma.digency.gov.amc.service.siel.SielParticipationDocument;

public interface DocumentService {

	Document uploadDocument(Document document);

	Document findByRefDocument(String refDocument);

	List<Document> findByRefObject(String refObject);

	void deleteDocument(Document document);

	List<Document> findByRefObjectAndRefParent(String refObject,String refParent);

	void deleteDocument(String refObject);

	@NotNull
	@Valid
	byte[] generateDocument(@NotNull @Valid SielParticipationDocument documentRequest, boolean isPurchase) throws IOException;

	void deleteByRefObject(String refObject);
}
