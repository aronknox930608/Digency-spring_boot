package ma.digency.gov.amc.service.card;

import liquibase.pro.packaged.C;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.card.CardResponse;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountResponse;
import ma.digency.gov.amc.mapper.ArtistAccountMapper;
import ma.digency.gov.amc.mapper.CardMapper;
import ma.digency.gov.amc.repository.ArtistAccountRepository;
import ma.digency.gov.amc.repository.CardRepository;
import ma.digency.gov.amc.repository.entity.Address;
import ma.digency.gov.amc.repository.entity.BirthData;
import ma.digency.gov.amc.repository.entity.artistCard.Card;
import ma.digency.gov.amc.repository.entity.proposalproject.*;
import ma.digency.gov.amc.service.proposalproject.ArtisticProfessionCategoryService;
import ma.digency.gov.amc.service.proposalproject.ArtisticProfessionDomainService;
import ma.digency.gov.amc.service.proposalproject.ArtisticProfessionService;
import ma.digency.gov.amc.service.proposalproject.GeneralInformationService;
import ma.digency.gov.amc.utils.enumeration.IdentityType;
import ma.digency.gov.amc.utils.enumeration.MaritalStatus;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

@RequiredArgsConstructor
public class ExcelServiceImpl implements  ExcelService{
    private final GeneralInformationService generalInformationService;
    private final ArtisticProfessionService artisticProfessionService;
    private final ArtisticProfessionCategoryService artisticProfessionCategoryService;
    private final ArtisticProfessionDomainService artisticProfessionDomainService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ArtistAccountRepository artistAccountRepository;
    private final ReferenceSequenceService referenceSequenceService;
    private final ArtistAccountMapper artistAccountMapper;
    private final CardRepository cardRepository;


    String[] HeaderArtistAccount={"Ref_Artist_Account","CIN","FirstName","LastName","FirstNameAR","LastNameAR","ArtistName",
            "ArtistNameAR","Gender","IdentityType","identityNumber","IdentityProfType","artistSpeciality",
            "ArtistSpecialityAR","Email","PhoneNumber","OtherPhoneNumber","MaritalStatus","DependentChildren",
            "OtherJobName","SocialSecurityName","SocialSecurityID","ArtisticWorkStartDate","LastArtisticActivity",
            "TeamName","TeamCreationDate","StudyLevel","ArtisticEstablishmentName","RibNumber","DomainName","Status",
            "Nationality", "BirthDate","BirthCountry","BirthCity"};

    String[] HeaderCards={"refCard","numCard","dateCardCreation","dateCardValidation","cardType",
            "refArtistAccount", "status"};


