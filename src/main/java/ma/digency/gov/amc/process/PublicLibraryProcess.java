package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.publiclibrary.*;
import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.publiclibrary.PublicLibrary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;


public interface PublicLibraryProcess {

    PublicLibraryResponse createPublicLibrary(PublicLibraryRequest publicLibraryRequest, @RequestPart(name = "picture" , required = false) MultipartFile multipartFile) ;

    PublicLibraryResponse updatePublicLibrary(PublicLibraryResponse request, @RequestPart(name = "picture" , required = false) MultipartFile multipartFile);

    void deletePublicLibrary(String refPublicLibrary);

    PublicLibraryResponse findPublicLibrary(String refPublicLibrary);

    List<PublicLibraryResponse> findALlPublicLibraries();

    PageableResponse<PublicLibraryResponse> findAllPublicLibraries(Pageable pageable);

    Page<PublicLibrary> searchPublicLibraries(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy,String libraryName, String nameOfTheResponsible, String partner, String libraryStatus );

    ByteArrayInputStream exportPublicLibrariesData();

    //////////////

    PersonnelResponse createPersonnel(PersonnelRequest personnelRequest);

    PersonnelResponse updatePersonnel(PersonnelResponse request);

    void deletePersonnel(String refPersonnel);

    PersonnelResponse findPersonnel(String refPersonnel);

    List<PersonnelResponse> findAllPersonnel();

    ///////

    DocumentaryCollectionResponse createDocumentaryCollection( DocumentaryCollectionRequest  documentaryCollectionRequest);

    DocumentaryCollectionResponse updateDocumentaryCollection(DocumentaryCollectionResponse request);

    void deleteDocumentaryCollection(String refDocumentaryCollection);

    DocumentaryCollectionResponse findDocumentaryCollection(String refDocumentaryCollection);

    List<DocumentaryCollectionResponse> findAllDocumentaryCollection();

    ////////////

    BriefcaseBooksResponse createBriefcaseBooks(BriefcaseBooksRequest briefcaseBooksRequest);

    BriefcaseBooksResponse updateBriefcaseBooks(BriefcaseBooksResponse request);

    void deleteBriefcaseBooks(String refBriefcaseBooks);

    BriefcaseBooksResponse findBriefcaseBooks(String refBriefcaseBooks);

    List<BriefcaseBooksResponse> findAllBriefcaseBooks();

/////////////////

    EquipmentResponse createEquipment(EquipmentRequest equipmentRequest);

    EquipmentResponse updateEquipment(EquipmentResponse request);

    void deleteEquipment(String refEquipment);

    EquipmentResponse findEquipment(String refEquipment);

    List<EquipmentResponse> findAllEquipment();

    ////////////

    ItEquipmentResponse createItEquipment(ItEquipmentRequest itEquipmentRequest);

    ItEquipmentResponse updateItEquipment(ItEquipmentResponse request);

    void deleteItEquipment(String refItEquipment);

    ItEquipmentResponse findItEquipment(String refItEquipment);

    List<ItEquipmentResponse> findAllItEquipment();

    ///////

    SpacesResponse createSpaces(SpacesRequest spacesRequest);

    SpacesResponse updateSpaces(SpacesResponse request);

    void deleteSpaces(String refSpaces);

    SpacesResponse findSpaces(String refSpaces);

    List<SpacesResponse> findAllSpaces();

}
