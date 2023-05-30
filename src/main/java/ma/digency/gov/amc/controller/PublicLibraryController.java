package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.library.*;
import ma.digency.gov.amc.dto.participant.PrinterRequest;
import ma.digency.gov.amc.dto.participant.PrinterResponse;
import ma.digency.gov.amc.dto.publiclibrary.*;
import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.publiclibrary.PublicLibrary;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RequestMapping("publiclibrary/")
public interface PublicLibraryController {

    @PostMapping("publiclibraries/")
    ResponseEntity<PublicLibraryResponse> createPublicLibrary(@RequestPart (name = "publicLibrary" , required = true) PublicLibraryRequest request,
                                                              @RequestPart(name = "picture" , required = false) MultipartFile multipartFile);

    @PutMapping("publiclibraries/")
    ResponseEntity<PublicLibraryResponse> updatePublicLibrary(@RequestPart (name = "publicLibrary" , required = true) PublicLibraryResponse request,
                                                @RequestPart(name = "picture" , required = false) MultipartFile multipartFile);

    @DeleteMapping("publiclibraries/{refPublicLibrary}")
    ResponseEntity<Void> deletePublicLibrary(@PathVariable("refPublicLibrary") @NotBlank String refPublicLibrary);

    @GetMapping("publiclibraries/{refPublicLibrary}")
    ResponseEntity<PublicLibraryResponse> getPublicLibrary(@PathVariable("refPublicLibrary") @NotBlank String refPublicLibrary);

    @GetMapping("publiclibraries/")
    ResponseEntity<List<PublicLibraryResponse>> getAllPublicLibraries();

