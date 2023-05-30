package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.NotificationResponse;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.shared.DocumentRequest;
import ma.digency.gov.amc.dto.siel.*;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.SielMapper;
import ma.digency.gov.amc.repository.entity.Document;
import ma.digency.gov.amc.repository.entity.Notification;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.repository.entity.siel.*;
import ma.digency.gov.amc.service.AccountService;
import ma.digency.gov.amc.service.shared.DocumentService;
import ma.digency.gov.amc.service.shared.NotificationService;
import ma.digency.gov.amc.service.siel.*;
import ma.digency.gov.amc.utils.PatchUtils;
import ma.digency.gov.amc.utils.SearchUtils;
import ma.digency.gov.amc.utils.enumeration.EntityNameEnum;
import ma.digency.gov.amc.utils.enumeration.NotificationNature;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SielProcessImpl implements SielProcess {

    private final PublicationService publicationService;

    private final AccountService accountService;

    private final SielMapper sielMapper;

    private final ExhibitorService exhibitorService;

    private final NotificationProcess notificationProcess;

    private final BookingSchoolService bookingSchoolService;

    private final NotificationService notificationService;

    private final BookingStandService bookingStandService;

    private final EditionService editionService;

    private final ActivityProposalService activityProposalService;

    private final PublisherRepresentedService publisherRepresentedService;

    private final ReferenceSequenceService referenceSequenceService;

    private final ForeignRepresentedService foreignRepresentedService;

    private final DocumentProcess documentProcess;

    private final DocumentService documentService;

    @Override
    public void deletePublication(String refExhibitor, String refPublication) {
        publicationService.deletePublication(refExhibitor, refPublication);
    }

    @Override
    public PublicationResponse updatePublication(String refExhibitor, String refPublication,
                                                 PublicationResponse response) {
        Publication publication = publicationService.findPublicationByRefPublication(refExhibitor, refPublication);
        Publication newPub = sielMapper.updatePublicationFromPublicationResponse(response, publication);

        return sielMapper.publicationToPublicationResponse(publicationService.savePublication(newPub));
    }


    @Override
    public PageableResponse<PublicationResponse> findAllPublication(String refExhibitor,String statusEnum, Pageable pageable) {
        StatusEnum status = StatusEnum.determineStatus(statusEnum);
        Page<PublicationResponse> page = publicationService.findPublicationsPageable(refExhibitor,status,pageable)
                .map(sielMapper::publicationToPublicationResponse);
        return new PageableResponse<>(page);

    }

    @Override
    public List<PublicationResponse> updateOrCreatePublications(String refExhibitor,
                                                                List<PublicationResponse> publicationsDto) {
        Edition openEdition = editionService.findOpenEditions(StatusEnum.OPEN);
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        List<PublicationResponse> repoPublication =
                sielMapper.publicationToPublicationResponse(exhibitor.getPublications());

        List<PublicationResponse> publicationAdd = PatchUtils.getObjectToBeAdd(repoPublication, publicationsDto);
        preparePublicationToBeAdd(exhibitor, publicationAdd,openEdition);

        List<PublicationResponse> publicationUpdate = PatchUtils.getObjectToBeUpdated(repoPublication, publicationsDto);
        preparePublicationToBeUpdated(exhibitor, publicationUpdate);

        Exhibitor updateExhibitor = exhibitorService.createOrUpdateExhibitor(exhibitor);
        return sielMapper.publicationToPublicationResponse(updateExhibitor.getPublications());
    }

    @Override
    public PublicationResponse getPublicationByRefPublication(String refExhibitor, String refPublication) {
        Publication publication = publicationService.findPublicationByRefPublication(refExhibitor, refPublication);
        return sielMapper.publicationToPublicationResponse(publication);
    }

    @Override
    public PublicationResponse createPublication(String refExhibitor, PublicationRequest response) {
        Edition edition = editionService.findOpenEditions(StatusEnum.OPEN);
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        Publication newPublication = sielMapper.publicationRequestToPublication(response);
        String refPublication = referenceSequenceService.generateRefPublication();
        newPublication.setRefPublication(refPublication);
        EditionPublication editionPublication = new EditionPublication(new EditionPublicationId(edition.getRefEdition(),refPublication),edition);

        newPublication.setRefExhibitor(exhibitor.getRefExhibitor());
        newPublication.setStatus(StatusEnum.PENDING);
        newPublication.setEditionPublications(Arrays.asList(editionPublication));
        exhibitor.getPublications().add(newPublication);
        exhibitorService.createOrUpdateExhibitor(exhibitor);
        return sielMapper.publicationToPublicationResponse(newPublication);
    }

    @Override
    public ExhibitorResponse createExhibitor(ExhibitorRequest exhibitorRequest) {
        var exhibitor = sielMapper.exhibitorRequestToExhibitor(exhibitorRequest);
        exhibitor.setStatus(StatusEnum.PENDING);
       // try{
       // notificationProcess.sendEmail(prepareParameters(exhibitor), NotificationNature.VALID_INSCRIPTION);}
        //catch(Exception e)
        //{
         //   System.out.println(e.getMessage());
        //}
        return sielMapper.exhibitorToExhibitorResponse(exhibitorService.createOrUpdateExhibitor(exhibitor));
    }

    @Override
    public ExhibitorResponse updateExhibitor(ExhibitorResponse request) {
        Exhibitor find = exhibitorService.findExhibitorByRefExhibitor(request.getRefExhibitor());
        sielMapper.updatexhibitorResponseToExhibitor(request, find);
        return sielMapper.exhibitorToExhibitorResponse(exhibitorService.createOrUpdateExhibitor(find));
    }

    @Override
    public ExhibitorResponse checkUpdateWithReturn(String email) {
        Optional<Account> account = accountService.findAccountByEmail(email);
        if(account.isEmpty() || account.get().getLogin()==null)
            return new ExhibitorResponse();
        return sielMapper.exhibitorToExhibitorResponse(this.exhibitorService.findExhibitorByRefExhibitor(account.get().getLogin()));
    }

    @Override
    public void deleteExhibitor(String refExhibitor) {
        exhibitorService.deleteExhibitor(refExhibitor);

    }

    @Override
    public BookingSchoolResponse getBookingSchoolByRefBookingSchool(String refBookingSchool) {
        var booking = bookingSchoolService.findBookingSchoolByRefBookingSchool(refBookingSchool);
        return sielMapper.bookingSchoolToBookingSchoolResponse(booking);
    }

    @Override
    public BookingSchoolResponse updateBookingSchool(String refBookingSchool, BookingSchoolRequest request) {
        var findBooking = bookingSchoolService.findBookingSchoolByRefBookingSchool(refBookingSchool);
        var updateBooking = sielMapper.updateBookingSchoolFromBookingSchoolRequest(request, findBooking);
        return sielMapper.bookingSchoolToBookingSchoolResponse(bookingSchoolService.saveBookingSchool(updateBooking));
    }

    @Override
    public BookingSchoolResponse createBookingSchool(BookingSchoolRequest request) {
        var edition = editionService.findOpenEditions(StatusEnum.OPEN);
        var booking = sielMapper.bookingSchoolRequestToBookingSchool(request);
        booking.setRefBookingSchool(referenceSequenceService.generateRefBookingSchool());
        booking.setStatus(StatusEnum.PENDING);
        booking.setRefEdition(edition.getRefEdition());
        return sielMapper.bookingSchoolToBookingSchoolResponse(bookingSchoolService.saveBookingSchool(booking));
    }

    @Override
    public void deleteBookingSchool(String refBookingRequest) {
        bookingSchoolService.deleteBookingSchool(refBookingRequest);
    }

    @Override
    public PageableResponse<BookingSchoolResponse> findAllBookingSchool(String statusEnum, Pageable pageable) {
        StatusEnum status = StatusEnum.determineStatus(statusEnum);
        var page = bookingSchoolService.findBookingSchoolPageable(status, pageable)
                .map(sielMapper::bookingSchoolToBookingSchoolResponse);
        return new PageableResponse<>(page);
    }

    @Override
    public void updateEntityStatus(String refEntity, String statusEnum, String refObject) {
        var entityName = EntityNameEnum.from(refEntity);
        StatusEnum status = StatusEnum.from(statusEnum);
        switch (entityName) {

            case EXHIBITOR:
                var ex = exhibitorService.updateStatus(refObject, status);
                notificationProcess.sendNotificationAndMail(prepareParameters(ex), NotificationNature.VALID_EXHIBITOR);
                break;

            case BOOKING_SCHOOL:
                bookingSchoolService.updateStatus(refObject, status);
                break;

            case PUBLICATION:
                publicationService.updateStatus(refObject, status);
                break;
            case BOOKING_STAND:
                exhibitorService.updateStand(refObject, status);
                break;

        }
    }


    @Override
    public ExhibitorResponse findExhibitor(String refExhibitor) {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        return sielMapper.exhibitorToExhibitorResponse(exhibitor);
    }
    /// to update
    @Override
    public PageableResponse<ExhibitorResponse> findAllExhibitors(String statusEnum , Pageable pageable) {
        Page<Exhibitor> page ;
        StatusEnum status = StatusEnum.determineStatus(statusEnum);
        page = exhibitorService.findExhibitorsPageable(status,pageable);

        var pageResponse = page.map(sielMapper::exhibitorToExhibitorResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public PageableResponse<ExhibitorResponse> findAllExhibitors(Pageable pageable) {
        var page = exhibitorService.findAllExhibitors(pageable)
                .map(sielMapper::exhibitorToExhibitorResponse);
        return new PageableResponse<>(page);
    }

    @Override
    public List<ActivityProposalResponse> updateOrCreateActivitiesProposal(String refExhibitor,
                                                                           List<ActivityProposalResponse> activities) {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        List<ActivityProposalResponse> repoActivities =
                sielMapper.activitiesToActivitiesResponse(exhibitor.getActivities());

        List<ActivityProposalResponse> activitiesAdd = PatchUtils.getObjectToBeAdd(repoActivities, activities);
        prepareActivitiesToToBeAdd(exhibitor, activitiesAdd);

        List<ActivityProposalResponse> activitiesUpdate = PatchUtils.getObjectToBeUpdated(repoActivities, activities);
        prepareActivitiesToBeUpdated(exhibitor, activitiesUpdate);

        Exhibitor updateExhibitor = exhibitorService.createOrUpdateExhibitor(exhibitor);
        return sielMapper.activitiesToActivitiesResponse(updateExhibitor.getActivities());
    }

    @Override
    public ActivityProposalResponse createActivityProposal(String refExhibitor, ActivityProposalRequest response) {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        ActivityProposal activityProposal = sielMapper.activityRequestToActivity(response);
        activityProposal.setRefActivityProposal(referenceSequenceService.generateRefActivityProposal());
        activityProposal.setRefExhibitor(exhibitor.getRefExhibitor());
        exhibitor.getActivities().add(activityProposal);
        exhibitorService.createOrUpdateExhibitor(exhibitor);
        return sielMapper.activityToActivityResponse(activityProposal);
    }

    @Override
    public void deleteActivityProposal(String refExhibitor, String refActivityProposal) {
        activityProposalService.deleteActivityProposal(refExhibitor, refActivityProposal);
    }

    @Override
    public ActivityProposalResponse getActivityProposal(String refExhibitor, String refActivityProposal) {
        return sielMapper.activityToActivityResponse(
                activityProposalService.findActivityProposalByRefActivityProposalAndRefExhibitor(refActivityProposal,
                        refExhibitor));
    }

    @Override
    public List<ActivityProposalResponse> getAllActivitiesProposal(String refExhibitor) {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        return sielMapper.activitiesToActivitiesResponse(exhibitor.getActivities());
    }

    @Override
    public List<PublisherRepresentedResponse> updateOrCreatePublisher(String refExhibitor,
                                                                      List<PublisherRepresentedResponse> request) {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        List<PublisherRepresentedResponse> repoPublishers =
                sielMapper.publishersToPublishersResponse(exhibitor.getPublishers());

        List<PublisherRepresentedResponse> publishersAdd = PatchUtils.getObjectToBeAdd(repoPublishers, request);
        preparePublisherToToBeAdd(exhibitor, publishersAdd);

        List<PublisherRepresentedResponse> publishersUpdate = PatchUtils.getObjectToBeUpdated(repoPublishers, request);
        preparePublisherToBeUpdated(exhibitor, publishersUpdate);

        Exhibitor updateExhibitor = exhibitorService.createOrUpdateExhibitor(exhibitor);
        return sielMapper.publishersToPublishersResponse(updateExhibitor.getPublishers());
    }

    @Override
    public List<PublisherRepresentedResponse> updateOrCreatePublisherAdapted(String refExhibitor, List<PublisherRepresentedRequest> request) {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        List<PublisherRepresentedRequest> repoPublishers =
                sielMapper.publishersToPublisherRequests(exhibitor.getPublishers());

        List<PublisherRepresentedRequest> publishersAdd = PatchUtils.getObjectToBeAdd(repoPublishers, request);
        preparePublisherToToBeAddAdapted(exhibitor, publishersAdd);

        List<PublisherRepresentedRequest> publishersUpdate = PatchUtils.getObjectToBeUpdated(repoPublishers, request);
        preparePublisherToBeUpdatedAdapted(exhibitor, publishersUpdate);

        Exhibitor updateExhibitor = exhibitorService.createOrUpdateExhibitor(exhibitor);
        return sielMapper.publishersToPublishersResponse(updateExhibitor.getPublishers());
    }

    Document getDocumentFromRequest(PublisherRepresentedRequest dto,String refExhibitor)
    {
        DocumentRequest documentRequest = dto.getPhotoScanned();
        Document document = new Document();
        if(documentRequest==null)
            return document;
        try {
            if(documentRequest.getRefDocument()==null)
                document.setRefDocument(referenceSequenceService.generateRefDocument());
            document.setRefObject(dto.getRefPublisherRepresented());
            if(refExhibitor==null)
                documentRequest.setRefParent(document.getRefObject());
            document.setRefParent(refExhibitor);
            document.setName(documentRequest.getFile().getOriginalFilename());
            document.setType(documentRequest.getFile().getContentType());
            document.setData(documentRequest.getFile().getBytes());
            document.setNature(documentRequest.getDocumentType());
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
    }

    @Override
    public PublisherRepresentedResponse createPublisherRepresented(String refExhibitor,
                                                                   PublisherRepresentedRequest request, MultipartFile multipartFile) {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        PublisherRepresented publisherRepresented = sielMapper.publisherRequestToPublisher(request);
        publisherRepresented.setRefPublisherRepresented(referenceSequenceService.generateRefPublisherRepresented());
        publisherRepresented.setRefExhibitor(exhibitor.getRefExhibitor());
        exhibitor.getPublishers().add(publisherRepresented);
        exhibitorService.createOrUpdateExhibitor(exhibitor);
        if(multipartFile!=null)
            documentProcess.saveDocument("PROCURATION",multipartFile,publisherRepresented.getRefPublisherRepresented(),refExhibitor);
        return sielMapper.publisherToPublisherResponse(publisherRepresented);
    }

    @Override
    public void deletePublisherRepresented(String refExhibitor, String refPublisherRepresented) {
        try{
            documentService.findByRefObject(refPublisherRepresented).forEach(document->{
                documentService.deleteDocument(document);
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        publisherRepresentedService.deletePublisherRepresented(refExhibitor, refPublisherRepresented);
    }

    @Override
    public PublisherRepresentedResponse getPublisherRepresented(String refExhibitor, String refPublisherRepresented) {
        return sielMapper.publisherToPublisherResponse(
                publisherRepresentedService.findPublisherRepresented(refExhibitor, refPublisherRepresented));
    }

    @Override
    public List<PublisherRepresentedResponse> getAllPublisherRepresented(String refExhibitor) {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);

        return sielMapper.publishersToPublishersResponse(exhibitor.getPublishers());
    }

    @Override
    public BookingStandResponse createBookingStand(String refExhibitor, BookingStandRequest request) {
        Edition edition = editionService.findOpenEditions(StatusEnum.OPEN);
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        exhibitor.getBookingStands().stream()
                .filter(stand -> stand.getRefEdition().equals(edition.getRefEdition())).findFirst()
                .ifPresent(elem -> {
                    throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_BOOKING_STAND_EXIST);
                });

        BookingStand bookingStand = sielMapper.bookingStandRequestToBookingStand(request);
        bookingStand.setRefBookingStand(referenceSequenceService.generateRefBookingStand());
        bookingStand.setStatus(StatusEnum.PENDING);
        bookingStand.setRefEdition(edition.getRefEdition());
        bookingStand.setRefExhibitor(exhibitor.getRefExhibitor());

        exhibitor.getBookingStands().add(bookingStand);
        exhibitorService.createOrUpdateExhibitor(exhibitor);
        return sielMapper.bookingStandToBookingStandResponse(bookingStand);
    }


    @Override
    public BookingStandResponse getBookingStand(String refExhibitor) {
        var edition = editionService.findOpenEditions(StatusEnum.OPEN);
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        var bookingStand = exhibitor.getBookingStands().stream()
                .filter(stand -> stand.getRefEdition().equals(edition.getRefEdition())).findFirst()
                .orElse(new BookingStand());

        return sielMapper.bookingStandToBookingStandResponse(bookingStand);
    }

    @Override
    public BookingStandResponse updateBookingStand(String refExhibitor, BookingStandResponse response,
                                                   String refBookingStand) {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        var edition = editionService.findOpenEditions(StatusEnum.OPEN);
        var bookingStand = exhibitor.getBookingStands().stream()
                .filter(stand -> stand.getRefEdition().equals(edition.getRefEdition())).findFirst()
                .orElseThrow(()-> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_BOOKING_STAND_EXIST));

        if (refBookingStand.equals(bookingStand.getRefBookingStand())) {
            sielMapper.updateBookingStandFromBookingStandResponse(response, bookingStand);
        }
        exhibitorService.createOrUpdateExhibitor(exhibitor);
        return sielMapper.bookingStandToBookingStandResponse(bookingStand);
    }

    @Override
    public NotificationResponse getAllNotification(String refParent) {
        NotificationResponse ntf = new NotificationResponse();
        ntf.setContent(notificationService.getAllNotificationByRefObject(refParent)
                .stream().map(Notification::getContent).collect(Collectors.toList()));
        return ntf;
    }

    @Override
    public EditionResponse createEdition(EditionRequest request) {
        if(request.getStartedDate().isAfter(request.getEndDate())){
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_INVALID_DATE_FOUND);
        }
        editionService.findOpenEdition(StatusEnum.OPEN).ifPresent(edition -> {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_OPEN_EDITION_ALREADY_EXIST);
        });

        Edition edition = sielMapper.editionRequestToEdition(request);
        edition.setRefEdition(referenceSequenceService.generateRefEdition());
        edition.setStatus(StatusEnum.OPEN);
        return sielMapper.editionToEditionResponse(editionService.saveEdition(edition));
    }

    @Override
    public List<EditionResponse> findALlEdition() {
        return editionService.findAllEditions().stream()
                .map(sielMapper::editionToEditionResponse).collect(Collectors.toList());
    }

    @Override
    public EditionResponse updateEdition(String refEdition, EditionRequest request) {
        Edition findEdition = editionService.findEditionByRef(refEdition);
        //check valid date
        if(request.getStartedDate().isAfter(request.getEndDate())){
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_INVALID_DATE_FOUND);
        }
        sielMapper.updateEditionFromEditionRequest(request, findEdition);
        return sielMapper.editionToEditionResponse(editionService.saveEdition(findEdition));
    }

    @Override
    public void deleteEdition(String refEdition) {
        editionService.deleteEdition(refEdition);
    }

    @Override
    public Boolean isOpenForSubscription() {
        Optional<Edition> openEdition = editionService.findOpenEdition(StatusEnum.OPEN);
        if(openEdition.isEmpty()){
            return false;
        }
        LocalDate currentDate = LocalDate.now();

        return (currentDate.isEqual(openEdition.get().getStartedDate()) || currentDate.isAfter(openEdition.get().getStartedDate())) && currentDate.isBefore(openEdition.get().getEndDate());
    }

    @Override
    public void validatedSubscription(String refParent) {
        exhibitorService.updateStatus(refParent,StatusEnum.VALID_SUBSCRIPTION);
    }

    @Override
    public PageableResponse<ExhibitorsFullData> getAllBookingStand(String statusEnum, Pageable pageable) {
        StatusEnum status = StatusEnum.determineStatus(statusEnum);
        Page<ExhibitorsFullData> page = bookingStandService.findBookingPageable(status,pageable)
                .map(this::bookingStandTOExhibitors);
        return new PageableResponse<>(page);
    }

    @Override
    public List<EditionGeneralInformationResponse> findAllExhibitorsPublication(String refExhibitor) {
        var exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        Set<String> refEditions = prepareRefEditionByExhibitors(exhibitor.getPublications());

        List<EditionGeneralInformationResponse> information = new ArrayList<>();

        for (String ref : refEditions ){
            EditionGeneralInformationResponse info = new EditionGeneralInformationResponse();
            var page = publicationService.findPublicationsByEditionAndExhibitor(ref, SearchUtils.createPageable(0,10), refExhibitor);
            info.setEdition(sielMapper.editionToEditionResponse(editionService.findEditionByRef(ref)));
            info.setPublications(new PageableResponse<>(page.map(sielMapper::publicationToPublicationResponse)));
            info.setBookingStand(sielMapper.bookingStandToBookingStandResponse(bookingStandService.findBookingByEditionAndExhibitor(ref,refExhibitor)));
            information.add(info);
        }

        return information;
    }

    @Override
    public EditionGeneralInformationResponse findPublicationOfExhibitorByEdition(String refEdition, Pageable pageable, String reExhibitor) {
        var page = publicationService.findPublicationsByEditionAndExhibitor(refEdition, pageable, reExhibitor);
        EditionGeneralInformationResponse info = new EditionGeneralInformationResponse();
        info.setBookingStand(sielMapper.bookingStandToBookingStandResponse(bookingStandService.findBookingByEditionAndExhibitor(refEdition,reExhibitor)));
        info.setEdition(sielMapper.editionToEditionResponse(editionService.findEditionByRef(refEdition)));
        info.setPublications(new PageableResponse<>(page.map(sielMapper::publicationToPublicationResponse)));

        return info;
    }

    @Override
    public ExhibitorGeneralInformationResponse findAllExhibitorsByEdition(String refEdition, Pageable pageable, String country) {

        Page<Exhibitor> page= exhibitorService.findExhibitorsByEdition(refEdition, pageable,null);

        ExhibitorGeneralInformationResponse info = new ExhibitorGeneralInformationResponse();
        info.setEdition(sielMapper.editionToEditionResponse(editionService.findEditionByRef(refEdition)));
        info.setExhibitors(new PageableResponse<>(page.map(sielMapper::exhibitorToExhibitorResponse)));
        return info;
    }

    @Override
    public EditionGeneralInformationResponse finPublicationByEdition(String refEdition, Pageable pageable) {
        var page = publicationService.findPublicationsByEdition(refEdition,pageable);
        EditionGeneralInformationResponse info = new EditionGeneralInformationResponse();
        info.setEdition(sielMapper.editionToEditionResponse(editionService.findEditionByRef(refEdition)));
        info.setPublications(new PageableResponse<>(page.map(sielMapper::publicationToPublicationResponse)));
        return info;
    }

    @Override
    public PageableResponse<ActivityProposalResponse> getAllActivitiesProposalPages(String refExhibitor, Pageable pageable) {
        var page = activityProposalService.findActivityProposalByRefExhibitor(refExhibitor,pageable);
        return new PageableResponse<>(page.map(sielMapper::activityToActivityResponse));
    }

    @Override
    public PageableResponse<PublisherRepresentedResponse> getAllPublisherRepresentedPage(String refExhibitor, Pageable pageable) {
        Page<PublisherRepresented> page = publisherRepresentedService.findPublishersRepresentedByRefExhibitor(refExhibitor,pageable);
        return  new PageableResponse<>(page.map(sielMapper::publisherToPublisherResponse));
    }

    @Override
    public PageableResponse<ExhibitorResponse> findAllExhibitorsToValidate(Pageable pageable) {
        Page<Exhibitor> page ;
        page = exhibitorService.findExhibitorsToValidatePageable(pageable);

        var pageResponse = page.map(sielMapper::exhibitorToExhibitorResponse);
        return new PageableResponse<>(pageResponse);
    }

    @Override
    public PublisherRepresentedResponse updatePublisherRepresented(String refExhibitor, String refPublisherRepresented, PublisherRepresentedRequest request, MultipartFile multipartFile) {
        var publisher = publisherRepresentedService.findPublisherRepresented(refExhibitor,refPublisherRepresented);
        sielMapper.updatePublisherFromPublisherRequest(request,publisher);

        return sielMapper.publisherToPublisherResponse(publisherRepresentedService.update(publisher));
    }

    @Override
    public List<ForeignRepresentedResponse> getAllForeignRepresentedPage(String refExhibitor) {
        Page<ForeignRepresented> page ;
         return foreignRepresentedService.findForeignRepresentedByRefExhibitor(refExhibitor).stream()
                .map(sielMapper::foreignToForeignResponse).collect(Collectors.toList());
    }

    @Override
    public ForeignRepresentedResponse getForeignRepresented(String refExhibitor, String refForeignRepresented) {
        return sielMapper.foreignToForeignResponse(
                foreignRepresentedService.findForeignRepresented(refExhibitor, refForeignRepresented));
    }

    @Override
    public void deleteForeignRepresented(String refExhibitor, String refForeignRepresented) {
        foreignRepresentedService.deleteForeignRepresented(refExhibitor, refForeignRepresented);

    }

    @Override
    public ForeignRepresentedResponse updateForeignRepresented(String refExhibitor, String refForeignRepresented, ForeignRepresentedRequest request) {
        var publisher = foreignRepresentedService.findForeignRepresented(refExhibitor,refForeignRepresented);
        sielMapper.updateForeignFromForeignRequest(request,publisher);

        return sielMapper.foreignToForeignResponse(foreignRepresentedService.update(publisher));
    }

    @Override
    public ForeignRepresentedResponse createForeignRepresented(String refExhibitor, ForeignRepresentedRequest request) {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        ForeignRepresented  foreignRepresented = sielMapper.foreignRequestToForeign(request);
        foreignRepresented.setRefForeignRepresented(referenceSequenceService.generateRefForeignRepresented());
        foreignRepresented.setRefExhibitor(exhibitor.getRefExhibitor());
//        foreignRepresentedService.saveForeignRepresented(foreignRepresented);
        exhibitor.getForeignPublishers().add(foreignRepresented);
        exhibitorService.createOrUpdateExhibitor(exhibitor);
        return sielMapper.foreignToForeignResponse(foreignRepresented);
    }

    protected Set<String> prepareRefEditionByExhibitors(List<Publication> publications){
        Set<String> refs = new HashSet<>();
        publications.forEach(publication -> {
            publication.getEditionPublications().forEach(editionPublication -> {
                if(!refs.contains(editionPublication.getEditionPublicationId().getRefEdition())){
                    refs.add(editionPublication.getEditionPublicationId().getRefEdition());
                }
            });
        });
        return refs;
    }

    protected void preparePublicationToBeAdd(Exhibitor exhibitor, List<PublicationResponse> publicationsDto,Edition edition) {

        if (!CollectionUtils.isEmpty(publicationsDto)) {
            publicationsDto.forEach(publicationDto -> {
                String refPublication = referenceSequenceService.generateRefPublication();
                EditionPublication editionPublication = new EditionPublication(new EditionPublicationId(edition.getRefEdition(),refPublication),edition);
                publicationDto.setRefPublication(refPublication);
                Publication publicationToAdd = sielMapper.publicationResponseToPublication(publicationDto);
                publicationToAdd.setRefExhibitor(exhibitor.getRefExhibitor());
                publicationToAdd.setStatus(StatusEnum.PENDING);
                publicationToAdd.setEditionPublications(Arrays.asList(editionPublication) );
                exhibitor.getPublications().add(publicationToAdd);
            });
        }
    }

    protected void preparePublicationToBeUpdated(Exhibitor exhibitor, List<PublicationResponse> publicationsDto) {
        if (!CollectionUtils.isEmpty(publicationsDto) && !CollectionUtils.isEmpty(exhibitor.getPublications())) {
            Map<String, Publication> publicationMap = exhibitor.getPublications().stream()
                    .collect(Collectors.toMap(Publication::getRefPublication, publication -> publication));
            publicationsDto.forEach(publicationDto -> {
                if (publicationMap.get(publicationDto.getRefPublication()) != null) {
                    sielMapper.updatePublicationFromPublicationResponse(publicationDto,
                            publicationMap.get(publicationDto.getRefPublication()));
                }
            });

        }
    }

    protected void prepareActivitiesToToBeAdd(Exhibitor exhibitor, List<ActivityProposalResponse> activitiesAdd) {
        if (!CollectionUtils.isEmpty(activitiesAdd)) {
            activitiesAdd.forEach(activityDto -> {
                activityDto.setRefActivityProposal(referenceSequenceService.generateRefActivityProposal());
                ActivityProposal activitiesToAdd = sielMapper.activityResponseToActivity(activityDto);
                activitiesToAdd.setRefExhibitor(exhibitor.getRefExhibitor());
                exhibitor.getActivities().add(activitiesToAdd);
            });
        }

    }

    protected void prepareActivitiesToBeUpdated(Exhibitor exhibitor, List<ActivityProposalResponse> activitiesAdd) {
        if (!CollectionUtils.isEmpty(activitiesAdd) && !CollectionUtils.isEmpty(exhibitor.getActivities())) {
            Map<String, ActivityProposal> activityMap = exhibitor.getActivities().stream()
                    .collect(Collectors.toMap(ActivityProposal::getRefActivityProposal, activity -> activity));
            activitiesAdd.forEach(activityDto -> {
                if (activityMap.get(activityDto.getRefActivityProposal()) != null) {
                    sielMapper.updateActivityFromActivityMapResponse(activityDto,
                            activityMap.get(activityDto.getRefActivityProposal()));
                }
            });

        }
    }

    protected void preparePublisherToBeUpdated(Exhibitor exhibitor, List<PublisherRepresentedResponse> publishersDto) {
        if (!CollectionUtils.isEmpty(publishersDto) && !CollectionUtils.isEmpty(exhibitor.getPublishers())) {
            Map<String, PublisherRepresented> publisherMap = exhibitor.getPublishers().stream().collect(
                    Collectors.toMap(PublisherRepresented::getRefPublisherRepresented, publisher -> publisher));
            publishersDto.forEach(publisherDto -> {
                if (publisherMap.get(publisherDto.getRefPublisherRepresented()) != null) {
                    sielMapper.updatePublisherFromPublisherResponse(publisherDto,
                            publisherMap.get(publisherDto.getRefPublisherRepresented()));
                }
            });

        }
    }

    protected void preparePublisherToBeUpdatedAdapted(Exhibitor exhibitor, List<PublisherRepresentedRequest> publishersDto) {
        if (!CollectionUtils.isEmpty(publishersDto) && !CollectionUtils.isEmpty(exhibitor.getPublishers())) {
            Map<String, PublisherRepresented> publisherMap = exhibitor.getPublishers().stream().collect(
                    Collectors.toMap(PublisherRepresented::getRefPublisherRepresented, publisher -> publisher));
            publishersDto.forEach(publisherDto -> {
                if (publisherMap.get(publisherDto.getRefPublisherRepresented()) != null) {
                    sielMapper.updatePublisherFromPublisherResponse(
                            sielMapper.publisherRequestToPublisherResponse(publisherDto),
                            publisherMap.get(publisherDto.getRefPublisherRepresented()));
                }
                if(publisherDto.getPhotoScanned()!=null)
                    documentService.uploadDocument(getDocumentFromRequest(publisherDto,exhibitor.getRefExhibitor()));
            });

        }
    }

    protected void preparePublisherToToBeAdd(Exhibitor exhibitor, List<PublisherRepresentedResponse> publishersDto) {
        if (!CollectionUtils.isEmpty(publishersDto)) {
            publishersDto.forEach(publisherDto -> {
                publisherDto.setRefPublisherRepresented(referenceSequenceService.generateRefPublisherRepresented());
                PublisherRepresented publisherToAdd = sielMapper.publisherResponseToPublisher(publisherDto);
                publisherToAdd.setRefExhibitor(exhibitor.getRefExhibitor());
                exhibitor.getPublishers().add(publisherToAdd);
            });
        }
    }

    protected void preparePublisherToToBeAddAdapted(Exhibitor exhibitor, List<PublisherRepresentedRequest> publishersDto) {
        if (!CollectionUtils.isEmpty(publishersDto)) {
            publishersDto.forEach(publisherDto -> {
                publisherDto.setRefPublisherRepresented(referenceSequenceService.generateRefPublisherRepresented());
                PublisherRepresented publisherToAdd = sielMapper.publisherRequestToPublisher(publisherDto);
                publisherToAdd.setRefExhibitor(exhibitor.getRefExhibitor());
                exhibitor.getPublishers().add(publisherToAdd);
                if(publisherDto.getPhotoScanned()!=null)
                    documentService.uploadDocument(getDocumentFromRequest(publisherDto,exhibitor.getRefExhibitor()));
            });
        }
    }

    protected HashMap<String,String> prepareParameters(Exhibitor exhibitor) {
        HashMap<String, String> pars = new HashMap<>();
        if (exhibitor != null) {
            pars.put("refObject", exhibitor.getRefExhibitor());
            pars.put("to", exhibitor.getEmail());
            pars.put("#fullName", exhibitor.getResponsibleManagerName());
            pars.put("#salutation", "Mr/Mme");
            pars.put("#nameExhibitor", exhibitor.getPublishingHouseName());
            pars.put("#status", exhibitor.getStatus().name());
        }

        return pars;
    }

    protected HashMap<String, String> prepareParameters(Publication pub) {
        HashMap<String, String> pars = new HashMap<>();
        if (pub != null) {
            Exhibitor find = exhibitorService.findExhibitorByRefExhibitor(pub.getRefExhibitor());
            pars.put("refObject", pub.getRefExhibitor());
            pars.put("to", find.getEmail());
            pars.put("#fullName", find.getResponsibleManagerName());
            pars.put("#salutation", "Mr/Mme");
            pars.put("#namePublication", pub.getTitle());
            pars.put("#status", pub.getStatus().name());

        }
        return pars;

    }


    ExhibitorsFullData bookingStandTOExhibitors(BookingStand bookingStand){
        ExhibitorsFullData full = new ExhibitorsFullData();
        if(bookingStand !=null){
            full.setBookingStand(sielMapper.bookingStandToBookingStandResponse(bookingStand));
            Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(bookingStand.getRefExhibitor());
            if(exhibitor !=null){
                full.setExhibitor(sielMapper.exhibitorToExhibitorResponse(exhibitor));
                full.setNbrActivitiesProposal(CollectionUtils.isEmpty(exhibitor.getActivities())? 0: exhibitor.getActivities().size());
                full.setNbrPublications(CollectionUtils.isEmpty(exhibitor.getPublications()) ? 0 : exhibitor.getPublications().size());
                full.setNbrPublishers(CollectionUtils.isEmpty(exhibitor.getPublishers())?0:exhibitor.getPublishers().size());
            }
        }
        return full;
    }

}