    @Override
    public void save(MultipartFile file) {
        try {
            List<ArtistAccount> artists = importExcelData(file.getInputStream());
            for(ArtistAccount artist:artists)
            {
                artist.setRefArtistAccount(referenceSequenceService.generateRefArtistAccount());
                artistAccountRepository.save(artist);
            }

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }

    }
    @Override
    public void saveCardExcelFile(MultipartFile file) {
        try {
            List<Card> cards = importArtistCardFile(file.getInputStream());
            for(Card c:cards)
            {
                c.setRefArtistAccount(referenceSequenceService.generateRefCard());
                cardRepository.save(c);
            }

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }

    }
    @Override
    public List<ArtistAccount> importExcelData(InputStream is) throws IOException {
        List<ArtistAccount> artistAccounts=new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Row row : sheet){
            if (row.getRowNum() != 0) {
                ArtistAccount artistAccount = new ArtistAccount();
                artistAccount.setCin(getCellValue(row,0));
                artistAccount.setFirstName(getCellValue(row,1));
                artistAccount.setLastName(getCellValue(row,2));
                artistAccount.setFirstNameAR(getCellValue(row,3));
                artistAccount.setLastNameAR(getCellValue(row,4));
                artistAccount.setArtistName(getCellValue(row,5));
                artistAccount.setArtistNameAR(getCellValue(row,6));
                artistAccount.setGender(getCellValue(row,7));
                String identityType=getCellValue(row,8);
                switch (identityType){
                    case "CIN_CARD":
                        artistAccount.setIdentityType(IdentityType.CIN_CARD);
                        break;
                    case "PASSPORT":
                        artistAccount.setIdentityType(IdentityType.PASSPORT);
                        break;
                    case "DRIVER_LICENSE_CARD":
                        artistAccount.setIdentityType(IdentityType.DRIVER_LICENSE_CARD);
                        break;
                    case "RESIDENT_CARD":
                        artistAccount.setIdentityType(IdentityType.RESIDENT_CARD);
                        break;
                }
                artistAccount.setIdentityNumber(getCellValue(row,9));
                artistAccount.setIdentityProfType(getCellValue(row,10));
                artistAccount.setArtistSpeciality(getCellValue(row,11));
                artistAccount.setArtistSpecialityAR(getCellValue(row,12));
                artistAccount.setEmail(getCellValue(row,13));
                artistAccount.setPhoneNumber(getCellValue(row,14));
                artistAccount.setOtherPhoneNumber(getCellValue(row,15));
                String maritalStatus=getCellValue(row,16);
                switch (maritalStatus){
                    case "MARRIED":
                        artistAccount.setMaritalStatus(MaritalStatus.MARRIED);
                        break;
                    case "SINGLE":
                        artistAccount.setMaritalStatus(MaritalStatus.SINGLE);
                        break;
                    case "DIVORCED":
                        artistAccount.setMaritalStatus(MaritalStatus.DIVORCED);
                        break;
                }
                artistAccount.setDependentChildren(Integer.valueOf(getCellValue(row,17)));
                artistAccount.setOtherJobName(getCellValue(row,18));
                artistAccount.setSocialSecurityName(getCellValue(row,19));
                artistAccount.setSocialSecurityID(getCellValue(row,20));
                artistAccount.setArtisticWorkStartDate(LocalDate.parse(getCellValue(row,21),formatter));
                artistAccount.setLastArtisticActivity(getCellValue(row,22));
                artistAccount.setTeamName(getCellValue(row,23));
                artistAccount.setTeamCreationDate(LocalDate.parse(getCellValue(row,24),formatter));
                artistAccount.setStudyLevel(getCellValue(row,25));
                artistAccount.setArtisticEtablishmentName(getCellValue(row,26));
                BirthData birthData=new BirthData();
                birthData.setBirthDate(LocalDate.parse(getCellValue(row,27),formatter));
                birthData.setBirthCountry(getCellValue(row,28));
                birthData.setBirthCity(getCellValue(row,29));
                birthData.setNationality(getCellValue(row,30));
                artistAccount.setBirthdata(birthData);
                artistAccount.setRibNumber(getCellValue(row,31));
                artistAccount.setDomainName(getCellValue(row,32));
                String statusEnum=getCellValue(row,33);
                switch (statusEnum){
                    case "PENDING":
                        artistAccount.setStatus(StatusEnum.PENDING);
                        break;
                    case "REJECTED":
                        artistAccount.setStatus(StatusEnum.REJECTED);
                        break;
                    case "DELETED":
                        artistAccount.setStatus(StatusEnum.DELETED);
                        break;
                    case "OPEN":
                        artistAccount.setStatus(StatusEnum.OPEN);
                        break;
                    case "CLOSE":
                        artistAccount.setStatus(StatusEnum.CLOSE);
                        break;
                    case "ACCEPTED":
                        artistAccount.setStatus(StatusEnum.ACCEPTED);
                        break;
                    case "VALID_SUBSCRIPTION":
                        artistAccount.setStatus(StatusEnum.VALID_SUBSCRIPTION);
                        break;
                }
                Address address=new Address();
                address.setProvince(getCellValue(row,34));
                address.setPostalCode(getCellValue(row,35));
                address.setCity(getCellValue(row,36));
                address.setCountry(getCellValue(row,37));
                address.setAddress(getCellValue(row,38));
                address.setRegion(getCellValue(row,39));
                artistAccount.setAddress(address);
                GeneralInformation generalInformation=new GeneralInformation();
                generalInformation.setProjectName(getCellValue(row,40));
                generalInformation.setProjectTitle(getCellValue(row,41));
                generalInformation.setProjectType(getCellValue(row,42));
                generalInformation.setNumDancesOrSongs(Integer.parseInt(getCellValue(row,43)));
                generalInformation.setDurationTime(Float.parseFloat(getCellValue(row,44)));
                generalInformation.setProjectCost(Double.valueOf(getCellValue(row,45)));
                generalInformation.setProjectDescription(getCellValue(row,46));
                generalInformation.setAlbumTitle(getCellValue(row,47));
                generalInformation=generalInformationService.createNewGeneralInformation(generalInformation);
                artistAccount.setGeneralInformation(generalInformation);

                ArtisticProfession artisticProfession=new ArtisticProfession();
                artisticProfession.setRefArtisticProfession(referenceSequenceService.generateRefArtisticProfession());
                artisticProfession.setName(getCellValue(row,48));
                artisticProfession.setNameAr(getCellValue(row,49));

                ArtisticProfessionDomain artisticProfessionDomain=new ArtisticProfessionDomain();
                artisticProfessionDomain.setName(getCellValue(row,50));
                artisticProfessionDomain.setNameAr(getCellValue(row,51));


                ArtisticProfessionCategory artisticProfessionCategory=new ArtisticProfessionCategory();
                artisticProfessionCategory.setName(getCellValue(row,52));
                artisticProfessionCategory.setNameAr(getCellValue(row,53));
                artisticProfessionCategory=artisticProfessionCategoryService.createNewArtisticProfessionCategory(artisticProfessionCategory);

                artisticProfessionDomain.setRefArtisticProfessionCategory(artisticProfessionCategory.getRefArtisticProfessionCategory());
                artisticProfessionDomain=artisticProfessionDomainService.createNewArtisticProfessionDomain(artisticProfessionDomain);

                artisticProfession.setRefArtisticProfessionDomain(artisticProfessionDomain.getRefArtisticProfessionDomain());
                artisticProfession=artisticProfessionService.createNewArtisticProfession(artisticProfession);
                artistAccount.setArtisticProfession(artisticProfession);
                artistAccounts.add(artistAccount);
            }
        }
        return artistAccounts;
    }

    @Override
    public List<Card> importArtistCardFile(InputStream inputStream) throws IOException {
        List<Card> cards = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yy");
        for (Row row : sheet){
            if (row.getRowNum() > 0) {
                Card card =new Card();
                card.setRefCard(referenceSequenceService.generateRefCard());
                card.setNumCard(Integer.parseInt(getCellValue(row,0)));
                card.setDateCardCreation(LocalDate.parse(getCellValue(row,1),formatter));
                card.setDateCardValidation(LocalDate.parse(getCellValue(row,2),formatter));
                card.setCardType(getCellValue(row,3));
                card.setStatus(getCellValue(row,4));
                cards.add(card);
            }
        }
        return cards;
    }


    @Override
    public List<ArtistAccountResponse> getAllArtists() {
        List<ArtistAccountResponse> artists=new ArrayList<>();

        artistAccountRepository.findAll().forEach(x-> artists.add(artistAccountMapper.artistAccountToArtistAccountResponse(x)));
        return artists;
    }


    @Override
    public ByteArrayInputStream exportArtistData(List<ArtistAccountResponse> artists) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet = workbook.createSheet("artists");
            Row row = sheet.createRow(0);

            //Define Header Cel Style
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            //creating header cells
            for(int i=0;i<HeaderArtistAccount.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(HeaderArtistAccount[i]);
                cell.setCellStyle(cellStyle);

            }

            //creating data rows foreach ArtistAccount
            for (int i = 0; i < artists.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(artists.get(i).getRefArtistAccount());
                dataRow.createCell(1).setCellValue(artists.get(i).getCin());
                dataRow.createCell(2).setCellValue(artists.get(i).getFirstName());
                dataRow.createCell(3).setCellValue(artists.get(i).getLastName());
                dataRow.createCell(4).setCellValue(artists.get(i).getFirstNameAR());
                dataRow.createCell(5).setCellValue(artists.get(i).getLastNameAR());
                dataRow.createCell(6).setCellValue(artists.get(i).getArtistName());
                dataRow.createCell(7).setCellValue(artists.get(i).getArtistNameAR());
                dataRow.createCell(8).setCellValue(artists.get(i).getGender());
                dataRow.createCell(9).setCellValue(artists.get(i).getIdentityType().toString());
                dataRow.createCell(10).setCellValue(artists.get(i).getIdentityNumber());
                dataRow.createCell(11).setCellValue(artists.get(i).getIdentityProfType());
                dataRow.createCell(12).setCellValue(artists.get(i).getArtistSpeciality());
                dataRow.createCell(13).setCellValue(artists.get(i).getArtistSpecialityAR());
                dataRow.createCell(14).setCellValue(artists.get(i).getEmail());
                dataRow.createCell(15).setCellValue(artists.get(i).getPhoneNumber());
                dataRow.createCell(16).setCellValue(artists.get(i).getOtherPhoneNumber());
                dataRow.createCell(17).setCellValue(artists.get(i).getMaritalStatus().toString());
                dataRow.createCell(18).setCellValue(artists.get(i).getDependentChildren());
                dataRow.createCell(19).setCellValue(artists.get(i).getOtherJobName());
                dataRow.createCell(20).setCellValue(artists.get(i).getSocialSecurityName());
                dataRow.createCell(21).setCellValue(artists.get(i).getSocialSecurityID());
                dataRow.createCell(22).setCellValue(artists.get(i).getArtisticWorkStartDate());
                dataRow.createCell(23).setCellValue(artists.get(i).getLastArtisticActivity());
                dataRow.createCell(24).setCellValue(artists.get(i).getTeamName());
                dataRow.createCell(25).setCellValue(artists.get(i).getTeamCreationDate());
                dataRow.createCell(26).setCellValue(artists.get(i).getStudyLevel());
                dataRow.createCell(27).setCellValue(artists.get(i).getArtisticEtablishmentName());
                dataRow.createCell(28).setCellValue(artists.get(i).getRibNumber());
                dataRow.createCell(29).setCellValue(artists.get(i).getDomainName());
                dataRow.createCell(30).setCellValue(artists.get(i).getStatus().toString());
                dataRow.createCell(31).setCellValue(artists.get(i).getBirthdata().getNationality());
                dataRow.createCell(32).setCellValue(artists.get(i).getBirthdata().getBirthDate().toString());
                dataRow.createCell(33).setCellValue(artists.get(i).getBirthdata().getBirthCountry());
                dataRow.createCell(34).setCellValue(artists.get(i).getBirthdata().getBirthCity());



            }
            for(int i=0;i<31;i++){
                sheet.autoSizeColumn(i);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch (IOException ex){
            logger.error("Error during export Excel file", ex);
            return null;
        }
    }


    @Override
    public ByteArrayInputStream exportArtistCard(List<CardResponse> cards) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet = workbook.createSheet("artistCard");
            Row row = sheet.createRow(0);

            //Define Header Cel Style
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            //creating header cells
            for(int i=0;i<HeaderCards.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(HeaderCards[i]);
                cell.setCellStyle(cellStyle);

            }


            //creating data rows foreach ArtistCard
            for (int i = 0; i < cards.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(cards.get(i).getRefCard());
                dataRow.createCell(1).setCellValue(cards.get(i).getNumCard());
                dataRow.createCell(2).setCellValue(cards.get(i).getDateCardCreation().toString());
                dataRow.createCell(3).setCellValue(cards.get(i).getDateCardValidation().toString());
                dataRow.createCell(4).setCellValue(cards.get(i).getCardType());
                dataRow.createCell(5).setCellValue(cards.get(i).getRefArtistAccount());
                dataRow.createCell(6).setCellValue(cards.get(i).getStatus());

            }
            for(int i=0;i<cards.size();i++){
                sheet.autoSizeColumn(i);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch (IOException ex){
            logger.error("Error during export Excel file", ex);
            return null;
        }
    }


    @Override
    public String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }


}
