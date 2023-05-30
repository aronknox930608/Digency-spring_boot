package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.publiclibrary.*;
import ma.digency.gov.amc.repository.entity.publiclibrary.*;

import java.util.List;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface PublicLibraryMapper {

    PublicLibraryResponse publicLibraryToPublicLibraryResponse(PublicLibrary publicLibrary);

    PublicLibrary publicLibraryResponseToPublicLibrary(PublicLibraryResponse response);

    PublicLibrary publicLibraryRequestToPublicLibrary(PublicLibraryRequest publicLibraryRequest);

    PublicLibrary updatePublicLibraryFromPublicLibraryResponse(PublicLibraryResponse response, PublicLibrary author);

    List<PublicLibraryResponse> publicLibraryToPublicLibraryResponse(List<PublicLibrary> publicLibrary);

    PersonnelResponse personnelToPersonnelResponse(Personnel personnel);

    Personnel personnelRequestToPersonnel(PersonnelRequest personnelRequest);

    Personnel updatePersonnelFromPersonnelResponse(PersonnelResponse response, Personnel personnel);

    BriefcaseBooksResponse briefcaseBooksToBriefcaseBooksResponse(BriefcaseBooks briefcaseBooks);

    BriefcaseBooks briefcaseBooksRequestToBriefcaseBooks(BriefcaseBooksRequest briefcaseBooksRequest);

    BriefcaseBooks updateBriefcaseBooksFromBriefcaseBooksResponse(BriefcaseBooksResponse response, BriefcaseBooks briefcaseBooks);

    DocumentaryCollectionResponse documentaryCollectionToDocumentaryCollectionResponse(DocumentaryCollection documentaryCollection);

    DocumentaryCollection documentaryCollectionRequestToDocumentaryCollection(DocumentaryCollectionRequest documentaryCollectionRequest);

    DocumentaryCollection updateDocumentaryCollectionFromDocumentaryCollectionResponse(DocumentaryCollectionResponse response, DocumentaryCollection documentaryCollection);

    EquipmentResponse equipmentToEquipmentResponse(Equipment equipment);

    Equipment equipmentRequestToEquipment(EquipmentRequest equipmentRequest);

    Equipment updateEquipmentFromEquipmentResponse(EquipmentResponse response, Equipment equipment);

    ItEquipmentResponse itEquipmentToItEquipmentResponse(ItEquipment itEquipment);

    ItEquipment itEquipmentRequestToItEquipment(ItEquipmentRequest itEquipmentRequest);

    ItEquipment updateItEquipmentFromItEquipmentResponse(ItEquipmentResponse response, ItEquipment itEquipment);

    SpacesResponse spacesToSpacesResponse(Spaces spaces);

    Spaces spacesRequestToSpaces(SpacesRequest spacesRequest);

    Spaces updateSpacesFromSpacesResponse(SpacesResponse response, Spaces spaces);

}
