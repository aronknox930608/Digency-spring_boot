package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.library.*;
import ma.digency.gov.amc.dto.participant.PrinterResponse;
import ma.digency.gov.amc.dto.publiclibrary.*;
import ma.digency.gov.amc.process.LibraryProcess;
import ma.digency.gov.amc.process.PublicLibraryProcess;
import ma.digency.gov.amc.repository.entity.publiclibrary.Personnel;
import ma.digency.gov.amc.repository.entity.publiclibrary.PublicLibrary;
import ma.digency.gov.amc.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Tag(name = "PublicLibrary")
public class PublicLibraryControllerImpl implements PublicLibraryController{

   @Autowired
    private PublicLibraryProcess publicLibraryProcess;


    @Override
    public ResponseEntity<PublicLibraryResponse> createPublicLibrary(PublicLibraryRequest request, MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publicLibraryProcess.createPublicLibrary(request,multipartFile));
    }

    @Override
    public ResponseEntity<PublicLibraryResponse> updatePublicLibrary(PublicLibraryResponse request, MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.updatePublicLibrary(request,multipartFile));
    }

    @Override
    public ResponseEntity<Void> deletePublicLibrary(String refPublicLibrary) {
        publicLibraryProcess.deletePublicLibrary(refPublicLibrary);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PublicLibraryResponse> getPublicLibrary(String refPublicLibrary) {
        return ResponseEntity.status(HttpStatus.OK).body(publicLibraryProcess.findPublicLibrary(refPublicLibrary));
    }

    @Override
    public ResponseEntity<List<PublicLibraryResponse>> getAllPublicLibraries() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.findALlPublicLibraries());
    }

    @Override
    public ResponseEntity<PageableResponse<PublicLibraryResponse>> getAllPublicLibraries(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(publicLibraryProcess.findAllPublicLibraries(SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<Page<PublicLibrary>> searchPublicLibraries(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String libraryName, String nameOfTheResponsible, String partner, String libraryStatus) {
        return ResponseEntity.status(HttpStatus.OK).body(publicLibraryProcess.searchPublicLibraries(pageNumber, pageSize, sortDirection, sortBy, libraryName, nameOfTheResponsible,partner ,libraryStatus ));
    }

    @Override
    public ResponseEntity<Resource> exportLibrariesData() {
        String filename = "publicLibraries.xlsx";
        InputStreamResource file = new InputStreamResource(publicLibraryProcess.exportPublicLibrariesData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @Override
    public PersonnelResponse createPersonnel(PersonnelRequest request) {
        return publicLibraryProcess.createPersonnel(request);
    }

    @Override
    public ResponseEntity<PersonnelResponse> updatePersonnel(PersonnelResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.updatePersonnel(request));
    }

    @Override
    public ResponseEntity<Void> deletePersonnel(String refPersonnel) {
        publicLibraryProcess.deletePersonnel(refPersonnel);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<PersonnelResponse> getPersonnel(String refPersonnel) {
        return ResponseEntity.status(HttpStatus.OK).body(publicLibraryProcess.findPersonnel(refPersonnel));
    }

    @Override
    public ResponseEntity<List<PersonnelResponse>> getAllPersonnel() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.findAllPersonnel());
    }

    @Override
    public BriefcaseBooksResponse createBriefcaseBooks(BriefcaseBooksRequest request) {
        return publicLibraryProcess.createBriefcaseBooks(request);
    }

    @Override
    public ResponseEntity<BriefcaseBooksResponse> updateBriefcaseBooks(BriefcaseBooksResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.updateBriefcaseBooks(request));
    }

    @Override
    public ResponseEntity<Void> deleteBriefcaseBooks(String refBriefcaseBooks) {
        publicLibraryProcess.deleteBriefcaseBooks(refBriefcaseBooks);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<BriefcaseBooksResponse> getBriefcaseBooks(String refBriefcaseBooks) {
        return ResponseEntity.status(HttpStatus.OK).body(publicLibraryProcess.findBriefcaseBooks(refBriefcaseBooks));
    }

    @Override
    public ResponseEntity<List<BriefcaseBooksResponse>> getAllBriefcaseBooks() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.findAllBriefcaseBooks());
    }

    @Override
    public DocumentaryCollectionResponse createDocumentaryCollection(DocumentaryCollectionRequest request) {
        return publicLibraryProcess.createDocumentaryCollection(request);
    }

    @Override
    public ResponseEntity<DocumentaryCollectionResponse> updateDocumentaryCollection(DocumentaryCollectionResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.updateDocumentaryCollection(request));
    }

    @Override
    public ResponseEntity<Void> deleteDocumentaryCollection(String refDocumentaryCollection) {
        publicLibraryProcess.deleteDocumentaryCollection(refDocumentaryCollection);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<DocumentaryCollectionResponse> getDocumentaryCollection(String refDocumentaryCollection) {
        return ResponseEntity.status(HttpStatus.OK).body(publicLibraryProcess.findDocumentaryCollection(refDocumentaryCollection));
    }

    @Override
    public ResponseEntity<List<DocumentaryCollectionResponse>> getAllDocumentaryCollection() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.findAllDocumentaryCollection());
    }

    @Override
    public EquipmentResponse createEquipment(EquipmentRequest request) {
        return publicLibraryProcess.createEquipment(request);
    }


    @Override
    public ResponseEntity<EquipmentResponse> updateEquipment(EquipmentResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.updateEquipment(request));
    }

    @Override
    public ResponseEntity<Void> deleteEquipment(String refEquipment) {
        publicLibraryProcess.deleteEquipment(refEquipment);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<EquipmentResponse> getEquipment(String refEquipment) {
        return ResponseEntity.status(HttpStatus.OK).body(publicLibraryProcess.findEquipment(refEquipment));
    }

    @Override
    public ResponseEntity<List<EquipmentResponse>> getAllEquipment() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.findAllEquipment());
    }

    @Override
    public ItEquipmentResponse createItEquipment(ItEquipmentRequest request) {
        return publicLibraryProcess.createItEquipment(request);
    }

    @Override
    public ResponseEntity<ItEquipmentResponse> updateItEquipment(ItEquipmentResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.updateItEquipment(request));
    }

    @Override
    public ResponseEntity<Void> deleteItEquipment(String refItEquipment) {
        publicLibraryProcess.deleteItEquipment(refItEquipment);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ItEquipmentResponse> getItEquipment(String refItEquipment) {
        return ResponseEntity.status(HttpStatus.OK).body(publicLibraryProcess.findItEquipment(refItEquipment));
    }

    @Override
    public ResponseEntity<List<ItEquipmentResponse>> getAllItEquipment() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.findAllItEquipment());
    }

    @Override
    public SpacesResponse createSpaces(SpacesRequest request) {
        return publicLibraryProcess.createSpaces(request);
    }

    @Override
    public ResponseEntity<SpacesResponse> updateSpaces(SpacesResponse request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.updateSpaces(request));
    }

    @Override
    public ResponseEntity<Void> deleteSpaces(String refSpaces) {
        publicLibraryProcess.deleteSpaces(refSpaces);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @Override
    public ResponseEntity<SpacesResponse> getSpaces(String refSpaces) {
        return ResponseEntity.status(HttpStatus.OK).body(publicLibraryProcess.findSpaces(refSpaces));
    }

    @Override
    public ResponseEntity<List<SpacesResponse>> getAllSpaces() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(publicLibraryProcess.findAllSpaces());
    }
}
