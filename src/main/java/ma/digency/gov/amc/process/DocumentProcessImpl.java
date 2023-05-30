package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.DocumentResponse;
import ma.digency.gov.amc.dto.DocumentTypeResponse;
import ma.digency.gov.amc.dto.siel.PublicationResponse;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.DocumentMapper;
import ma.digency.gov.amc.repository.entity.Document;
import ma.digency.gov.amc.repository.entity.siel.*;
import ma.digency.gov.amc.service.shared.ConvertNumber;
import ma.digency.gov.amc.service.shared.DocumentService;
import ma.digency.gov.amc.service.siel.EditionService;
import ma.digency.gov.amc.service.siel.ExhibitorService;
import ma.digency.gov.amc.service.siel.SielDocumentResource;
import ma.digency.gov.amc.service.siel.SielParticipationDocument;
import ma.digency.gov.amc.utils.AmcUtilis;
import ma.digency.gov.amc.utils.PatchUtils;
import ma.digency.gov.amc.utils.enumeration.CountryEnum;
import ma.digency.gov.amc.utils.enumeration.DocumentColumnEnum;
import ma.digency.gov.amc.utils.enumeration.Product;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentProcessImpl implements DocumentProcess {

    private static final DateTimeFormatter DTF_FR = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter DTF_AR = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String DOCUMENT_NAME_FR = "Demande de participation.docx";

    //private static final String DOCUMENT_NAME_AR = "طلب مشاركة والتزام";

    private static final String DOCUMENT_NAME_PURCHASE_FR = "Bon de Commande.docx";

    private static final String AUTHOR = "المؤلف - Auteur";

    private static final String TITLE = "عنوان الكتاب - Titre livre";

    private static final String PUBLISHER = "الناشر - Editeur";

    private static final String PUBLISHING_YEAR = " سنة النشر- Année de publication";

    private static final String COPIES_NB = "عدد النسخ - Nombre d'exemplaires";

    private static final String AMOUT = "السعر بالدرهم - Prix en dirhams";

    private static final String LEGAL_DEPOSIT = "Dépôt légal - رقم الايداع القانوني";

    private static final String SPECIALITY = "تصنيف ديوي  -  Spécialité selon Dewey";

    private static final String ISBN = "الترقيم الدولي -  ISBN";

    //private static final String COLIS = "عدد الطرود - Nombre de Colis";

    private final DocumentService documentService;

    private final ExhibitorService exhibitorService;

    private final ReferenceSequenceService referenceSequenceService;

    private final DocumentMapper documentMapper;

    private final EditionService editionService;

    @Override
    public DocumentTypeResponse saveDocument(String uploadType, MultipartFile multipartFile, String refObject,String refParent) {

        Document document = new Document();
        try {
            document.setRefDocument(referenceSequenceService.generateRefDocument());
            document.setRefObject(refObject);
            if(refParent==null)
                refParent=refObject;
            document.setRefParent(refParent);
            document.setName(multipartFile.getOriginalFilename());
            document.setType(multipartFile.getContentType());
            document.setData(multipartFile.getBytes());
            document.setNature(uploadType);
            return documentMapper.documentToDocumentTypeResponse(documentService.uploadDocument(document));
        } catch (IOException e) {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }


    }

    @Override
    public DocumentResponse visualizeDocument(String refDocument) {
        Document document = documentService.findByRefDocument(refDocument);
        return documentMapper.documentToDocumentResponse(document);
    }

    @Override
    public List<DocumentTypeResponse> findDocuments(String refObject) {
        List<Document> documents = documentService.findByRefObject(refObject);
        return documentMapper.documentsToDocumentsType(documents);
    }

    @Override
    public List<DocumentTypeResponse> findDocumentsByRefObjectAndRefParent(String refObject, String refParent) {
        List<Document> documents = documentService.findByRefObjectAndRefParent(refObject,refParent);
        return documentMapper.documentsToDocumentsType(documents);
    }

    @Override
    public DocumentResponse generateRequestParticipation(String refExhibitor, String language) throws IOException {

        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        Edition edition = editionService.findOpenEditions(StatusEnum.OPEN);
        SielParticipationDocument documentToGenerate = buildDocumentToGenerate(exhibitor,edition, language);
        return DocumentResponse.builder()
                .data(documentService.
                        generateDocument(documentToGenerate, false))
                .name(DOCUMENT_NAME_FR)
                .type("application/pdf")
                .build();

    }

    @Override
    public DocumentResponse generatePurchaseOrder(String refExhibitor, String language) throws IOException {
        Exhibitor exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        Edition edition = editionService.findOpenEditions(StatusEnum.OPEN);
        SielParticipationDocument documentToGenerate = buildDocumentToGenerate(exhibitor,edition, language);
        return DocumentResponse.builder()
                .data(documentService.
                        generateDocument(documentToGenerate, true))
                .name(DOCUMENT_NAME_PURCHASE_FR)
                .type("application/pdf")
                .build();

    }

    @Override
    public HashMap<Integer, String> addExhibitorsFromDocument(String refExhibitor, MultipartFile multipartFile) {

        var exhibitor = exhibitorService.findExhibitorByRefExhibitor(refExhibitor);
        Workbook workbook = null;
        HashMap<Integer, String> warnnings = new HashMap<>();
        List<Publication> publications = new ArrayList<>();
        try {
            workbook = new XSSFWorkbook(multipartFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            sheet.getHeader();
            for (Row row : sheet) {
                if(IterableUtils.size(row)<=1){
                    break;
                }
                if (row.getRowNum() != 0) {
                    determinePublication(row, warnnings, publications);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
        List<Publication> newPublication = PatchUtils.getObjectToBeAdd(exhibitor.getPublications(), publications);
        if (!CollectionUtils.isEmpty(newPublication)) {
            preparePublicationToBeAdd(exhibitor,publications,editionService.findOpenEditions(StatusEnum.OPEN));
        }
        return warnnings;
    }

    @Override
    public String updateDocument(MultipartFile multipartFile, String refDocument) {
        Document document = documentService.findByRefDocument(refDocument);
        try {
            document.setName(multipartFile.getOriginalFilename());
            document.setType(multipartFile.getContentType());
            document.setData(multipartFile.getBytes());
            documentService.uploadDocument(document);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
        return document.getName();
    }

    @Override
    public DocumentResponse uploadPublicationModel() {
        DocumentResponse response = new DocumentResponse();
        try {
            String documentClassPath = SielDocumentResource.SIEL_PUBLICATION_MODULE.getClassPath();
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(documentClassPath);
            response.setData(inputStream.readAllBytes());
            response.setName("model importation des publication");
            response.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }catch (Exception e){
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
        return response;
    }

    @Override
    public Document findDocumentByRef(String refDcument) {
        return documentService.findByRefDocument(refDcument);
    }

    @Override
    public DocumentResponse uploadForeignExhibitor() {
        DocumentResponse response = new DocumentResponse();
        response.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setName("model importation des publication");
        try{
            String documentClassPath = SielDocumentResource.SIEL_FORIEGN_EXHIBITOR.getClassPath();
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(documentClassPath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Edition edition = editionService.findOpenEditions(StatusEnum.OPEN);
            List<Exhibitor>  foreignExhibitor = exhibitorService.findExhibitorsByEdition(edition.getRefEdition(),
                    Pageable.unpaged(),"Other").getContent();
            int i=1;
            for(Exhibitor ex : foreignExhibitor){
               Row row = sheet.createRow(i++);
                addDataToRow(row,ex);

            }

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);) {
                workbook.write(outputStream);
                response.setData(outputStream.toByteArray());
            }


        }catch (Exception e){
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
        return response;
    }

    protected void addDataToRow(Row row , Exhibitor exhibitor){
        //full Name
        Cell cell = row.createCell(0);
        cell.setCellValue(exhibitor.getPersonName());

        //Pays
        Cell cellPays = row .createCell(1);
        cellPays.setCellValue(exhibitor.getBirthCountry());

        //BirthDate
        Cell cellBirthDate = row.createCell(2);
        cellBirthDate.setCellValue(exhibitor.getBirthDay());

        //PassportNumber
        Cell cellPassport = row.createCell(3);
        cellPassport.setCellValue(exhibitor.getPassportNumber());

        //ExpirationDate
        Cell cellExpiration  = row.createCell(4);
        cellExpiration.setCellValue(exhibitor.getPassportExpiration());

        //TypePassport
        Cell cellPassportType = row.createCell(5);
        cellPassportType.setCellValue(exhibitor.getPassportNumber());

        //Nationality
        Cell cellNationality = row.createCell(6);
        cellNationality.setCellValue(exhibitor.getBirthNationality());

        //PayRisdence
       // Cell cellRisidence = row.createCell(7);
        //cellRisidence.setCellValue(exhibitor.getb);
    }


    @Override
    public List<Document> findDocumentByRefObject(String refObject) {
        return documentService.findByRefObject(refObject);
    }


    protected void determinePublication(Row rows, HashMap<Integer, String> warnnings, List<Publication> publications) {

        Publication publication = new Publication();
        List<String> errorFiled = new ArrayList<>();

        Double amout = getNumericFromRow(rows.getCell(DocumentColumnEnum.AMOUT.getCellIndex()));
        if (amout == null) {
            errorFiled.add(AMOUT);
        } else {
            publication.setAmout(amout);
        }

        String author = getStringFromRow(rows.getCell(DocumentColumnEnum.AUTHOR.getCellIndex()));
        if (!AmcUtilis.isValidAlphanumeric(author)) {
            errorFiled.add(AUTHOR);
        } else {
            publication.setAuthor(author);
        }
        String publisher = getStringFromRow(rows.getCell(DocumentColumnEnum.PUBLISHER.getCellIndex()));
        if (!AmcUtilis.isValidAlphanumeric(publisher)) {
            errorFiled.add(PUBLISHER);
        } else {
            publication.setPublisher(publisher);
        }
        Double copies = getNumericFromRow(rows.getCell(DocumentColumnEnum.COPIES_NB.getCellIndex()));
        if (copies == null) {
            errorFiled.add(COPIES_NB);
        } else {
            publication.setCopiesNbr(copies.longValue());
        }

        String isbn = getStringFromRow(rows.getCell(DocumentColumnEnum.ISBN.getCellIndex()));
        if (!AmcUtilis.isValidIsbn(isbn)) {
            errorFiled.add(ISBN);
        } else {
            publication.setIsbn(isbn);
        }


        String legalDeposit = getStringFromRow(rows.getCell(DocumentColumnEnum.LEGAL_DEPOSIT.getCellIndex()));
        if (!AmcUtilis.isValidAlphanumeric(legalDeposit+"")) {
            errorFiled.add(LEGAL_DEPOSIT);
        } else {
            publication.setLegalDeposit(legalDeposit);
        }
       /* Double colis = getNumericFromRow(rows.getCell(DocumentColumnEnum.COLIS.getCellIndex()));
        if(colis == null){
            errorFiled.add(COLIS);
        }else{
            publication.setColis(colis.intValue());
        }
        */
        publication.setColis(0);
        String title = getStringFromRow(rows.getCell(DocumentColumnEnum.TITLE.getCellIndex()));
        if (!AmcUtilis.isValidAlphanumeric(title)) {
            errorFiled.add(TITLE);
        }
        publication.setTitle(title);

        String speciality = getStringFromRow(rows.getCell(DocumentColumnEnum.SPECIALITY.getCellIndex()));
        if (!AmcUtilis.isValidAlphanumeric(speciality)) {
            errorFiled.add(SPECIALITY);

        } else {
            publication.setSpeciality(speciality);
        }

        Double datePublisher = getNumericFromRow(rows.getCell(DocumentColumnEnum.PUBLISHING_DATE.getCellIndex()));
        if (datePublisher == null) {
            errorFiled.add(PUBLISHING_YEAR);
        } else {
            publication.setPublishingDate(datePublisher.intValue());
        }

        if (CollectionUtils.isEmpty(errorFiled)) {
            publications.add(publication);
        } else {
            warnnings.put(rows.getRowNum(), errorFiled.stream().collect(Collectors.joining(",")));
        }

    }

    protected String getStringFromRow(Cell cell) {
        try {
            //todd check if not number
            return cell.getStringCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    protected Double getNumericFromRow(Cell cell) {
        try {
            return cell.getNumericCellValue();
        } catch (Exception e) {
            return null;
        }
    }


    private SielParticipationDocument buildDocumentToGenerate(Exhibitor exhibitor, Edition edition, String language) {
        DateTimeFormatter dtf = "fr".equalsIgnoreCase(language) ? DTF_FR : DTF_AR;
        String emptyCase = "□";
        String filCase = "■";
        var bookingStand = exhibitor.getBookingStands().stream()
                .filter(stand -> stand.getRefEdition().equals(edition.getRefEdition())).findFirst();

        SielParticipationDocument.SielParticipationDocumentBuilder builder = SielParticipationDocument.builder().address(exhibitor.getAddress())
                .date(LocalDate.now().format(dtf))
                .startDateEdition(edition.getStartedDate().format(dtf))
                .startDateEditionFr(edition.getStartedDate().format(DTF_FR))
                .endDateEdition(edition.getEndDate().format(dtf))
                .endDateEditionFr(edition.getEndDate().format(DTF_FR))
                .endDate(edition.getEndDate().format(dtf))
                .endDateFr(edition.getStartedDate().format(DTF_FR))
                .email(exhibitor.getEmail())
                .establishmentName(exhibitor.getPublishingHouseName())
                .representing(exhibitor.getCompanyRepresentative())
                .responsible(exhibitor.getResponsibleManagerName())
                .faxNumber(exhibitor.getFax())
                .language(language)
                .phoneNumber(exhibitor.getPhoneNumber())
                .productsExhibited(exhibitor.getPresentedMateriels().stream().map(ele-> Product.arabLabel(ele,language)).collect(Collectors.joining(",")))
                .rib(edition.getRib())
                .pays(CountryEnum.arabLabel(exhibitor.getCountry(),language));
        if (bookingStand.isPresent()) {
            builder.standEmpty(bookingStand.get().getCategory().equals("2") ? filCase : emptyCase)
                    .standEquipped(bookingStand.get().getCategory().equals("1") ? filCase : emptyCase)
                    .standSize(bookingStand.get().getVolumeInCubicMeter())
                    .virement(bookingStand.get().getPaymentMethod().equalsIgnoreCase("1") ? filCase : emptyCase)
                    .cheque(bookingStand.get().getPaymentMethod().equalsIgnoreCase("2") ? filCase : emptyCase)
                    .totalStand(String.valueOf(calculateTotalStand(bookingStand.get())))
                    .totalLetterAr(ConvertNumber.convertNumberToArabicWords(String.valueOf(calculateTotalStand(bookingStand.get()))))
                    .totalLetterFr(ConvertNumber.convertNumberToFrenchWords(String.valueOf(calculateTotalStand(bookingStand.get()))));

        } else {
            builder.standEmpty(exhibitor.getHallClass().equals("2") ? filCase : emptyCase)
                    .standEquipped(exhibitor.getHallClass().equals("1") ? filCase : emptyCase)
                    .standSize(exhibitor.getWingAreaSquare())
                    .virement(emptyCase)
                    .cheque(emptyCase)
                    .totalStand("")
                    .totalLetterFr("")
                    .totalLetterAr("");
        }

        return builder.build();
    }


    private int calculateTotalStand(BookingStand stand) {
        int volume = Integer.valueOf(stand.getVolumeInCubicMeter());
        if (stand.getCategory().equals("1")) {

            return volume * 804;
        }
        return volume * 522;
    }


    protected void preparePublicationToBeAdd(Exhibitor exhibitor, List<Publication> publicationsDto, Edition edition) {

        if (!CollectionUtils.isEmpty(publicationsDto)) {
            publicationsDto.forEach(publicationDto -> {
                String refPublication = referenceSequenceService.generateRefPublication();
                EditionPublication editionPublication = new EditionPublication(new EditionPublicationId(edition.getRefEdition(),refPublication),edition);
                publicationDto.setRefPublication(refPublication);
                publicationDto.setRefExhibitor(exhibitor.getRefExhibitor());
                publicationDto.setStatus(StatusEnum.PENDING);
                publicationDto.setEditionPublications(Arrays.asList(editionPublication) );
                exhibitor.getPublications().add(publicationDto);
            });

            exhibitorService.createOrUpdateExhibitor(exhibitor);
        }
    }

}

