package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.library.*;
import ma.digency.gov.amc.dto.participant.DistributorResponse;
import ma.digency.gov.amc.dto.publiclibrary.*;
import ma.digency.gov.amc.dto.searchParticipant.DistributorSearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.PublicLibrarySearchCriteria;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.LibraryMapper;
import ma.digency.gov.amc.mapper.PublicLibraryMapper;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.author.Book;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.publiclibrary.*;
import ma.digency.gov.amc.repository.entity.search.DistributorPage;
import ma.digency.gov.amc.repository.entity.search.PublicLibraryPage;
import ma.digency.gov.amc.service.ExcelPublicLibraryService;
import ma.digency.gov.amc.service.library.AuthorService;
import ma.digency.gov.amc.service.library.BookService;
import ma.digency.gov.amc.service.library.LibraryService;
import ma.digency.gov.amc.service.publiclibrary.*;
import ma.digency.gov.amc.utils.PatchUtils;
import ma.digency.gov.amc.utils.enumeration.NotificationNature;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicLibraryProcessImpl implements PublicLibraryProcess{

    private final PublicLibraryService publicLibraryService;
    private final PublicLibraryMapper publicLibraryMapper;
    private final PersonnelService personnelService;
    private final BriefcaseBooksService briefcaseBooksService;
    private final DocumentaryCollectionService documentaryCollectionService;
    private final ItEquipmentService itEquipmentService;
    private final EquipmentService equipmentService;
    private final SpacesService spacesService;
    private final ExcelPublicLibraryService excelpublicLibraryService;
    private final NotificationProcess notificationProcess;
    private final ReferenceSequenceService referenceSequenceService;


    @Enumerated(EnumType.STRING)
    private StatusEnum status;


    @Override
    public PublicLibraryResponse createPublicLibrary(PublicLibraryRequest publicLibraryRequest, MultipartFile multipartFile) {
        try{
            var publicLibrary = publicLibraryMapper.publicLibraryRequestToPublicLibrary(publicLibraryRequest);
            if(multipartFile != null){publicLibrary.setLibraryPicture(multipartFile.getBytes());}

            return publicLibraryMapper.publicLibraryToPublicLibraryResponse(publicLibraryService.createOrUpdatePublicLibraryAndImage(publicLibrary,multipartFile));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
    }


    @Override
    public PublicLibraryResponse updatePublicLibrary(PublicLibraryResponse request, MultipartFile multipartFile) {
        try{

            PublicLibrary find = publicLibraryService.findPublicLibraryByRefPublicLibrary(request.getRefPublicLibrary());
            if(multipartFile != null){find.setLibraryPicture(multipartFile.getBytes());}
            publicLibraryMapper.updatePublicLibraryFromPublicLibraryResponse(request, find);
            return publicLibraryMapper.publicLibraryToPublicLibraryResponse(publicLibraryService.createOrUpdatePublicLibraryAndImage(find,multipartFile));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
    }
    @Override
    public void deletePublicLibrary(String refPublicLibrary) {
       publicLibraryService.deletePublicLibrary(refPublicLibrary);
    }

    @Override
    public PublicLibraryResponse findPublicLibrary(String refPublicLibrary) {
        PublicLibrary publicLibrary = publicLibraryService.findPublicLibraryByRefPublicLibrary(refPublicLibrary);
        return publicLibraryMapper.publicLibraryToPublicLibraryResponse(publicLibrary);
    }

    @Override
    public List<PublicLibraryResponse> findALlPublicLibraries() {
        return publicLibraryService.findAllPublicLibraries().stream()
                .map(publicLibraryMapper::publicLibraryToPublicLibraryResponse).collect(Collectors.toList());
    }

    @Override
    public PageableResponse<PublicLibraryResponse> findAllPublicLibraries(Pageable pageable) {
        Page<PublicLibrary> page ;
        page = publicLibraryService.findPublicLibraryPageable(pageable);

        var pageResponse = page.map(publicLibraryMapper::publicLibraryToPublicLibraryResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public Page<PublicLibrary> searchPublicLibraries(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy, String libraryName, String nameOfTheResponsible, String partner, String libraryStatus) {
        PublicLibraryPage publicLibraryPage=new PublicLibraryPage();

        publicLibraryPage.setPageNumber(pageNumber);

        publicLibraryPage.setPageSize(pageSize);

        publicLibraryPage.setSortBy(sortBy);

        publicLibraryPage.setSortDirection(sortDirection);


        PublicLibrarySearchCriteria publicLibrarySearchCriteria=new PublicLibrarySearchCriteria();


        publicLibrarySearchCriteria.setLibraryName(libraryName);

        publicLibrarySearchCriteria.setNameOfTheResponsible(nameOfTheResponsible);

        publicLibrarySearchCriteria.setPartner(partner);

        publicLibrarySearchCriteria.setLibraryStatus(libraryStatus);



        return publicLibraryService.getPublicLibraries(publicLibraryPage,publicLibrarySearchCriteria);
    }

    @Override
    public ByteArrayInputStream exportPublicLibrariesData() {
        List<PublicLibraryResponse> publicLibrary= excelpublicLibraryService.getAllPublicLibraries();

        ByteArrayInputStream inputStream=excelpublicLibraryService.exportPublicLibraryData(publicLibrary);

        return inputStream;
    }

    @Override
    public PersonnelResponse createPersonnel(PersonnelRequest personnelRequest) {
        var personnel = publicLibraryMapper.personnelRequestToPersonnel(personnelRequest);
        return publicLibraryMapper.personnelToPersonnelResponse(personnelService.createOrUpdatePersonnel(personnel));
    }

    @Override
    public PersonnelResponse updatePersonnel(PersonnelResponse request) {
        Personnel find = personnelService.findPersonnelByRefPersonnel(request.getRefPersonnel());
        publicLibraryMapper.updatePersonnelFromPersonnelResponse(request, find);
        return publicLibraryMapper.personnelToPersonnelResponse(personnelService.createOrUpdatePersonnel(find));
    }

    @Override
    public void deletePersonnel(String refPersonnel) {
        personnelService.deletePersonnel(refPersonnel);
    }

    @Override
    public PersonnelResponse findPersonnel(String refPersonnel) {
        Personnel personnel =personnelService.findPersonnelByRefPersonnel(refPersonnel);
        return publicLibraryMapper.personnelToPersonnelResponse(personnel);
    }

    @Override
    public List<PersonnelResponse> findAllPersonnel() {
        return personnelService.findAllPersonnel().stream()
                .map(publicLibraryMapper::personnelToPersonnelResponse).collect(Collectors.toList());
    }

    @Override
    public DocumentaryCollectionResponse createDocumentaryCollection(DocumentaryCollectionRequest documentaryCollectionRequest) {
        var documentaryCollection = publicLibraryMapper.documentaryCollectionRequestToDocumentaryCollection(documentaryCollectionRequest);
        return publicLibraryMapper.documentaryCollectionToDocumentaryCollectionResponse(documentaryCollectionService.createOrUpdateDocumentaryCollection(documentaryCollection));
    }

    @Override
    public DocumentaryCollectionResponse updateDocumentaryCollection(DocumentaryCollectionResponse request) {
        DocumentaryCollection find = documentaryCollectionService.findDocumentaryCollectionByRefDocumentaryCollection(request.getRefDocumentaryCollection());
        publicLibraryMapper.updateDocumentaryCollectionFromDocumentaryCollectionResponse(request, find);
        return publicLibraryMapper.documentaryCollectionToDocumentaryCollectionResponse(documentaryCollectionService.createOrUpdateDocumentaryCollection(find));
    }

    @Override
    public void deleteDocumentaryCollection(String refDocumentaryCollection) {
        documentaryCollectionService.deleteDocumentaryCollection(refDocumentaryCollection);
    }

    @Override
    public DocumentaryCollectionResponse findDocumentaryCollection(String refDocumentaryCollection) {
        DocumentaryCollection documentaryCollection = documentaryCollectionService.findDocumentaryCollectionByRefDocumentaryCollection(refDocumentaryCollection);
        return publicLibraryMapper.documentaryCollectionToDocumentaryCollectionResponse(documentaryCollection);
    }

    @Override
    public List<DocumentaryCollectionResponse> findAllDocumentaryCollection() {
        return documentaryCollectionService.findAllDocumentaryCollection().stream()
                .map(publicLibraryMapper::documentaryCollectionToDocumentaryCollectionResponse).collect(Collectors.toList());
    }

    @Override
    public BriefcaseBooksResponse createBriefcaseBooks(BriefcaseBooksRequest briefcaseBooksRequest) {
        var briefcaseBooks = publicLibraryMapper.briefcaseBooksRequestToBriefcaseBooks(briefcaseBooksRequest);
        return publicLibraryMapper.briefcaseBooksToBriefcaseBooksResponse(briefcaseBooksService.createOrUpdateBriefcaseBooks(briefcaseBooks));
    }

    @Override
    public BriefcaseBooksResponse updateBriefcaseBooks(BriefcaseBooksResponse request) {
        BriefcaseBooks find = briefcaseBooksService.findBriefcaseBooksByRefBriefcaseBooks(request.getRefBriefcaseBooks());
        publicLibraryMapper.updateBriefcaseBooksFromBriefcaseBooksResponse(request, find);
        return publicLibraryMapper.briefcaseBooksToBriefcaseBooksResponse(briefcaseBooksService.createOrUpdateBriefcaseBooks(find));
    }

    @Override
    public void deleteBriefcaseBooks(String refBriefcaseBooks) {
        briefcaseBooksService.deleteBriefcaseBooks(refBriefcaseBooks);
    }

    @Override
    public BriefcaseBooksResponse findBriefcaseBooks(String refBriefcaseBooks) {
        BriefcaseBooks briefcaseBooks = briefcaseBooksService.findBriefcaseBooksByRefBriefcaseBooks(refBriefcaseBooks);
        return publicLibraryMapper.briefcaseBooksToBriefcaseBooksResponse(briefcaseBooks);
    }

    @Override
    public List<BriefcaseBooksResponse> findAllBriefcaseBooks() {
        return briefcaseBooksService.findAllBriefcaseBooks().stream()
                .map(publicLibraryMapper::briefcaseBooksToBriefcaseBooksResponse).collect(Collectors.toList());
    }

    @Override
    public EquipmentResponse createEquipment(EquipmentRequest equipmentRequest) {
        var equipment = publicLibraryMapper.equipmentRequestToEquipment(equipmentRequest);
        return publicLibraryMapper.equipmentToEquipmentResponse(equipmentService.createOrUpdateEquipment(equipment));
    }

    @Override
    public EquipmentResponse updateEquipment(EquipmentResponse request) {
        Equipment find = equipmentService.findEquipmentByRefEquipment(request.getRefEquipment());
        publicLibraryMapper.updateEquipmentFromEquipmentResponse(request, find);
        return publicLibraryMapper.equipmentToEquipmentResponse(equipmentService.createOrUpdateEquipment(find));
    }

    @Override
    public void deleteEquipment(String refEquipment) {
        equipmentService.deleteEquipment(refEquipment);
    }

    @Override
    public EquipmentResponse findEquipment(String refEquipment) {
        Equipment equipment = equipmentService.findEquipmentByRefEquipment(refEquipment);
        return publicLibraryMapper.equipmentToEquipmentResponse(equipment);
    }

    @Override
    public List<EquipmentResponse> findAllEquipment() {
        return equipmentService.findAllEquipment().stream()
                .map(publicLibraryMapper::equipmentToEquipmentResponse).collect(Collectors.toList());
    }

    @Override
    public ItEquipmentResponse createItEquipment(ItEquipmentRequest itEquipmentRequest) {
        var itEquipment = publicLibraryMapper.itEquipmentRequestToItEquipment(itEquipmentRequest);
        return publicLibraryMapper.itEquipmentToItEquipmentResponse(itEquipmentService.createOrUpdateItEquipment(itEquipment));
    }

    @Override
    public ItEquipmentResponse updateItEquipment(ItEquipmentResponse request) {
        ItEquipment find = itEquipmentService.findItEquipmentByRefItEquipment(request.getRefItEquipment());
        publicLibraryMapper.updateItEquipmentFromItEquipmentResponse(request, find);
        return publicLibraryMapper.itEquipmentToItEquipmentResponse(itEquipmentService.createOrUpdateItEquipment(find));
    }

    @Override
    public void deleteItEquipment(String refItEquipment) {
        itEquipmentService.deleteItEquipment(refItEquipment);
    }

    @Override
    public ItEquipmentResponse findItEquipment(String refItEquipment) {
        ItEquipment itEquipment = itEquipmentService.findItEquipmentByRefItEquipment(refItEquipment);
        return publicLibraryMapper.itEquipmentToItEquipmentResponse(itEquipment);
    }

    @Override
    public List<ItEquipmentResponse> findAllItEquipment() {
        return itEquipmentService.findAllItEquipment().stream()
                .map(publicLibraryMapper::itEquipmentToItEquipmentResponse).collect(Collectors.toList());
    }

    @Override
    public SpacesResponse createSpaces(SpacesRequest spacesRequest) {
        var spaces = publicLibraryMapper.spacesRequestToSpaces(spacesRequest);
        return publicLibraryMapper.spacesToSpacesResponse(spacesService.createOrUpdateSpaces(spaces));
    }

    @Override
    public SpacesResponse updateSpaces(SpacesResponse request) {
        Spaces find = spacesService.findSpacesByRefSpaces(request.getRefSpaces());
        publicLibraryMapper.updateSpacesFromSpacesResponse(request, find);
        return publicLibraryMapper.spacesToSpacesResponse(spacesService.createOrUpdateSpaces(find));
    }

    @Override
    public void deleteSpaces(String refSpaces) {
        spacesService.deleteSpaces(refSpaces);
    }

    @Override
    public SpacesResponse findSpaces(String refSpaces) {
        Spaces spaces = spacesService.findSpacesByRefSpaces(refSpaces);
        return publicLibraryMapper.spacesToSpacesResponse(spaces);
    }

    @Override
    public List<SpacesResponse> findAllSpaces() {
        return spacesService.findAllSpaces().stream()
                .map(publicLibraryMapper::spacesToSpacesResponse).collect(Collectors.toList());
    }
}