    @GetMapping("publiclibraries/pageable")
    ResponseEntity<PageableResponse<PublicLibraryResponse>> getAllPublicLibraries(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("publicLibraries/search")
    ResponseEntity<Page<PublicLibrary>> searchPublicLibraries(@RequestParam(required = false) int pageNumber,
                                                           int pageSize,
                                                           @RequestParam(required = false) Sort.Direction sortDirection,
                                                           @RequestParam(required = false) String sortBy,
                                                           @RequestParam(required = false) String libraryName ,
                                                           @RequestParam(required = false) String nameOfTheResponsible,
                                                           @RequestParam(required = false) String Partner,
                                                           @RequestParam(required = false) String libraryStatus);

    @GetMapping("publicLibraries/export")
    ResponseEntity<Resource> exportLibrariesData();

    /////////////////Personnel//////////////////

    @PostMapping("personnel/")
    PersonnelResponse createPersonnel(@RequestBody @Validated PersonnelRequest request);

    @PutMapping("personnel/")
    ResponseEntity<PersonnelResponse> updatePersonnel(@RequestBody @Validated PersonnelResponse request);

    @DeleteMapping("Personnel/{refPersonnel}")
    ResponseEntity<Void> deletePersonnel(@PathVariable("refPersonnel") @NotBlank String refPersonnel);

    @GetMapping("personnel/{refPersonnel}")
    ResponseEntity<PersonnelResponse> getPersonnel(@PathVariable("refPersonnel") @NotBlank String refPersonnel);

    @GetMapping("personnel/")
    ResponseEntity<List<PersonnelResponse>> getAllPersonnel();

////////////////BriefcaseBooks//////////////////////:

    @PostMapping("briefcaseBooks/")
    BriefcaseBooksResponse createBriefcaseBooks(@RequestBody @Validated BriefcaseBooksRequest request);

    @PutMapping("briefcaseBooks/")
    ResponseEntity<BriefcaseBooksResponse> updateBriefcaseBooks(@RequestBody @Validated BriefcaseBooksResponse request);

    @DeleteMapping("briefcaseBooks/{refBriefcaseBooks}")
    ResponseEntity<Void> deleteBriefcaseBooks(@PathVariable("refBriefcaseBooks") @NotBlank String refBriefcaseBooks);

    @GetMapping("briefcaseBooks/{refBriefcaseBooks}")
    ResponseEntity<BriefcaseBooksResponse> getBriefcaseBooks(@PathVariable("refBriefcaseBooks") @NotBlank String refBriefcaseBooks);

    @GetMapping("briefcaseBooks/")
    ResponseEntity<List<BriefcaseBooksResponse>> getAllBriefcaseBooks();

    //////////////DocumentaryCollection///////////

    @PostMapping("documentaryCollection/")
    DocumentaryCollectionResponse createDocumentaryCollection(@RequestBody @Validated DocumentaryCollectionRequest request);

    @PutMapping("documentaryCollection/")
    ResponseEntity<DocumentaryCollectionResponse> updateDocumentaryCollection(@RequestBody @Validated DocumentaryCollectionResponse request);

    @DeleteMapping("documentaryCollection/{refDocumentaryCollection}")
    ResponseEntity<Void> deleteDocumentaryCollection(@PathVariable("refDocumentaryCollection") @NotBlank String refDocumentaryCollection);

    @GetMapping("documentaryCollection/{refDocumentaryCollection}")
    ResponseEntity<DocumentaryCollectionResponse> getDocumentaryCollection(@PathVariable("refDocumentaryCollection") @NotBlank String refDocumentaryCollection);

    @GetMapping("documentaryCollection/")
    ResponseEntity<List<DocumentaryCollectionResponse>> getAllDocumentaryCollection();

    ////////Equipment//////////////////

    @PostMapping("equipment/")
    EquipmentResponse createEquipment(@RequestBody @Validated EquipmentRequest request);

    @PutMapping("equipment/")
    ResponseEntity<EquipmentResponse> updateEquipment(@RequestBody @Validated EquipmentResponse request);

    @DeleteMapping("equipment/{refEquipment}")
    ResponseEntity<Void> deleteEquipment(@PathVariable("refEquipment") @NotBlank String refEquipment);

    @GetMapping("equipment/{refEquipment}")
    ResponseEntity<EquipmentResponse> getEquipment(@PathVariable("refEquipment") @NotBlank String refEquipment);

    @GetMapping("equipment/")
    ResponseEntity<List<EquipmentResponse>> getAllEquipment();

    ////////itEquipment//////////////////

    @PostMapping("itEquipment/")
    ItEquipmentResponse createItEquipment(@RequestBody @Validated ItEquipmentRequest request);

    @PutMapping("itEquipment/")
    ResponseEntity<ItEquipmentResponse> updateItEquipment(@RequestBody @Validated ItEquipmentResponse request);

    @DeleteMapping("itEquipment/{refItEquipment}")
    ResponseEntity<Void> deleteItEquipment(@PathVariable("refItEquipment") @NotBlank String refItEquipment);

    @GetMapping("itEquipment/{refItEquipment}")
    ResponseEntity<ItEquipmentResponse> getItEquipment(@PathVariable("refItEquipment") @NotBlank String refItEquipment);

    @GetMapping("itEquipment/")
    ResponseEntity<List<ItEquipmentResponse>> getAllItEquipment();

    ////////Spaces//////////////////

    @PostMapping("spaces/")
    SpacesResponse createSpaces(@RequestBody @Validated SpacesRequest request);

    @PutMapping("spaces/")
    ResponseEntity<SpacesResponse> updateSpaces(@RequestBody @Validated SpacesResponse request);

    @DeleteMapping("spaces/{refSpaces}")
    ResponseEntity<Void> deleteSpaces(@PathVariable("refSpaces") @NotBlank String refSpaces);

    @GetMapping("spaces/{refSpaces}")
    ResponseEntity<SpacesResponse> getSpaces(@PathVariable("refSpaces") @NotBlank String refSpaces);

    @GetMapping("spaces/")
    ResponseEntity<List<SpacesResponse>> getAllSpaces();
}
