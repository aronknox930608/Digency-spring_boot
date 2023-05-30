package ma.digency.gov.amc.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import ma.digency.gov.amc.dto.WarningResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.DocumentGenerateDto;
import ma.digency.gov.amc.dto.DocumentResponse;
import ma.digency.gov.amc.dto.DocumentTypeResponse;
import ma.digency.gov.amc.process.DocumentProcess;

@RestController
@Tag(name = "Document")
public class DocumentControllerImpl implements DocumentController {

	@Autowired
	private DocumentProcess documentProcess;

	@Override
	public ResponseEntity<DocumentTypeResponse> uploadDocument(String uploadType, String refObject,String refParent, MultipartFile multipartFile) {
		return ResponseEntity.status(HttpStatus.CREATED).body(documentProcess.saveDocument(uploadType, multipartFile, refObject,refParent));
	}

	@Override
	public ResponseEntity<String> updateDocument(MultipartFile multipartFile, String refDocument) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(documentProcess.updateDocument(multipartFile, refDocument));
	}

	@Override
	public ResponseEntity<Resource> downloadDocument(String refDocument) {
		DocumentResponse file = documentProcess.visualizeDocument(refDocument);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(file.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(new ByteArrayResource(file.getData()));
	}

	@Override
	public ResponseEntity<List<DocumentTypeResponse>> getDocuments(String refObject) {
		return ResponseEntity.status(HttpStatus.OK).body(documentProcess.findDocuments(refObject));
	}

	@Override
	public ResponseEntity<Resource> generateRequestParticipationDocument(HttpServletResponse response, DocumentGenerateDto documentGenerateDto)
			throws IOException {
		DocumentResponse file =
				documentProcess.generateRequestParticipation(documentGenerateDto.getRefExhibitor(), documentGenerateDto.getLanguage());
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(file.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(new ByteArrayResource(file.getData()));

	}

	@Override
	public ResponseEntity<WarningResponse> addExhibitorsFromDocument(@NotBlank String refExhibitor, MultipartFile multipartFile) {

		HashMap<Integer,String> warnnings = documentProcess.addExhibitorsFromDocument(refExhibitor,multipartFile);
		WarningResponse warnningResponse = new WarningResponse(warnnings);

		return ResponseEntity.status(HttpStatus.OK).body(warnningResponse);
	}

	@Override
	public ResponseEntity<List<DocumentTypeResponse>> getDocumentsByReferences(String refParent, String refObject) {
		if(refObject==null)
			refObject=refParent;
		return ResponseEntity.status(HttpStatus.OK).body(documentProcess.findDocumentsByRefObjectAndRefParent(refObject,refParent));
	}


	@Override
    public ResponseEntity<Resource> generateRequestPurchaseOrderDocument(HttpServletResponse response, DocumentGenerateDto documentGenerateDto) throws IOException {
        DocumentResponse file =
                documentProcess.generatePurchaseOrder(documentGenerateDto.getRefExhibitor(), documentGenerateDto.getLanguage());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }
}
