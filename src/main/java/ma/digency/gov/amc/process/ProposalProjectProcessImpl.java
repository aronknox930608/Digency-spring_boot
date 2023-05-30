package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.proposalproject.*;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.ProposalProjectMapper;
import ma.digency.gov.amc.repository.entity.proposalproject.*;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.repository.entity.siel.Edition;
import ma.digency.gov.amc.service.AccountService;
import ma.digency.gov.amc.service.proposalproject.*;
import ma.digency.gov.amc.service.shared.NomenclatureService;
import ma.digency.gov.amc.service.siel.EditionService;
import ma.digency.gov.amc.utils.enumeration.DomainComponent;
import ma.digency.gov.amc.utils.enumeration.NotificationNature;
import ma.digency.gov.amc.utils.PatchUtils;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProposalProjectProcessImpl implements ProposalProjectProcess {

    public final ArtistAccountService artistAccountService;

    public final ProposalProjectMapper proposalProjectMapper;

    public final GeneralInformationService generalInformationService;

    public final ProjectDomainService projectDomainService;

    public final AccountService accountService;

    private final EditionService editionService;

    private final ReferenceSequenceService referenceSequenceService;

    public final ArtisticProfessionService artisticProfessionService;

    public final ArtisticProfessionDomainService artisticProfessionDomainService;

    public final ArtisticProfessionCategoryService artisticProfessionCategoryService;

    private final NotificationProcess notificationProcess;

    public final CooperativeAccountService cooperativeAccountService;
    public final ProposalProjectService proposalProjectService;

    public final NomenclatureService nomenclatureService;

    public final BookPersonAccountService bookPersonService;

    public final CooperativeAccountBookService cooperativeAccountBookService;


    @Override
    public ArtistAccountResponse createArtistAccount(ArtistAccountRequest artistAccountRequest) {
        artistAccountRequest.setStatus(StatusEnum.PENDING);
        ArtistAccount artistAccount = proposalProjectMapper.artistAccountDtoToArtistAccount(artistAccountRequest);
        artistAccount.setAccount(accountService.getAccountInRequest());
        artistAccount.setArtisticProfession(
                (artisticProfessionService.findArtisticProfessionByRef(artistAccountRequest.getRefArtisticProfession())).orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                        }));
        artistAccount.setGeneralInformation(createGenralInformation(artistAccountRequest.getGeneralInformation()));
        return proposalProjectMapper.artistAccountToArtistAccountDto(artistAccountService.createNewArtistAccount(artistAccount));
    }

    @Override
    public ArtistAccountResponse updateArtistAccount(ArtistAccountRequest artistAccountRequest,String ref) {
        Optional<ArtistAccount> artistAccountTmp = artistAccountService.findArtistAccountByRef(ref);
        if(artistAccountTmp.isEmpty())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        ArtistAccount artistAccount = artistAccountTmp.get();
        artistAccount.setArtisticProfession(
                (artisticProfessionService.findArtisticProfessionByRef(artistAccountRequest.getRefArtisticProfession())).orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                        }));
        proposalProjectMapper.updateArtistAccountDto(artistAccountRequest, artistAccount);
        return proposalProjectMapper.artistAccountToArtistAccountDto(artistAccountService.updateArtistAccount(artistAccount));
    }

    @Override
    public ArtistAccountResponse findArtistAccountByRef(String ref) {
        Optional<ArtistAccount> data = artistAccountService.findArtistAccountByRef(ref);
        return proposalProjectMapper.artistAccountToArtistAccountDto(data.orElseThrow(
                ()->{
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ARTIST_ACCOUNT_NOT_FOUND);
                }
        ));
    }

    @Override
    public ArtistAccountResponse findArtistAccountById(Long id) {
        Optional<ArtistAccount> data = artistAccountService.findArtistAccountById(id);
        return proposalProjectMapper.artistAccountToArtistAccountDto(data.orElseThrow(
                ()->{
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ARTIST_ACCOUNT_NOT_FOUND);
                }
        ));
    }

    @Override
    public List<ArtistAccountResponse> findAllArtistAccounts() {
        return artistAccountService.findAllArtistAccounts()
                .stream()
                .map(proposalProjectMapper::artistAccountToArtistAccountDto).collect(Collectors.toList());
    }

    @Override
    public PageableResponse<ArtistAccountResponse> findAllArtistAccountsByUserNotDELETED(Pageable pageable) {
        Page<ArtistAccountResponse> page = this.artistAccountService.findAllByAccountAndStatusNot(accountService.getAccountInRequest(),StatusEnum.DELETED,pageable)
                .map(this.proposalProjectMapper::artistAccountToArtistAccountDto);
        return new PageableResponse<>(page);
    }

    @Override
    @Transactional
    public void deleteArtistAccountById(Long id) {
        Optional<ArtistAccount> artistAccount = artistAccountService.findArtistAccountById(id);
        artistAccountService.updateArtistAccountStatus(artistAccount.orElseThrow(
                ()->
                {
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ARTIST_ACCOUNT_NOT_FOUND);
                }
        ), StatusEnum.DELETED);
    }

    @Override
    public void validateArtistAccountByRef(String ref) {
        Optional<ArtistAccount> artistAccount = artistAccountService.findArtistAccountByRef(ref);
        artistAccountService.updateArtistAccountStatus(artistAccount.orElseThrow(
                ()->
                {
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ARTIST_ACCOUNT_NOT_FOUND);
                }
        ), StatusEnum.VALID_SUBSCRIPTION);
    }

    @Override
    public void deleteArtistAccountByRef(String ref) {
        Optional<ArtistAccount> artistAccount = artistAccountService.findArtistAccountByRef(ref);
        artistAccountService.updateArtistAccountStatus(artistAccount.orElseThrow(
                ()->
                {
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ARTIST_ACCOUNT_NOT_FOUND);
                }
        ), StatusEnum.DELETED);
    }

    @Override
    public ArtistAccountResponse findArtistAccountByUser() {
        if(!isEditionOpen())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EDITION_FOUND);
        return proposalProjectMapper.artistAccountToArtistAccountDto(
                (this.artistAccountService.findArtistAccountByAccount(accountService.getAccountInRequest())).orElse(null));
    }

    @Override
    public PageableResponse<ArtistAccountResponse> findAllArtistAccountsPageable(Pageable pageable) {
        Page<ArtistAccountResponse> page = this.artistAccountService.findAllArtistAccountsPageable(pageable)
                .map(this.proposalProjectMapper::artistAccountToArtistAccountDto);
        return new PageableResponse<>(page);
    }


    public final CompanyAccountService companyAccountService;


    @Override
    public CompanyAccountResponse createCompanyAccount(CompanyAccountRequest request) {
        CompanyAccount companyAccount = proposalProjectMapper.companyAccountDtoToCompanyAccount(request);
        companyAccount.setAccount(accountService.getAccountInRequest());
        companyAccount.setArtisticProfession(
                (artisticProfessionService.findArtisticProfessionByRef(request.getRefArtisticProfession())).orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                        }));
        companyAccount.setGeneralInformation(createGenralInformation(request.getGeneralInformation()));
        return proposalProjectMapper.companyAccountToCompanyAccountDto(companyAccountService.createNewCompanyAccount(companyAccount));
    }

    @Override
    public List<CompanyAccountResponse> findAllCompanyAccount() {
        return companyAccountService.findAllCompanyAccounts()
                .stream()
                .map(proposalProjectMapper::companyAccountToCompanyAccountDto).collect(Collectors.toList());
    }

    @Override
    public CompanyAccountResponse getCompanyAccount(String refCompanyAccount) {
        Optional<CompanyAccount> data = companyAccountService.findCompanyAccountByRef(refCompanyAccount);
        return proposalProjectMapper.companyAccountToCompanyAccountDto(
                data.orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
                        }
                )
        );
    }

    @Override
    public void updateCompanyAccount(String refCompanyAccount,CompanyAccountRequest request) {
        Optional<CompanyAccount> updateCompanyAccount = companyAccountService.findCompanyAccountByRef(refCompanyAccount);
        if(updateCompanyAccount.isEmpty())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        CompanyAccount companyAccount = updateCompanyAccount.get();
        companyAccount.setArtisticProfession(
                (artisticProfessionService.findArtisticProfessionByRef(request.getRefArtisticProfession())).orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                        }));
        proposalProjectMapper.updateCompanyAccount(request, companyAccount);
        companyAccountService.updateCompanyAccount(companyAccount);
    }

    @Override
    public PageableResponse<CompanyAccountResponse> findPageableCompanyAccount(Pageable pageable) {
        Page<CompanyAccountResponse> page = this.companyAccountService.findPageableCompanyAccount(pageable)
                .map(this.proposalProjectMapper::companyAccountToCompanyAccountDto);
        return new PageableResponse<>(page);
    }

    @Override
    public CompanyAccountResponse updateCompanyAccountStatus(String refCompanyAccount, StatusEnum request) {
        Optional<CompanyAccount> updateCompanyAccount = companyAccountService.findCompanyAccountByRef(refCompanyAccount);
        if(updateCompanyAccount.isEmpty())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        CompanyAccount CompanyAccount = updateCompanyAccount.get();
        CompanyAccount.setStatus(request);
        companyAccountService.updateCompanyAccount(CompanyAccount);
        return proposalProjectMapper.companyAccountToCompanyAccountDto(CompanyAccount);
    }

    @Override
    public GeneralInformationResponse createGeneralInformation(GeneralInformationRequest request) {
        return proposalProjectMapper.generalInformationToGeneralInformationDto(createGenralInformation(request));
    }

    @Override
    public CompanyAccountResponse getCompanyAccountByUser() {
        if(!isEditionOpen())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EDITION_FOUND);
        return proposalProjectMapper.companyAccountToCompanyAccountDto(
                (companyAccountService.findCompanyAccountByAccount(accountService.getAccountInRequest())).orElse(null));
    }


    @Override
    public CooperativeAccountResponse getCooperativeAccountByUser() {
        if(!isEditionOpen())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EDITION_FOUND);
        return proposalProjectMapper.cooperativeAccountToCooperativeAccountDto(
                (cooperativeAccountService.findCooperativeAccountByAccount(accountService.getAccountInRequest())).orElse(null));
    }
    @Override
    public ArtisticProfessionResponse createArtisticProfession(ArtisticProfessionRequest request) {
        return proposalProjectMapper.artisticProfessionToArtisticProfessionDto(
                artisticProfessionService.createNewArtisticProfession(proposalProjectMapper.artisticProfessionDtoToArtisticProfession(request))
        );
    }

    @Override
    public List<ArtisticProfessionResponse> findAllArtisticProfession() {
        return artisticProfessionService.findAllArtisticProfessions()
                .stream()
                .map(proposalProjectMapper::artisticProfessionToArtisticProfessionDto).collect(Collectors.toList());
    }

    @Override
    public ArtisticProfessionResponse getArtisticProfession(String refArtisticProfession) {
        return proposalProjectMapper.artisticProfessionToArtisticProfessionDto(
                artisticProfessionService.findArtisticProfessionByRef(refArtisticProfession).orElse(null)
        );
    }

    @Override
    public ArtisticProfessionResponse updateArtisticProfession(String refArtisticProfession, ArtisticProfessionRequest request) {
        Optional<ArtisticProfession> old = this.artisticProfessionService.findArtisticProfessionByRef(refArtisticProfession);
        ArtisticProfession artisticProfession = proposalProjectMapper.updateArtisticProfession(request,old.orElseThrow(
                ()->{
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                }
        ));
        return proposalProjectMapper.artisticProfessionToArtisticProfessionDto(artisticProfessionService.updateArtisticProfession(artisticProfession));
    }

    @Override
    public List<ArtisticProfessionResponse> findAllArtisticProfessionsByDomain(String refArtisticProfessionDomain) {
        return artisticProfessionService.findAllArtisticProfessionsByDomain(refArtisticProfessionDomain)
                .stream()
                .map(proposalProjectMapper::artisticProfessionToArtisticProfessionDto).collect(Collectors.toList());
    }


    @Override
    public ArtisticProfessionDomainResponse createArtisticProfessionDomain(ArtisticProfessionDomainRequest request) {
        return proposalProjectMapper.artisticProfessionDomainToArtisticProfessionDomainDto(
                artisticProfessionDomainService.createNewArtisticProfessionDomain(proposalProjectMapper.artisticProfessionDomainDtoToArtisticProfessionDomain(request))
        );
    }

    @Override
    public List<ArtisticProfessionDomainResponse> findAllArtisticProfessionDomain() {
        return artisticProfessionDomainService.findAllArtisticProfessionDomains()
                .stream()
                .map(proposalProjectMapper::artisticProfessionDomainToArtisticProfessionDomainDto).collect(Collectors.toList());
    }

    @Override
    public ArtisticProfessionDomainResponse updateArtisticProfessionDomain(String refArtisticProfessionDomain, ArtisticProfessionDomainRequest request) {
        Optional<ArtisticProfessionDomain> old = this.artisticProfessionDomainService.findArtisticProfessionDomainByRef(refArtisticProfessionDomain);
        ArtisticProfessionDomain artisticProfessionDomain = proposalProjectMapper.updateArtisticProfessionDomain(request,old.orElseThrow(
                ()->{
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                }
        ));
        return proposalProjectMapper.artisticProfessionDomainToArtisticProfessionDomainDto(artisticProfessionDomainService.updateArtisticProfessionDomain(artisticProfessionDomain));
    }

    @Override
    public Boolean deleteArtisticProfession(String refArtisticProfession) {
         artisticProfessionService.deleteArtisticProfession(refArtisticProfession);
         return true;
    }

    @Override
    public Boolean deleteArtisticProfessionDomain(String refArtisticProfessionDomain) {
         artisticProfessionDomainService.deleteArtisticProfessionDomain(refArtisticProfessionDomain);
        return true;
    }
    @Override
    public Boolean deleteArtisticProfessionCategory(String refArtisticProfessionCategory) {
         artisticProfessionCategoryService.deleteArtisticProfessionCategory(refArtisticProfessionCategory);
         return true;
    }

    @Override
    public Boolean validateDemand(String ref, String Type) {
        Account account =null;
        switch (Type){
            case("COMPANY_ACCOUNT"):
                CompanyAccount ca = (companyAccountService.findCompanyAccountByRef(ref)).orElseThrow(()->{throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);});
                ca.setStatus(StatusEnum.VALID_SUBSCRIPTION);
                companyAccountService.updateCompanyAccount(ca);
                account = ca.getAccount();
                break;
            case("ARTIST_ACCOUNT"):
                ArtistAccount ar = (artistAccountService.findArtistAccountByRef(ref)).orElseThrow(()->{throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);});
                ar.setStatus(StatusEnum.VALID_SUBSCRIPTION);
                artistAccountService.updateArtistAccount(ar);
                //account = ar.getAccount();
                break;
            case("COOPERATIVE_ACCOUNT"):
                CooperativeAccount co = (cooperativeAccountService.findCooperativeAccountByRef(ref)).orElseThrow(()->{throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);});
                co.setStatus(StatusEnum.VALID_SUBSCRIPTION);
                cooperativeAccountService.updateCooperativeAccount(co);
                account = co.getAccount();
                break;
        }
        try{
            notificationProcess.sendEmail(prepareParameters(account), NotificationNature.CONFIRM_PROPOSAL_PROJECT);}
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public ProjectDomainResponse addOrUpdateSubDomain(String refDomain, List<ProjectSubDomainResponse> subDomains) {
        var domainRepo = projectDomainService.findProjectDomain(refDomain);
        var subsDomainRepo = domainRepo.getSubDomains();
        var subsDomain = proposalProjectMapper.projectDomainsResponseToProjectDomains(subDomains);

        var subDomainsAdd = PatchUtils.getObjectToBeAdd(subsDomainRepo, subsDomain);
        prepareSubDomainToBeAdd(domainRepo, subDomainsAdd);

        var subDomainsUpdate = PatchUtils.getObjectToBeUpdated(subsDomainRepo, subsDomain);
        prepareSubDomainToBeUpdate(domainRepo, subDomainsUpdate);

        var subDomainDelete = PatchUtils.getObjectToBeDeleted(subsDomainRepo,subsDomain);
        prepareSubDomainToBeDelete(domainRepo,subDomainDelete);

        projectDomainService.addOrUpdateProjectDomain(domainRepo);

        return proposalProjectMapper.projectDomainToProjectDomainResponse(domainRepo);
    }

    protected void prepareSubDomainToBeAdd(ProjectDomain domain, List<ProjectSubDomain> subDomains){
        if (!CollectionUtils.isEmpty(subDomains)) {
            subDomains.forEach(subDomain -> {
                subDomain.setRefSubDomain(referenceSequenceService.generateRefProjectSubDomain());
                subDomain.setRefDomain(domain.getRefDomain());
                domain.getSubDomains().add(subDomain);
            });
        }
    }
    protected void prepareSubDomainToBeUpdate(ProjectDomain domain, List<ProjectSubDomain> subDomains){
        if (!CollectionUtils.isEmpty(subDomains) && !CollectionUtils.isEmpty(domain.getSubDomains())) {
            Map<String, ProjectSubDomain> subDomainMap = domain.getSubDomains().stream().collect(
                    Collectors.toMap(ProjectSubDomain::getRefSubDomain, sub -> sub));
            subDomains.forEach(subDto -> {
                if (subDomainMap.get(subDto.getRefSubDomain()) != null) {
                    proposalProjectMapper.updateProjectSubDomain(proposalProjectMapper.projectSubDomainToProjectSubDomainResponse(subDto),
                            subDomainMap.get(subDto.getRefSubDomain()));
                }
            });

        }
    }
    protected void prepareSubDomainToBeDelete(ProjectDomain domain, List<ProjectSubDomain> subDomains) {
        if (!CollectionUtils.isEmpty(subDomains) && !CollectionUtils.isEmpty(domain.getSubDomains())) {
            domain.getSubDomains().removeAll(subDomains);
        }
    }

    @Override
    public List<ProjectDomainResponse> getAllProjectDomain() {
        return proposalProjectMapper.projectDomainsToProjectDomainsResponse(projectDomainService.findAll());
    }

    @Override
    public PageableResponse<ProjectDomainResponse> getAllProjectDomainPageable(Pageable pageable) {
        Page<ProjectDomainResponse> page = this.projectDomainService.findAllPage(pageable)
                .map(this.proposalProjectMapper::projectDomainToProjectDomainResponse);
        return new PageableResponse<>(page);
    }

    @Override
    public void deleteProjectDomain(String refDomain) {
        projectDomainService.deleteProjectDomain(refDomain);
    }

    @Override
    public ProjectDomainResponse updateProjectDomain(String refDomain, ProjectDomainRequest domain) {
        var projectDomain = proposalProjectMapper.updateProjectDomain(domain ,projectDomainService.findProjectDomain(refDomain));
        projectDomainService.addOrUpdateProjectDomain(projectDomain);
        return proposalProjectMapper.projectDomainToProjectDomainResponse(projectDomain);
    }

    @Override
    public ProjectDomainResponse getProjectDomain(String refDomain) {
        return proposalProjectMapper.projectDomainToProjectDomainResponse(projectDomainService.findProjectDomain(refDomain));
    }

    @Override
    public ProjectDomainResponse addProjectDomain(ProjectDomainRequest domain) {
        var projectDomain = proposalProjectMapper.projectDomainRequestToProjectDomain(domain);
        projectDomain.setRefDomain(referenceSequenceService.generateRefProjectDomain());
        projectDomainService.addOrUpdateProjectDomain(projectDomain);
        return proposalProjectMapper.projectDomainToProjectDomainResponse(projectDomain);
    }

    @Override
    public ProposalProjectResponse getProposalProject(String refProposalProject) {
        var project = proposalProjectService.findByRefProposalProject(refProposalProject);
        var out = proposalProjectMapper.proposalProjectToProposalProjectResponse(project);
        out.setDomain(project.getSubDomain().getRefDomain());
        return out;
    }

    @Override
    public ProposalProjectResponse updateProposalProject(String refProposalProject, ProposalProjectRequest request) {
        var project = proposalProjectService.findByRefProposalProject(refProposalProject);
        proposalProjectMapper.updateProposalPoject(request,project);
        if(request.getRefSubDomain()!=null){
            var subDomain = projectDomainService.findProjectSubDomain(request.getRefSubDomain());
            project.setSubDomain(subDomain);
        }
        proposalProjectService.createOrUpdate(project);

        return proposalProjectMapper.proposalProjectToProposalProjectResponse(project);
    }

    @Override
    public List<ProposalProjectResponse> getAllProposalProject() {
        var projects = proposalProjectService.findAllProposalProject();
        return proposalProjectMapper.proposalProjectsToProposalProjectResponse(projects);
    }

    @Override
    public PageableResponse<ProposalProjectResponse> getAllProposalProjectPageable(Pageable pageable) {
        Page<ProposalProjectResponse> page = this.proposalProjectService.findAllProposalProjectPageable(pageable)
                .map(this.proposalProjectMapper::proposalProjectToProposalProjectResponse);
        return new PageableResponse<>(page);
    }

    @Override
    public ProposalProjectResponse createProposalProject(ProposalProjectRequest request) {
        var project = proposalProjectMapper.proposalProjectRequestToProposalProject(request);
        String refProject = referenceSequenceService.generateRefProject();
        if(request.getRefSubDomain()!=null){
            var subDomain = projectDomainService.findProjectSubDomain(request.getRefSubDomain());
            project.setSubDomain(subDomain);
        }
        if(!CollectionUtils.isEmpty(request.getRefDocumentType())){
            List<ProposalProjectDocument> values = new ArrayList<>();
            for (Long in : request.getRefDocumentType()){
                var ppD = new ProposalProjectDocument();
                ppD.setRefProject(refProject);
                ppD.setValue(nomenclatureService.findNomenclatureValue(in));
                values.add(ppD);
            }
            project.setDocuments(values);
        }

        project.setRefProject(refProject);
        return proposalProjectMapper.proposalProjectToProposalProjectResponse(proposalProjectService.createOrUpdate(project));
    }

    @Override
    public List<ProposalProjectResponse> createProposalProjects(List<ProposalProjectRequest> request) {
        List<ProposalProjectResponse> out = new ArrayList<>();
        for(ProposalProjectRequest req:request)
            out.add(this.createProposalProject(req));
        return out;
    }

    @Override
    public BookPersonResponse createBookPerson(BookPersonRequest request) {
        var bookPerson = proposalProjectMapper.bookPersonRequestToBookPerson(request);
        bookPerson.setRefBookPerson(referenceSequenceService.generateRefBookPerson());
        return proposalProjectMapper.bookPersonToBookPersonResponse(bookPersonService.saveOrUpdate(bookPerson));
    }

    @Override
    public BookPersonResponse getBookPerson(String refBookPerson) {
        return proposalProjectMapper.bookPersonToBookPersonResponse(bookPersonService.findByRefBookPerson(refBookPerson));
    }

    @Override
    public PageableResponse<BookPersonResponse> getAllBookPerson(Pageable pageable) {
        var pag = bookPersonService.findAllBookPerson(pageable).map(proposalProjectMapper::bookPersonToBookPersonResponse);
        return new PageableResponse<>(pag);
    }

    @Override
    public BookPersonResponse updateBookPerson(String refBookPerson, BookPersonRequest bookPersonRequest) {
        var book = bookPersonService.findByRefBookPerson(refBookPerson);
        proposalProjectMapper.updateBookPersonFromBookPersonRequest(bookPersonRequest, book);

        return proposalProjectMapper.bookPersonToBookPersonResponse(bookPersonService.saveOrUpdate(book));
    }

    @Override
    public CooperativeAccountBookResponse createCooperativeAccountBook(CooperativeAccountBookRequest request) {
        CooperativeAccountBook cooperativeAccountBook = proposalProjectMapper.cooperativeAccountBookDtoToCooperativeAccountBook(request);
        cooperativeAccountBook.setAccount(accountService.getAccountInRequest());
        cooperativeAccountBook.setArtisticProfession(
                (artisticProfessionService.findArtisticProfessionByRef(request.getRefArtisticProfession())).orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                        }));
        cooperativeAccountBook.setGeneralInformation(createGenralInformation(request.getGeneralInformation()));
        return proposalProjectMapper.cooperativeAccountBookToCooperativeAccountBookDto(cooperativeAccountBookService.createNewCooperativeAccountBook(cooperativeAccountBook));
    }

    @Override
    public List<CooperativeAccountBookResponse> findAllCooperativeAccountBook() {
        return cooperativeAccountBookService.findAllCooperativeAccountBooks()
                .stream()
                .map(proposalProjectMapper::cooperativeAccountBookToCooperativeAccountBookDto).collect(Collectors.toList());
    }

    @Override
    public CooperativeAccountBookResponse getCooperativeAccountBook(String refCooperativeAccountBook) {
        Optional<CooperativeAccountBook> data = cooperativeAccountBookService.findCooperativeAccountBookByRef(refCooperativeAccountBook);
        return proposalProjectMapper.cooperativeAccountBookToCooperativeAccountBookDto(
                data.orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
                        }
                )
        );
    }

    @Override
    public void updateCooperativeAccountBook(String refCooperativeAccountBook,CooperativeAccountBookRequest request) {
        Optional<CooperativeAccountBook> updateCooperativeAccountBook = cooperativeAccountBookService.findCooperativeAccountBookByRef(refCooperativeAccountBook);
        if(updateCooperativeAccountBook.isEmpty())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        CooperativeAccountBook cooperativeAccountBook = updateCooperativeAccountBook.get();
        cooperativeAccountBook.setArtisticProfession(
                (artisticProfessionService.findArtisticProfessionByRef(request.getRefArtisticProfession())).orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                        }));
        proposalProjectMapper.updateCooperativeAccountBook(request, cooperativeAccountBook);
        cooperativeAccountBookService.updateCooperativeAccountBook(cooperativeAccountBook);
    }

    @Override
    public PageableResponse<CooperativeAccountBookResponse> findPageableCooperativeAccountBook(Pageable pageable) {
        Page<CooperativeAccountBookResponse> page = this.cooperativeAccountBookService.findPageableCooperativeAccountBook(pageable)
                .map(this.proposalProjectMapper::cooperativeAccountBookToCooperativeAccountBookDto);
        return new PageableResponse<>(page);
    }

    //TODO
    @Override
    public CooperativeAccountBookResponse getCooperativeAccountBookByUser() {
        if(!isEditionOpen())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EDITION_FOUND);
        return proposalProjectMapper.cooperativeAccountBookToCooperativeAccountBookDto(
                (this.cooperativeAccountBookService.findCooperativeAccountBookByAccount(accountService.getAccountInRequest())).orElse(null));
    }

    @Override
    public CooperativeAccountBookResponse updateCooperativeAccountBookStatus(String refCooperativeAccountBook, StatusEnum request) {
        Optional<CooperativeAccountBook> updateCooperativeAccountBook = cooperativeAccountBookService.findCooperativeAccountBookByRef(refCooperativeAccountBook);
        if(updateCooperativeAccountBook.isEmpty())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        CooperativeAccountBook CooperativeAccountBook = updateCooperativeAccountBook.get();
        CooperativeAccountBook.setStatus(request);
        cooperativeAccountBookService.updateCooperativeAccountBook(CooperativeAccountBook);
        return proposalProjectMapper.cooperativeAccountBookToCooperativeAccountBookDto(CooperativeAccountBook);
    }

    @Override
    public PageableResponse<CooperativeAccountBookResponse> findAllCooperativeAccountBooksByUserNotDELETED(Pageable pageable) {
        Page<CooperativeAccountBookResponse> page = this.cooperativeAccountBookService.findAllByAccountAndStatusNot(accountService.getAccountInRequest(),StatusEnum.DELETED,pageable)
                .map(this.proposalProjectMapper::cooperativeAccountBookToCooperativeAccountBookDto);
        return new PageableResponse<>(page);
    }

    @Override
    public List<String> getComponentFromEnum() {
        List<String> out = new ArrayList<>();
        for(DomainComponent type : DomainComponent.values())
            out.add(type.toString());
        return out;
    }


    @Override
    public List<ArtisticProfessionDomainResponse> findAllArtisticProfessionDomainsByCategory(String refArtisticProfessionCategory) {
        return artisticProfessionDomainService.findAllArtisticProfessionDomainsByCategory(refArtisticProfessionCategory)
                .stream()
                .map(proposalProjectMapper::artisticProfessionDomainToArtisticProfessionDomainDto).collect(Collectors.toList());
    }



    @Override
    public ArtisticProfessionCategoryResponse createArtisticProfessionCategory(ArtisticProfessionCategoryRequest request) {
        return proposalProjectMapper.artisticProfessionCategoryToArtisticProfessionCategoryDto(
                artisticProfessionCategoryService.createNewArtisticProfessionCategory(proposalProjectMapper.artisticProfessionCategoryDtoToArtisticProfessionCategory(request))
        );
    }

    @Override
    public List<ArtisticProfessionCategoryResponse> findAllArtisticProfessionCategory() {
        return artisticProfessionCategoryService.findAllArtisticProfessionCategory()
                .stream()
                .map(proposalProjectMapper::artisticProfessionCategoryToArtisticProfessionCategoryDto).collect(Collectors.toList());
    }

    @Override
    public ArtisticProfessionCategoryResponse getArtisticProfessionCategory(String refArtisticProfessionCategory) {
        return proposalProjectMapper.artisticProfessionCategoryToArtisticProfessionCategoryDto(
                artisticProfessionCategoryService.findArtisticProfessionCategoryByRef(refArtisticProfessionCategory).orElse(null)
        );
    }

    @Override
    public ArtisticProfessionCategoryResponse updateArtisticProfessionCategory(String refArtisticProfessionCategory, ArtisticProfessionCategoryRequest request) {
        Optional<ArtisticProfessionCategory> old = this.artisticProfessionCategoryService.findArtisticProfessionCategoryByRef(refArtisticProfessionCategory);
        ArtisticProfessionCategory ArtisticProfessionCategory = proposalProjectMapper.updateArtisticProfessionCategory(request,old.orElseThrow(
                ()->{
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                }
        ));
        return proposalProjectMapper.artisticProfessionCategoryToArtisticProfessionCategoryDto(artisticProfessionCategoryService.updateArtisticProfessionCategory(ArtisticProfessionCategory));
    }
    public GeneralInformation createGenralInformation(GeneralInformationRequest request){
        GeneralInformation generalInformation = proposalProjectMapper.generalInformationDtoToGeneralInformation(request);
        return generalInformationService.createNewGeneralInformation(generalInformation);
    }

    public Boolean isEditionOpen() {
        Optional<Edition> openEdition = editionService.findOpenEdition(StatusEnum.OPEN);
        if(openEdition.isEmpty())
            return false;
        LocalDate currentDate = LocalDate.now();
        return (currentDate.isEqual(openEdition.get().getStartedDate()) || currentDate.isAfter(openEdition.get().getStartedDate())) && currentDate.isBefore(openEdition.get().getEndDate());
    }
    protected HashMap<String, String> prepareParameters(Account auth) {
        HashMap<String, String> pars = new HashMap<>();
        if (auth != null) {
            pars.put("to", auth.getEmail());
            pars.put("#fullName", auth.getFirstname()+" "+auth.getLastname());
        }
        return pars;
    }



    @Override
    public CooperativeAccountResponse createCooperativeAccount(CooperativeAccountRequest request) {
        CooperativeAccount cooperativeAccount = proposalProjectMapper.cooperativeAccountDtoToCooperativeAccount(request);
        cooperativeAccount.setAccount(accountService.getAccountInRequest());
        cooperativeAccount.setArtisticProfession(
                (artisticProfessionService.findArtisticProfessionByRef(request.getRefArtisticProfession())).orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                        }));
        cooperativeAccount.setGeneralInformation(createGenralInformation(request.getGeneralInformation()));
        return proposalProjectMapper.cooperativeAccountToCooperativeAccountDto(cooperativeAccountService.createNewCooperativeAccount(cooperativeAccount));
    }

    @Override
    public List<CooperativeAccountResponse> findAllCooperativeAccount() {
        return cooperativeAccountService.findAllCooperativeAccounts()
                .stream()
                .map(proposalProjectMapper::cooperativeAccountToCooperativeAccountDto).collect(Collectors.toList());
    }

    @Override
    public CooperativeAccountResponse getCooperativeAccount(String refCooperativeAccount) {
        Optional<CooperativeAccount> data = cooperativeAccountService.findCooperativeAccountByRef(refCooperativeAccount);
        return proposalProjectMapper.cooperativeAccountToCooperativeAccountDto(
                data.orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
                        }
                )
        );
    }

    @Override
    public void updateCooperativeAccount(String refCooperativeAccount,CooperativeAccountRequest request) {
        Optional<CooperativeAccount> updateCooperativeAccount = cooperativeAccountService.findCooperativeAccountByRef(refCooperativeAccount);
        if(updateCooperativeAccount.isEmpty())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        CooperativeAccount cooperativeAccount = updateCooperativeAccount.get();
        cooperativeAccount.setArtisticProfession(
                (artisticProfessionService.findArtisticProfessionByRef(request.getRefArtisticProfession())).orElseThrow(
                        ()->{
                            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACTIVITY_NOT_FOUND);
                        }));
        proposalProjectMapper.updateCooperativeAccount(request, cooperativeAccount);
        cooperativeAccountService.updateCooperativeAccount(cooperativeAccount);
    }

    @Override
    public PageableResponse<CooperativeAccountResponse> findPageableCooperativeAccount(Pageable pageable) {
        Page<CooperativeAccountResponse> page = this.cooperativeAccountService.findPageableCooperativeAccount(pageable)
                .map(this.proposalProjectMapper::cooperativeAccountToCooperativeAccountDto);
        return new PageableResponse<>(page);
    }

    @Override
    public CooperativeAccountResponse updateCooperativeAccountStatus(String refCooperativeAccount, StatusEnum request) {
        Optional<CooperativeAccount> updateCooperativeAccount = cooperativeAccountService.findCooperativeAccountByRef(refCooperativeAccount);
        if(updateCooperativeAccount.isEmpty())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        CooperativeAccount CooperativeAccount = updateCooperativeAccount.get();
        CooperativeAccount.setStatus(request);
        cooperativeAccountService.updateCooperativeAccount(CooperativeAccount);
        return proposalProjectMapper.cooperativeAccountToCooperativeAccountDto(CooperativeAccount);
    }

    @Override
    public PageableResponse<CooperativeAccountResponse> findAllCooperativeAccountsByUserNotDELETED(Pageable pageable) {
        Page<CooperativeAccountResponse> page = this.cooperativeAccountService.findAllByAccountAndStatusNot(accountService.getAccountInRequest(),StatusEnum.DELETED,pageable)
                .map(this.proposalProjectMapper::cooperativeAccountToCooperativeAccountDto);
        return new PageableResponse<>(page);
    }

    @Override
    public PageableResponse<CompanyAccountResponse> findAllCompanyAccountsByUserNotDELETED(Pageable pageable) {
        Page<CompanyAccountResponse> page = this.companyAccountService.findAllByAccountAndStatusNot(accountService.getAccountInRequest(),StatusEnum.DELETED,pageable)
                .map(this.proposalProjectMapper::companyAccountToCompanyAccountDto);
        return new PageableResponse<>(page);
    }





}
