package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.proposalproject.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import java.util.List;

public interface ProposalProjectProcess {

    ArtistAccountResponse createArtistAccount(ArtistAccountRequest artistAccountRequest);

    ArtistAccountResponse updateArtistAccount(ArtistAccountRequest artistAccountRequest,String ref);

    ArtistAccountResponse findArtistAccountByRef(String ref);

    ArtistAccountResponse findArtistAccountById(Long id);

    List<ArtistAccountResponse> findAllArtistAccounts();

    PageableResponse<ArtistAccountResponse> findAllArtistAccountsByUserNotDELETED(Pageable pageable);

    void deleteArtistAccountById(Long id);

    void validateArtistAccountByRef(String ref);

    void deleteArtistAccountByRef(String ref);

    ArtistAccountResponse findArtistAccountByUser();

    PageableResponse<ArtistAccountResponse> findAllArtistAccountsPageable(Pageable pageable);

    CompanyAccountResponse createCompanyAccount(CompanyAccountRequest request);

    List<CompanyAccountResponse> findAllCompanyAccount();

    CompanyAccountResponse getCompanyAccount(String refCompanyAccount);

    CompanyAccountResponse updateCompanyAccountStatus(String refCompanyAccount, StatusEnum request);

    void updateCompanyAccount(String refCompanyAccount, CompanyAccountRequest request);

    PageableResponse<CompanyAccountResponse> findPageableCompanyAccount(Pageable pageable);

    GeneralInformationResponse createGeneralInformation(GeneralInformationRequest request);

    CompanyAccountResponse getCompanyAccountByUser();

     ArtisticProfessionResponse createArtisticProfession(ArtisticProfessionRequest request);

     List<ArtisticProfessionResponse> findAllArtisticProfession();

     ArtisticProfessionResponse getArtisticProfession(String refArtisticProfession);

     ArtisticProfessionResponse updateArtisticProfession(String refArtisticProfession, ArtisticProfessionRequest request);

     List<ArtisticProfessionResponse> findAllArtisticProfessionsByDomain(String refArtisticProfessionDomain);

     ArtisticProfessionCategoryResponse createArtisticProfessionCategory(ArtisticProfessionCategoryRequest request);

     List<ArtisticProfessionCategoryResponse> findAllArtisticProfessionCategory();

     ArtisticProfessionCategoryResponse getArtisticProfessionCategory(String refArtisticProfessionCategory);

     ArtisticProfessionCategoryResponse updateArtisticProfessionCategory(String refArtisticProfessionCategory, ArtisticProfessionCategoryRequest request);

     ArtisticProfessionDomainResponse createArtisticProfessionDomain(ArtisticProfessionDomainRequest request);

     List<ArtisticProfessionDomainResponse> findAllArtisticProfessionDomain();

     List<ArtisticProfessionDomainResponse> findAllArtisticProfessionDomainsByCategory(String refArtisticProfessionCategory);

     ArtisticProfessionDomainResponse updateArtisticProfessionDomain(String refArtisticProfessionDomain, ArtisticProfessionDomainRequest request);

     Boolean deleteArtisticProfession(String refArtisticProfession);

     Boolean deleteArtisticProfessionDomain(String refArtisticProfessionDomain);

     Boolean deleteArtisticProfessionCategory(String refArtisticProfessionCategory);

    ProjectDomainResponse addOrUpdateSubDomain(String refDomain, List<ProjectSubDomainResponse> subDomains);

    List<ProjectDomainResponse> getAllProjectDomain();

    PageableResponse<ProjectDomainResponse> getAllProjectDomainPageable(Pageable pageable);

    void deleteProjectDomain(String refDomain);

    ProjectDomainResponse updateProjectDomain(String refDomain, ProjectDomainRequest domain);

    ProjectDomainResponse getProjectDomain(String refDomain);

    ProjectDomainResponse addProjectDomain(ProjectDomainRequest domain);

    ProposalProjectResponse getProposalProject(String refProposalProject);

    ProposalProjectResponse updateProposalProject(String refProposalProject, ProposalProjectRequest request);

    List<ProposalProjectResponse> getAllProposalProject();

    PageableResponse<ProposalProjectResponse> getAllProposalProjectPageable(Pageable pageable);

    ProposalProjectResponse createProposalProject(ProposalProjectRequest request);

    List<ProposalProjectResponse> createProposalProjects(List<ProposalProjectRequest> request);

    Boolean validateDemand(String ref,String Type);


    CooperativeAccountResponse createCooperativeAccount(CooperativeAccountRequest request);

    List<CooperativeAccountResponse> findAllCooperativeAccount();

    CooperativeAccountResponse getCooperativeAccount(String refCooperativeAccount);

    CooperativeAccountResponse updateCooperativeAccountStatus(String refCooperativeAccount, StatusEnum request);

    void updateCooperativeAccount(String refCooperativeAccount, CooperativeAccountRequest request);

    PageableResponse<CooperativeAccountResponse> findPageableCooperativeAccount(Pageable pageable);

    CooperativeAccountResponse getCooperativeAccountByUser();

    PageableResponse<CompanyAccountResponse> findAllCompanyAccountsByUserNotDELETED(Pageable pageable);

    PageableResponse<CooperativeAccountResponse> findAllCooperativeAccountsByUserNotDELETED(Pageable pageable);



    BookPersonResponse createBookPerson(BookPersonRequest request);

    BookPersonResponse getBookPerson(String refBookPerson);

    PageableResponse<BookPersonResponse> getAllBookPerson(Pageable pageable);

    BookPersonResponse updateBookPerson(String refBookPerson, BookPersonRequest bookPersonRequest);


    CooperativeAccountBookResponse createCooperativeAccountBook(CooperativeAccountBookRequest request);

    List<CooperativeAccountBookResponse> findAllCooperativeAccountBook();

    CooperativeAccountBookResponse getCooperativeAccountBook(String refCooperativeAccountBook);

    CooperativeAccountBookResponse updateCooperativeAccountBookStatus(String refCooperativeAccountBook, StatusEnum request);

    void updateCooperativeAccountBook(String refCooperativeAccountBook, CooperativeAccountBookRequest request);

    PageableResponse<CooperativeAccountBookResponse> findPageableCooperativeAccountBook(Pageable pageable);

    CooperativeAccountBookResponse getCooperativeAccountBookByUser();

    PageableResponse<CooperativeAccountBookResponse> findAllCooperativeAccountBooksByUserNotDELETED(Pageable pageable);

    List<String> getComponentFromEnum();
}
