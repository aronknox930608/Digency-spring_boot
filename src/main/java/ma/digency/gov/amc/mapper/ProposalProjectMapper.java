package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.proposalproject.*;
import ma.digency.gov.amc.repository.entity.proposalproject.*;

import java.util.List;


@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)

public interface ProposalProjectMapper {
    ArtistAccount artistAccountDtoToArtistAccount(ArtistAccountRequest artistAccountRequest);

    ArtistAccountResponse artistAccountToArtistAccountDto(ArtistAccount artistAccount);

    List<ArtistAccountResponse> artistAccountsToArtistAccountsDto(List<ArtistAccount> artistAccount);

    ArtistAccount updateArtistAccountDto(ArtistAccountRequest update,ArtistAccount artistAccount);

    CompanyAccount companyAccountDtoToCompanyAccount(CompanyAccountRequest companyAccountRequest);

    CompanyAccountResponse companyAccountToCompanyAccountDto(CompanyAccount companyAccount);

    List<CompanyAccountResponse> companyAccountsToCompanyAccountsDto(List<CompanyAccount> companyAccount);

    CompanyAccount updateCompanyAccount(CompanyAccountRequest update, CompanyAccount companyAccount);

    GeneralInformation updateGeneralInformation(GeneralInformationRequest update, GeneralInformation generalInformation);

    GeneralInformationResponse generalInformationToGeneralInformationDto(GeneralInformation generalInformation);

    GeneralInformation generalInformationDtoToGeneralInformation(GeneralInformationRequest generalInformationRequest);

    ArtisticProfessionCategory updateArtisticProfessionCategory(ArtisticProfessionCategoryRequest update, ArtisticProfessionCategory artisticProfessionCategory);

    ArtisticProfessionDomain updateArtisticProfessionDomain(ArtisticProfessionDomainRequest update, ArtisticProfessionDomain artisticProfession);

    ArtisticProfession updateArtisticProfession(ArtisticProfessionRequest update, ArtisticProfession artisticProfession);

    ArtisticProfessionResponse artisticProfessionToArtisticProfessionDto(ArtisticProfession artisticProfession);

    ArtisticProfessionDomainResponse artisticProfessionDomainToArtisticProfessionDomainDto(ArtisticProfessionDomain artisticProfessionDomain);

    ArtisticProfessionCategoryResponse artisticProfessionCategoryToArtisticProfessionCategoryDto(ArtisticProfessionCategory artisticProfessionCategory);

    ArtisticProfession artisticProfessionDtoToArtisticProfession(ArtisticProfessionRequest artisticProfessionRequest);

    ArtisticProfessionDomain artisticProfessionDomainDtoToArtisticProfessionDomain(ArtisticProfessionDomainRequest artisticProfessionDomainResponse);

    ArtisticProfessionCategory artisticProfessionCategoryDtoToArtisticProfessionCategory(ArtisticProfessionCategoryRequest artisticProfessionCategoryRequest);


    ProjectDomainResponse projectDomainToProjectDomainResponse(ProjectDomain all);

    ProjectDomain updateProjectDomain(ProjectDomainRequest domain, ProjectDomain projectDomain);

    List<ProjectDomainResponse> projectDomainsToProjectDomainsResponse(List<ProjectDomain> all);

    ProjectDomain projectDomainRequestToProjectDomain(ProjectDomainRequest domain);

    List<ProjectSubDomain> projectDomainsResponseToProjectDomains(List<ProjectSubDomainResponse> subDomains);

    ProjectSubDomain updateProjectSubDomain(ProjectSubDomainResponse subDto, ProjectSubDomain projectSubDomain);

    ProjectSubDomainResponse projectSubDomainToProjectSubDomainResponse(ProjectSubDomain subDto);

    ProposalProjectResponse proposalProjectToProposalProjectResponse(ProposalProject project);

    List<ProposalProjectResponse> proposalProjectsToProposalProjectResponse(List<ProposalProject> projects);

    ProposalProject updateProposalPoject(ProposalProjectRequest request, ProposalProject project);

    ProposalProject proposalProjectRequestToProposalProject(ProposalProjectRequest request);
    CooperativeAccount cooperativeAccountDtoToCooperativeAccount(CooperativeAccountRequest CooperativeAccountRequest);

    CooperativeAccountResponse cooperativeAccountToCooperativeAccountDto(CooperativeAccount CooperativeAccount);

    List<CooperativeAccountResponse> cooperativeAccountsToCooperativeAccountsDto(List<CooperativeAccount> CooperativeAccount);

    CooperativeAccount updateCooperativeAccount(CooperativeAccountRequest update, CooperativeAccount CooperativeAccount);


    BookPersonAccount bookPersonRequestToBookPerson(BookPersonRequest request);

    BookPersonResponse bookPersonToBookPersonResponse(BookPersonAccount bookPerson);

    BookPersonAccount updateBookPersonFromBookPersonRequest(BookPersonRequest bookPersonRequest, BookPersonAccount book);

    CooperativeAccountBook cooperativeAccountBookDtoToCooperativeAccountBook(CooperativeAccountBookRequest CooperativeAccountBookRequest);

    CooperativeAccountBookResponse cooperativeAccountBookToCooperativeAccountBookDto(CooperativeAccountBook CooperativeAccountBook);

    List<CooperativeAccountBookResponse> cooperativeAccountBooksToCooperativeAccountBooksDto(List<CooperativeAccountBook> CooperativeAccountBook);

    CooperativeAccountBook updateCooperativeAccountBook(CooperativeAccountBookRequest update, CooperativeAccountBook CooperativeAccountBook);
}
