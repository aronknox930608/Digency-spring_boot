package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.proposalproject.*;
import ma.digency.gov.amc.process.ProposalProjectProcess;
import ma.digency.gov.amc.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import java.security.Principal;
import java.util.List;

@RestController
@Tag(name = "Proposal")
public class ProposalProjectControllerImpl implements ProposalProjectController {
    @Autowired
    private ProposalProjectProcess proposalProjectProcess;

    @Override
    public ResponseEntity<ArtistAccountResponse> createArtistAccount(ArtistAccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                proposalProjectProcess.createArtistAccount(request)
        );
    }

    @Override
    public ResponseEntity<ArtistAccountResponse> updateArtistAccount(String refArtistAccount, ArtistAccountRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                proposalProjectProcess.updateArtistAccount(request,refArtistAccount)
        );
    }

    @Override
    public ResponseEntity<Void> validateArtistAccount(String refArtistAccount) {
        proposalProjectProcess.validateArtistAccountByRef(refArtistAccount);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> deleteArtistAccountByRef(String ref) {
        proposalProjectProcess.deleteArtistAccountByRef(ref);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<ArtistAccountResponse>> getAllArtistAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findAllArtistAccounts());
    }

    @Override
    public ResponseEntity<ArtistAccountResponse> getArtistAccountByUser() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findArtistAccountByUser());
    }

    @Override
    public ResponseEntity<PageableResponse<ArtistAccountResponse>> getAllArtistAccountsPageableForUser(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(this.proposalProjectProcess.findAllArtistAccountsByUserNotDELETED( SearchUtils.createPageableSortCreationDate(page,size)));
    }

    @Override
    public ResponseEntity<PageableResponse<ArtistAccountResponse>> getAllArtistAccountsPageable(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(this.proposalProjectProcess.findAllArtistAccountsPageable( SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<ArtistAccountResponse> getArtistAccount(String ref) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findArtistAccountByRef(ref));
    }


    @Override
    public ResponseEntity<CompanyAccountResponse> createCompanyAccount(CompanyAccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proposalProjectProcess.createCompanyAccount(request));
    }

    @Override
    public ResponseEntity<List<CompanyAccountResponse>> findAllCompanyAccount() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findAllCompanyAccount());
    }

    @Override
    public ResponseEntity<CompanyAccountResponse> getCompanyAccountByUser() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.getCompanyAccountByUser());
    }

    @Override
    public ResponseEntity<PageableResponse<CompanyAccountResponse>> findPageableCompanyAccount(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findPageableCompanyAccount( SearchUtils.createPageable(page,size)));

    }

    @Override
    public ResponseEntity<CompanyAccountResponse> getCompanyAccount(String refCompanyAccount) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.getCompanyAccount(refCompanyAccount));
    }

    @Override
    public ResponseEntity<Void> updateCompanyAccount(String refCompanyAccount, CompanyAccountRequest request) {
        proposalProjectProcess.updateCompanyAccount(refCompanyAccount,request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<CompanyAccountResponse> updateCompanyAccountStatus(String refCompanyAccount, StatusEnum request) {
         return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.updateCompanyAccountStatus(refCompanyAccount,request));
    }

    @Override
    public ResponseEntity<ArtisticProfessionResponse> createArtisticProfession(ArtisticProfessionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.createArtisticProfession(request));
    }

    @Override
    public ResponseEntity<List<ArtisticProfessionResponse>> findAllArtisticProfession() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findAllArtisticProfession());
    }

    @Override
    public ResponseEntity<List<ArtisticProfessionResponse>> getArtisticProfession(String refArtisticProfessionDomain) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findAllArtisticProfessionsByDomain(refArtisticProfessionDomain));
    }

    @Override
    public ResponseEntity<ArtisticProfessionResponse> updateArtisticProfession(String refArtisticProfession, ArtisticProfessionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.updateArtisticProfession(refArtisticProfession, request));
    }


    @Override
    public ResponseEntity<ArtisticProfessionDomainResponse> createArtisticProfessionDomain(ArtisticProfessionDomainRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.createArtisticProfessionDomain(request));
    }

    @Override
    public ResponseEntity<List<ArtisticProfessionDomainResponse>> findAllArtisticProfessionDomain() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findAllArtisticProfessionDomain());
    }

    @Override
    public ResponseEntity<List<ArtisticProfessionDomainResponse>> getArtisticProfessionDomainByCategory(String refArtisticProfessionCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findAllArtisticProfessionDomainsByCategory(refArtisticProfessionCategory));
    }

    @Override
    public ResponseEntity<ArtisticProfessionDomainResponse> updateArtisticProfessionDomain(String refArtisticProfessionDomain, ArtisticProfessionDomainRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.updateArtisticProfessionDomain(refArtisticProfessionDomain, request));
    }


    @Override
    public ResponseEntity<ArtisticProfessionCategoryResponse> createArtisticProfessionCategory(ArtisticProfessionCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.createArtisticProfessionCategory(request));
    }

    @Override
    public ResponseEntity<List<ArtisticProfessionCategoryResponse>> findAllArtisticProfessionCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findAllArtisticProfessionCategory());
    }


    @Override
    public ResponseEntity<ArtisticProfessionCategoryResponse> updateArtisticProfessionCategory(String refArtisticProfessionCategory, ArtisticProfessionCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.updateArtisticProfessionCategory(refArtisticProfessionCategory, request));
    }

    @Override
    public ResponseEntity<Boolean> deleteArtisticProfession(String refArtisticProfession) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.deleteArtisticProfession(refArtisticProfession));
    }

    @Override
    public ResponseEntity<Boolean> deleteArtisticProfessionCategory(String refArtisticProfessionCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.deleteArtisticProfessionCategory(refArtisticProfessionCategory));
    }

    @Override
    public ResponseEntity<Boolean> validateDemand(String ref, String type) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.validateDemand(ref,type));
    }

    @Override
    public ResponseEntity<ProjectDomainResponse> addProjectDomain(ProjectDomainRequest domain) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                proposalProjectProcess.addProjectDomain(domain)
        );
    }

    @Override
    public ResponseEntity<ProjectDomainResponse> getProjectDomain(String refDomain) {
        return ResponseEntity.status(HttpStatus.OK).body(
                proposalProjectProcess.getProjectDomain(refDomain)
        );
    }

    @Override
    public ResponseEntity<ProjectDomainResponse> updateProjectDomain(String refDomain, ProjectDomainRequest domain) {
        return ResponseEntity.status(HttpStatus.OK).body(
                proposalProjectProcess.updateProjectDomain(refDomain,domain)
        );
    }

    @Override
    public ResponseEntity<Void> deleteProjectDomain(String refDomain) {
        proposalProjectProcess.deleteProjectDomain(refDomain);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<ProjectDomainResponse>> getAllProjectDomain() {
        return ResponseEntity.status(HttpStatus.OK).body(
                proposalProjectProcess.getAllProjectDomain()
        );
    }

    @Override
    public ResponseEntity<PageableResponse<ProjectDomainResponse>> getAllProjectDomainPageable(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(this.proposalProjectProcess.getAllProjectDomainPageable( SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<ProjectDomainResponse> addOrUpdateSubDomain(String refDomain, List<ProjectSubDomainResponse> subDomains) {
        return ResponseEntity.status(HttpStatus.OK).body(
                proposalProjectProcess.addOrUpdateSubDomain(refDomain,subDomains)
        );
    }

    @Override
    public ResponseEntity<ProposalProjectResponse> getProposalProject(String refProposalProject) {
        return ResponseEntity.status(HttpStatus.OK).body(
                proposalProjectProcess.getProposalProject(refProposalProject)
        );
    }

    @Override
    public ResponseEntity<ProposalProjectResponse> updateProposalProject(String refProposalProject, ProposalProjectRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                proposalProjectProcess.updateProposalProject(refProposalProject,request)
        );
    }

    @Override
    public ResponseEntity<List<ProposalProjectResponse>> getAllProposalProject() {
        return ResponseEntity.status(HttpStatus.OK).body(
                proposalProjectProcess.getAllProposalProject()
        );
    }

    @Override
    public ResponseEntity<PageableResponse<ProposalProjectResponse>> getAllProposalProjectPageable(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(this.proposalProjectProcess.getAllProposalProjectPageable( SearchUtils.createPageable(page,size)));
    }

    @Override
    public ResponseEntity<ProposalProjectResponse> createProposalProject(ProposalProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                proposalProjectProcess.createProposalProject(request)
        );
    }

    @Override
    public ResponseEntity<List<ProposalProjectResponse>> createProposalProjects(List<ProposalProjectRequest> request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                proposalProjectProcess.createProposalProjects(request)
        );
    }


    @Override
    public ResponseEntity<Boolean> deleteArtisticProfessionDomain(String refArtisticProfessionDomain) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.deleteArtisticProfessionDomain(refArtisticProfessionDomain));
    }


    @Override
    public ResponseEntity<CooperativeAccountResponse> createCooperativeAccount(CooperativeAccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proposalProjectProcess.createCooperativeAccount(request));
    }

    @Override
    public ResponseEntity<List<CooperativeAccountResponse>> findAllCooperativeAccount() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findAllCooperativeAccount());
    }

    @Override
    public ResponseEntity<CooperativeAccountResponse> getCooperativeAccountByUser() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.getCooperativeAccountByUser());
    }

    @Override
    public ResponseEntity<PageableResponse<CooperativeAccountResponse>> findPageableCooperativeAccount(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findPageableCooperativeAccount( SearchUtils.createPageable(page,size)));

    }

    @Override
    public ResponseEntity<CooperativeAccountResponse> getCooperativeAccount(String refCooperativeAccount) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.getCooperativeAccount(refCooperativeAccount));
    }

    @Override
    public ResponseEntity<Void> updateCooperativeAccount(String refCooperativeAccount, CooperativeAccountRequest request) {
        proposalProjectProcess.updateCooperativeAccount(refCooperativeAccount,request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<CooperativeAccountResponse> updateCooperativeAccountStatus(String refCooperativeAccount, StatusEnum request) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.updateCooperativeAccountStatus(refCooperativeAccount,request));
    }
    @Override
    public ResponseEntity<PageableResponse<CooperativeAccountResponse>> getAllCooperativeAccountsPageableForUser(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(this.proposalProjectProcess.findAllCooperativeAccountsByUserNotDELETED( SearchUtils.createPageableSortCreationDate(page,size)));
    }
    @Override
    public ResponseEntity<PageableResponse<CompanyAccountResponse>> getAllCompanyAccountsPageableForUser(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(this.proposalProjectProcess.findAllCompanyAccountsByUserNotDELETED( SearchUtils.createPageableSortCreationDate(page,size)));
    }

    @Override
    public ResponseEntity<CooperativeAccountBookResponse> createCooperativeAccountBook(CooperativeAccountBookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proposalProjectProcess.createCooperativeAccountBook(request));
    }

    @Override
    public ResponseEntity<List<CooperativeAccountBookResponse>> findAllCooperativeAccountBook() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findAllCooperativeAccountBook());
    }

    @Override
    public ResponseEntity<CooperativeAccountBookResponse> getCooperativeAccountBookByUser() {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.getCooperativeAccountBookByUser());
    }

    @Override
    public ResponseEntity<PageableResponse<CooperativeAccountBookResponse>> findPageableCooperativeAccountBook(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.findPageableCooperativeAccountBook( SearchUtils.createPageable(page,size)));

    }

    @Override
    public ResponseEntity<CooperativeAccountBookResponse> getCooperativeAccountBook(String refCooperativeAccountBook) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.getCooperativeAccountBook(refCooperativeAccountBook));
    }

    @Override
    public ResponseEntity<Void> updateCooperativeAccountBook(String refCooperativeAccountBook, CooperativeAccountBookRequest request) {
        proposalProjectProcess.updateCooperativeAccountBook(refCooperativeAccountBook,request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<CooperativeAccountBookResponse> updateCooperativeAccountBookStatus(String refCooperativeAccountBook, StatusEnum request) {
        return ResponseEntity.status(HttpStatus.OK).body(proposalProjectProcess.updateCooperativeAccountBookStatus(refCooperativeAccountBook,request));
    }
    @Override
    public ResponseEntity<PageableResponse<CooperativeAccountBookResponse>> getAllCooperativeAccountBooksPageableForUser(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(this.proposalProjectProcess.findAllCooperativeAccountBooksByUserNotDELETED( SearchUtils.createPageableSortCreationDate(page,size)));
    }

    @Override
    public ResponseEntity<List<String>> getComponents() {
        return ResponseEntity.status(HttpStatus.OK).body(this.proposalProjectProcess.getComponentFromEnum());
    }

}
