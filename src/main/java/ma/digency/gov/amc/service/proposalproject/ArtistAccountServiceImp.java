package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.ArtistAccountRepository;
import ma.digency.gov.amc.repository.entity.Address;
import ma.digency.gov.amc.repository.entity.BirthData;
import ma.digency.gov.amc.repository.entity.proposalproject.*;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.IdentityType;
import ma.digency.gov.amc.utils.enumeration.MaritalStatus;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistAccountServiceImp implements ArtistAccountService{

    private final ArtistAccountRepository artistAccountRepository;

    private final ReferenceSequenceService referenceSequenceService;

    private final GeneralInformationService generalInformationService;

    private final ArtisticProfessionCategoryService artisticProfessionCategoryService;

    private final ArtisticProfessionService artisticProfessionService;

    private final ArtisticProfessionDomainService artisticProfessionDomainService;


    @Override
    public ArtistAccount createNewArtistAccount(ArtistAccount artistAccount) {
        artistAccount.setRefArtistAccount(referenceSequenceService.generateRefArtistAccount());
        return artistAccountRepository.save(artistAccount);
    }

    @Override
    public ArtistAccount updateArtistAccount(ArtistAccount artistAccount) {
        return artistAccountRepository.save(artistAccount);
    }

    @Override
    public void deleteArtistAccount(ArtistAccount artistAccount) {
        artistAccountRepository.delete(artistAccount);
    }

    @Override
    public List<ArtistAccount> findAllArtistAccounts() {
        return this.artistAccountRepository.findAll();
    }

    @Override
    public Optional<ArtistAccount> findArtistAccountById(Long id) {
        return this.artistAccountRepository.findById(id);
    }


    @Override
    public ArtistAccount findArtistAccountByEmail(String email) {
        return artistAccountRepository.findByEmail(email);
    }


    @Override
    public Optional<ArtistAccount> findArtistAccountByRef(String ref) {
        return this.artistAccountRepository.findByRefArtistAccount(ref);
    }


    @Override
    public ArtistAccount findArtistAccountByRefArtist(String ref) {
        return artistAccountRepository.findArtistAccountByRefArtistAccount(ref);
    }


    @Override
    public Page<ArtistAccount> findAllArtistAccountsPageable(Pageable pageable) {
        return this.artistAccountRepository.findAll(pageable);
    }

    @Override
    public Optional<ArtistAccount> updateArtistAccountStatus(ArtistAccount artistAccount, StatusEnum status) {
        artistAccount.setStatus(status);
        return Optional.of(artistAccountRepository.save(artistAccount));
    }

    @Override
    public ArtistAccount findArtistByCin(String cin) {
        return this.artistAccountRepository.findArtistAccountByCin(cin);
    }

    @Override
    public Optional<ArtistAccount> findArtistByRef(String ref) {
        return this.artistAccountRepository.findByRefArtistAccount(ref);
    }

    @Override
    public Page<ArtistAccount> findAllByAccountAndStatusNot(Account account, StatusEnum status,Pageable pageable) {
        return this.artistAccountRepository.findAll(setCretieria(account, status), pageable);
    }

    @Override
    public Void save(MultipartFile file) {
        try{
            List<ArtistAccount> artistAccounts=importExcelData(file.getInputStream());
            for(ArtistAccount artistAccount:artistAccounts){
                ArtistAccount artist=artistAccountRepository.findArtistAccountByCin(artistAccount.getCin());
                if(artist==null) {
                    artistAccount.setRefArtistAccount(referenceSequenceService.generateRefArtistAccount());
                    artistAccountRepository.save(artistAccount);
                }
            }
        }
        catch(IOException e){
            throw  new RuntimeException("fail to store excel data:" + e.getMessage());
        }

        return null;
    }

    @Override
    public List<ArtistAccount> importExcelData(InputStream is) throws IOException {
        List<ArtistAccount> artistAccounts=new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yy");
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
    public String getCellValue(Row row, int cellNo) {
        DataFormatter formatter=new DataFormatter();
        Cell cell= (Cell) row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    @Override
    public ByteArrayInputStream exportArtistsData(List<ArtistAccount> artistAccounts) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet=workbook.createSheet("demands");
            Row row = sheet.createRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            String[] Header={"refArtistAccoun","cin","firstName","lastName","firsNameAR","lastNameAr","artistName","artistNameAR","gender","identityType","identityNumber",
                    "identityProfType","artistSpeciality","artistSpecialityAR","email","phoneNumber","otherPhoneNumber",
                    "maritalStatus","dependentChildren","otherJobName","socialSecurityName","socialSecurityID","artisticWorkStartDate","lastArtisticActivity",
                    "teamName","teamCreationDate","studyLevel","artisticEtablishmentName","birthDate","birthCountry","birthCity","nationality","ribNumber","domaineName","status","province","posalCode","city","country","address","region","projectName","projectTitle","projectType","numDancesOrSongs","durationTime","projectCost","projectDescription","albumTitle","NameProfession","NameAr","ArtisticProfessionDomainNme","ArtisticProfessionDomainNmeAr","ArtistProfessionCategoryName","ArtistProfessionCategoryNameAr"
            };
            for(int i=0;i<Header.length;i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
            for (int i = 0; i < artistAccounts.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(artistAccounts.get(i).getRefArtistAccount());
                dataRow.createCell(1).setCellValue(artistAccounts.get(i).getCin());
                dataRow.createCell(2).setCellValue(artistAccounts.get(i).getFirstName());
                dataRow.createCell(3).setCellValue(artistAccounts.get(i).getLastName());
                dataRow.createCell(4).setCellValue(artistAccounts.get(i).getFirstNameAR());
                dataRow.createCell(5).setCellValue(artistAccounts.get(i).getLastNameAR());
                dataRow.createCell(6).setCellValue(artistAccounts.get(i).getArtistName());
                dataRow.createCell(7).setCellValue(artistAccounts.get(i).getArtistNameAR());

                dataRow.createCell(8).setCellValue(artistAccounts.get(i).getGender());
                dataRow.createCell(9).setCellValue(artistAccounts.get(i).getIdentityType().toString());
                dataRow.createCell(10).setCellValue(artistAccounts.get(i).getIdentityNumber());
                dataRow.createCell(11).setCellValue(artistAccounts.get(i).getIdentityProfType());
                dataRow.createCell(12).setCellValue(artistAccounts.get(i).getArtistSpeciality());
                dataRow.createCell(13).setCellValue(artistAccounts.get(i).getArtistSpecialityAR());
                dataRow.createCell(14).setCellValue(artistAccounts.get(i).getEmail());
                dataRow.createCell(15).setCellValue(artistAccounts.get(i).getPhoneNumber());
                dataRow.createCell(16).setCellValue(artistAccounts.get(i).getOtherPhoneNumber());
                dataRow.createCell(17).setCellValue(artistAccounts.get(i).getMaritalStatus().toString());
                dataRow.createCell(18).setCellValue(artistAccounts.get(i).getDependentChildren());
                dataRow.createCell(19).setCellValue(artistAccounts.get(i).getOtherJobName());

                dataRow.createCell(20).setCellValue(artistAccounts.get(i).getSocialSecurityName());
                dataRow.createCell(21).setCellValue(artistAccounts.get(i).getSocialSecurityID());
                dataRow.createCell(22).setCellValue(artistAccounts.get(i).getArtisticWorkStartDate());

                dataRow.createCell(23).setCellValue(artistAccounts.get(i).getLastArtisticActivity());
                dataRow.createCell(24).setCellValue(artistAccounts.get(i).getTeamName());
                dataRow.createCell(25).setCellValue(artistAccounts.get(i).getTeamCreationDate());

                dataRow.createCell(26).setCellValue(artistAccounts.get(i).getStudyLevel());
                dataRow.createCell(27).setCellValue(artistAccounts.get(i).getArtisticEtablishmentName());
                dataRow.createCell(28).setCellValue(artistAccounts.get(i).getBirthdata().getBirthDate());
                dataRow.createCell(29).setCellValue(artistAccounts.get(i).getBirthdata().getBirthCountry());
                dataRow.createCell(30).setCellValue(artistAccounts.get(i).getBirthdata().getBirthCity());
                dataRow.createCell(31).setCellValue(artistAccounts.get(i).getBirthdata().getNationality());

                dataRow.createCell(2).setCellValue(artistAccounts.get(i).getRibNumber());
                dataRow.createCell(33).setCellValue(artistAccounts.get(i).getDomainName());
                dataRow.createCell(34).setCellValue(artistAccounts.get(i).getStatus().toString());
                dataRow.createCell(35).setCellValue(artistAccounts.get(i).getAddress().getProvince());

                dataRow.createCell(36).setCellValue(artistAccounts.get(i).getAddress().getPostalCode());
                dataRow.createCell(37).setCellValue(artistAccounts.get(i).getAddress().getCity());
                dataRow.createCell(38).setCellValue(artistAccounts.get(i).getAddress().getCountry());
                dataRow.createCell(39).setCellValue(artistAccounts.get(i).getAddress().getAddress());
                dataRow.createCell(40).setCellValue(artistAccounts.get(i).getAddress().getRegion());
                dataRow.createCell(41).setCellValue(artistAccounts.get(i).getGeneralInformation().getProjectName());
                dataRow.createCell(42).setCellValue(artistAccounts.get(i).getGeneralInformation().getProjectTitle());
                dataRow.createCell(43).setCellValue(artistAccounts.get(i).getGeneralInformation().getProjectType());
                dataRow.createCell(44).setCellValue(artistAccounts.get(i).getGeneralInformation().getNumDancesOrSongs());
                dataRow.createCell(45).setCellValue(artistAccounts.get(i).getGeneralInformation().getDurationTime());
                dataRow.createCell(46).setCellValue(artistAccounts.get(i).getGeneralInformation().getProjectCost());
                dataRow.createCell(47).setCellValue(artistAccounts.get(i).getGeneralInformation().getProjectDescription());
                dataRow.createCell(48).setCellValue(artistAccounts.get(i).getGeneralInformation().getAlbumTitle());
                dataRow.createCell(49).setCellValue(artistAccounts.get(i).getArtisticProfession().getName());
                dataRow.createCell(50).setCellValue(artistAccounts.get(i).getArtisticProfession().getNameAr());

                ArtisticProfessionDomain artisticProfessionDomain=artisticProfessionDomainService.findArtisticProfessionDomainByRef(artistAccounts.get(i).getArtisticProfession().getRefArtisticProfessionDomain()).get();
                dataRow.createCell(51).setCellValue(artisticProfessionDomain.getName());
                dataRow.createCell(52).setCellValue(artisticProfessionDomain.getNameAr());

                ArtisticProfessionCategory artisticProfessionCategory=artisticProfessionCategoryService.findArtisticProfessionCategoryByRef(artisticProfessionDomain.getRefArtisticProfessionCategory()).get();
                dataRow.createCell(53).setCellValue(artisticProfessionCategory.getName());
                dataRow.createCell(54).setCellValue(artisticProfessionCategory.getNameAr());
            }

            for(int i=0;i<31;i++){
                sheet.autoSizeColumn(i);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch (IOException ex){
            return null;
        }
    }


    @Override
    public Optional<ArtistAccount> findArtistAccountByAccount(Account account) {
        return this.artistAccountRepository.findFirstByAccountOrderByDateCreationAsc(account);
    }

    private Specification<ArtistAccount> setCretieria(Account account,StatusEnum status) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("account"), account));
            predicates.add(criteriaBuilder.notEqual(root.get("status"), status));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
