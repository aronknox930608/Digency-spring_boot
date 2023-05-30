package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.proposalproject.*;
import ma.digency.gov.amc.repository.entity.proposalproject.ProposalProject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;

import ma.digency.gov.amc.utils.enumeration.StatusEnum;

@Validated
@RequestMapping("proposal/")
public interface ProposalProjectController {

    @PostMapping(path = "artist-account")
    ResponseEntity<ArtistAccountResponse> createArtistAccount(@RequestBody @Validated ArtistAccountRequest request);

    @PutMapping(path = "artist-account/{refArtistAccount}")
    ResponseEntity<ArtistAccountResponse> updateArtistAccount(@PathVariable("refArtistAccount") @NotBlank String refArtistAccount,@RequestBody @Validated ArtistAccountRequest request);

    @PutMapping(path = "artist-account/{refArtistAccount}/finish")
    ResponseEntity<Void> validateArtistAccount(@PathVariable("refArtistAccount") @NotBlank String refArtistAccount);

    @DeleteMapping(path = "artist-account/{refArtistAccount}")
    ResponseEntity<Void> deleteArtistAccountByRef(@PathVariable("refArtistAccount") @NotBlank String refArtistAccount);

    @GetMapping(path="artist-account/static")
    ResponseEntity<List<ArtistAccountResponse>> getAllArtistAccounts();

    @GetMapping(path = "artist-account/get-by-user")
    ResponseEntity<ArtistAccountResponse> getArtistAccountByUser();

