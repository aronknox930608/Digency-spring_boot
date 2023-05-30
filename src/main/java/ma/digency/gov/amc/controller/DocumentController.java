package ma.digency.gov.amc.controller;


import ma.digency.gov.amc.dto.DocumentGenerateDto;
import ma.digency.gov.amc.dto.DocumentResponse;
import ma.digency.gov.amc.dto.DocumentTypeResponse;
import ma.digency.gov.amc.dto.WarningResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import java.io.IOException;
import java.util.List;

@Validated
@RequestMapping("documents/")
public interface DocumentController {

    @PostMapping()
    ResponseEntity<DocumentTypeResponse> uploadDocument(@RequestPart(name = "documentType", required = true) String documentType,
                                                    @RequestPart(name = "refObject", required = true) String refObject,
                                                    @RequestPart(name = "refParent", required = false) String refParent,
                                                    @RequestPart(name = "file", required = true) MultipartFile multipartFile);

    @PutMapping("{refDocument}")
    ResponseEntity<String> updateDocument(@RequestPart(name = "file", required = true) MultipartFile multipartFile,
                                          @PathVariable("refDocument") @NotBlank String refDocument);

    @GetMapping(path = "{refDocument}/visualizeDocument", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Resource> downloadDocument(@PathVariable("refDocument") @NotBlank String refDocument);

    @GetMapping("{refObject}")
    ResponseEntity<List<DocumentTypeResponse>> getDocuments(@PathVariable("refObject") @NotBlank String refObject);

    @PostMapping(value = "generate-request-participation")
    ResponseEntity<Resource> generateRequestParticipationDocument(HttpServletResponse response,@RequestBody DocumentGenerateDto documentGenerateDto) throws
            IOException;

    @PostMapping(value = "generate-bon-commande")
    ResponseEntity<Resource> generateRequestPurchaseOrderDocument(HttpServletResponse response,@RequestBody DocumentGenerateDto documentGenerateDto) throws
            IOException;


    @PostMapping("{refExhibitor}/exhibitors")
    ResponseEntity<WarningResponse> addExhibitorsFromDocument(@PathVariable("refExhibitor") @NotBlank String refExhibitor
            , @RequestPart(name = "file", required = true) MultipartFile multipartFile);

    @GetMapping("parent/{refParent}")
    ResponseEntity<List<DocumentTypeResponse>> getDocumentsByReferences(@PathVariable("refParent") @NotBlank String refParent,@RequestParam(required = false) String refObject);



}