    @GetMapping(path="artist-account/user")
    ResponseEntity<PageableResponse<ArtistAccountResponse>> getAllArtistAccountsPageableForUser(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @GetMapping(path="artist-account")
    ResponseEntity<PageableResponse<ArtistAccountResponse>> getAllArtistAccountsPageable(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @GetMapping(path="artist-account/{refArtistAccount}")
    ResponseEntity<ArtistAccountResponse> getArtistAccount(@PathVariable("refArtistAccount") @NotBlank String refArtistAccount);

    @PostMapping(path = "company-account")
    ResponseEntity<CompanyAccountResponse> createCompanyAccount(@RequestBody @Validated CompanyAccountRequest request);

    @GetMapping(path = "company-account/all")
    ResponseEntity<List<CompanyAccountResponse>> findAllCompanyAccount();

    @GetMapping(path = "company-account/get-by-user")
    ResponseEntity<CompanyAccountResponse> getCompanyAccountByUser();

    @GetMapping(path="company-account")
    ResponseEntity<PageableResponse<CompanyAccountResponse>> findPageableCompanyAccount(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @GetMapping(path="company-account/get-by-ref/{refCompanyAccount}")
    ResponseEntity<CompanyAccountResponse> getCompanyAccount(@PathVariable("refCompanyAccount") @NotBlank String refCompanyAccount);

    @PutMapping("company-account/{refCompanyAccount}")
    ResponseEntity<Void> updateCompanyAccount(@PathVariable("refCompanyAccount") @NotBlank String refCompanyAccount,
            @RequestBody @Validated CompanyAccountRequest request);


    @PutMapping("company-account/update-status/{refCompanyAccount}")
    ResponseEntity<CompanyAccountResponse> updateCompanyAccountStatus(@PathVariable("refCompanyAccount") String refCompanyAccount,
                                                                      @RequestParam("status") StatusEnum request);

    @PostMapping(path = "artistic-profession")
    ResponseEntity<ArtisticProfessionResponse> createArtisticProfession(@RequestBody @Validated ArtisticProfessionRequest request);

    @GetMapping(path = "artistic-profession/all")
    ResponseEntity<List<ArtisticProfessionResponse>> findAllArtisticProfession();


    @GetMapping(path="artistic-profession/get-by-domain/{refArtisticProfessionDomain}")
    ResponseEntity<List<ArtisticProfessionResponse>> getArtisticProfession(@PathVariable("refArtisticProfessionDomain") @NotBlank String refArtisticProfessionDomain);

    @PutMapping("artistic-profession/{refArtisticProfession}")
    ResponseEntity<ArtisticProfessionResponse> updateArtisticProfession(@PathVariable("refArtisticProfession") @NotBlank String refArtisticProfession,
                                                  @RequestBody @Validated ArtisticProfessionRequest request);

    @PostMapping(path = "artistic-profession-domain")
    ResponseEntity<ArtisticProfessionDomainResponse> createArtisticProfessionDomain(@RequestBody @Validated ArtisticProfessionDomainRequest request);

    @GetMapping(path = "artistic-profession-domain/all")
    ResponseEntity<List<ArtisticProfessionDomainResponse>> findAllArtisticProfessionDomain();


    @GetMapping(path="artistic-profession-domain/get-by-category/{refArtisticProfessionCategory}")
    ResponseEntity<List<ArtisticProfessionDomainResponse>> getArtisticProfessionDomainByCategory(@PathVariable("refArtisticProfessionCategory") @NotBlank String refArtisticProfessionCategory);

    @PutMapping("artistic-profession-domain/{refArtisticProfessionDomain}")
    ResponseEntity<ArtisticProfessionDomainResponse> updateArtisticProfessionDomain(@PathVariable("refArtisticProfessionDomain") @NotBlank String refArtisticProfessionDomain,
                                                                                    @RequestBody @Validated ArtisticProfessionDomainRequest request);


    @PostMapping(path = "artistic-profession-categories")
    ResponseEntity<ArtisticProfessionCategoryResponse> createArtisticProfessionCategory(@RequestBody @Validated ArtisticProfessionCategoryRequest request);

    @GetMapping(path = "artistic-profession-categories/all")
    ResponseEntity<List<ArtisticProfessionCategoryResponse>> findAllArtisticProfessionCategory();


    @PutMapping("artistic-profession-categories/{refArtisticProfessionCategory}")
    ResponseEntity<ArtisticProfessionCategoryResponse> updateArtisticProfessionCategory(@PathVariable("refArtisticProfessionCategory") @NotBlank String refArtisticProfessionCategory,
                                                                                        @RequestBody @Validated ArtisticProfessionCategoryRequest request);
    @DeleteMapping("artistic-profession/{refArtisticProfession}")
    ResponseEntity<Boolean> deleteArtisticProfession(@PathVariable("refArtisticProfession") @NotBlank String refArtisticProfession);

    @DeleteMapping("artistic-profession-domain/{refArtisticProfessionDomain}")
    ResponseEntity<Boolean> deleteArtisticProfessionDomain(@PathVariable("refArtisticProfessionDomain") @NotBlank String refArtisticProfessionDomain);

    @DeleteMapping("artistic-profession-category/{refArtisticProfessionCategory}")
    ResponseEntity<Boolean> deleteArtisticProfessionCategory(@PathVariable("refArtisticProfessionCategory") @NotBlank String refArtisticProfessionCategory);


    @PostMapping("domains")
    ResponseEntity<ProjectDomainResponse> addProjectDomain(@RequestBody ProjectDomainRequest domain);

    @GetMapping("domains/{refDomain}")
    ResponseEntity<ProjectDomainResponse> getProjectDomain(@PathVariable("refDomain") String refDomain);

    @PutMapping("domains/{refDomain}")
    ResponseEntity<ProjectDomainResponse> updateProjectDomain(@PathVariable("refDomain") String refDomain ,
                                                              @RequestBody ProjectDomainRequest domain);

    @DeleteMapping("domains/{refDomain}")
    ResponseEntity<Void> deleteProjectDomain(@PathVariable("refDomain") String refDomain);

    @GetMapping("domains")
    ResponseEntity<List<ProjectDomainResponse>> getAllProjectDomain();

    @GetMapping("domains/paging")
    ResponseEntity<PageableResponse<ProjectDomainResponse>> getAllProjectDomainPageable(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @PutMapping("domains/{refDomain}/sub-domains")
    ResponseEntity<ProjectDomainResponse> addOrUpdateSubDomain(@PathVariable("refDomain") String refDomain,
                                                               @RequestBody List<ProjectSubDomainResponse> subDomains);

    @GetMapping("{refProposalProject}")
    ResponseEntity<ProposalProjectResponse> getProposalProject(@PathVariable("refProposalProject") String refProposalProject);

    @PutMapping("{refProposalProject}")
    ResponseEntity<ProposalProjectResponse> updateProposalProject(@PathVariable("refProposalProject") String refProposalProject,
                                                                  @RequestBody ProposalProjectRequest request);

    @GetMapping
    ResponseEntity<List<ProposalProjectResponse>> getAllProposalProject();

    @GetMapping("/paging")
    ResponseEntity<PageableResponse<ProposalProjectResponse>> getAllProposalProjectPageable(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @PostMapping
    ResponseEntity<ProposalProjectResponse> createProposalProject(@RequestBody ProposalProjectRequest request);


    @PostMapping("/bulk")
    ResponseEntity<List<ProposalProjectResponse>> createProposalProjects(@RequestBody List<ProposalProjectRequest> request);

    @PutMapping("validate-demande/{type}/ref/{refAccount}")
    ResponseEntity<Boolean> validateDemand(@PathVariable("refAccount") @NotBlank String refAccount,@PathVariable("type") @NotBlank String type);


    @PostMapping(path = "cooperative-account")
    ResponseEntity<CooperativeAccountResponse> createCooperativeAccount(@RequestBody @Validated CooperativeAccountRequest request);

    @GetMapping(path = "cooperative-account/all")
    ResponseEntity<List<CooperativeAccountResponse>> findAllCooperativeAccount();

    @GetMapping(path = "cooperative-account/get-by-user")
    ResponseEntity<CooperativeAccountResponse> getCooperativeAccountByUser();

    @GetMapping(path="cooperative-account")
    ResponseEntity<PageableResponse<CooperativeAccountResponse>> findPageableCooperativeAccount(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @GetMapping(path="cooperative-account/get-by-ref/{refCooperativeAccount}")
    ResponseEntity<CooperativeAccountResponse> getCooperativeAccount(@PathVariable("refCooperativeAccount") @NotBlank String refCooperativeAccount);

    @PutMapping("cooperative-account/{refCooperativeAccount}")
    ResponseEntity<Void> updateCooperativeAccount(@PathVariable("refCooperativeAccount") @NotBlank String refCooperativeAccount,
                                                  @RequestBody @Validated CooperativeAccountRequest request);


    @PutMapping("cooperative-account/update-status/{refCooperativeAccount}")
    ResponseEntity<CooperativeAccountResponse> updateCooperativeAccountStatus(@PathVariable("refCooperativeAccount") String refCooperativeAccount,
                                                                              @RequestParam("status") StatusEnum request);

    @GetMapping(path="cooperative-account/user")
    ResponseEntity<PageableResponse<CooperativeAccountResponse>> getAllCooperativeAccountsPageableForUser(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @GetMapping(path="company-account/user")
    ResponseEntity<PageableResponse<CompanyAccountResponse>> getAllCompanyAccountsPageableForUser(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);


    @PostMapping(path = "cooperative-account-book")
    ResponseEntity<CooperativeAccountBookResponse> createCooperativeAccountBook(@RequestBody @Validated CooperativeAccountBookRequest request);

    @GetMapping(path = "cooperative-account-book/all")
    ResponseEntity<List<CooperativeAccountBookResponse>> findAllCooperativeAccountBook();

    @GetMapping(path = "cooperative-account-book/get-by-user")
    ResponseEntity<CooperativeAccountBookResponse> getCooperativeAccountBookByUser();

    @GetMapping(path="cooperative-account-book")
    ResponseEntity<PageableResponse<CooperativeAccountBookResponse>> findPageableCooperativeAccountBook(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @GetMapping(path="cooperative-account-book/get-by-ref/{refCooperativeAccountBook}")
    ResponseEntity<CooperativeAccountBookResponse> getCooperativeAccountBook(@PathVariable("refCooperativeAccountBook") @NotBlank String refCooperativeAccountBook);

    @PutMapping("cooperative-account-book/{refCooperativeAccountBook}")
    ResponseEntity<Void> updateCooperativeAccountBook(@PathVariable("refCooperativeAccountBook") @NotBlank String refCooperativeAccountBook,
                                                      @RequestBody @Validated CooperativeAccountBookRequest request);


    @PutMapping("cooperative-account-book/update-status/{refCooperativeAccountBook}")
    ResponseEntity<CooperativeAccountBookResponse> updateCooperativeAccountBookStatus(@PathVariable("refCooperativeAccountBook") String refCooperativeAccountBook,
                                                                                      @RequestParam("status") StatusEnum request);

    @GetMapping(path="cooperative-account-book/user")
    ResponseEntity<PageableResponse<CooperativeAccountBookResponse>> getAllCooperativeAccountBooksPageableForUser(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @GetMapping("/components")
    ResponseEntity<List<String>> getComponents();
}
