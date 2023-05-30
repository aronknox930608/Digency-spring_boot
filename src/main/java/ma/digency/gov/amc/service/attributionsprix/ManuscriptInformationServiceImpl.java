package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.*;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.FontType;
import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptInformation;
import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptType;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.commons.collections4.IterableUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ManuscriptInformationServiceImpl implements ManuscriptInformationService {

    private final ReferenceSequenceService referenceSequenceService;

    private final ManuscriptInformationRepository manuscriptInformationRepository;

    private final ManuscriptTypeRepository manuscriptTypeRepository;

    private final FontTypeRepository fontTypeRepository;

    private final DemandRepository demandRepository;

    private final OwnerHandWrittenRepository ownerHandWrittenRepository;


    @Override
    public ManuscriptInformation createOrUpdatemanuscript(ManuscriptInformation manuscriptInformation) {
        if (null == manuscriptInformation.getRefManuscriptInformation()) {
            var ref = referenceSequenceService.generateRefManuscriptType();
            manuscriptInformation.setRefManuscriptInformation(ref);
        }

        return manuscriptInformationRepository.save(manuscriptInformation);
    }

    @Override
    public ManuscriptInformation update(ManuscriptInformation manuscriptInformation) {
        ManuscriptInformation maniscrupInfoFind = manuscriptInformationRepository.findManuscriptInformationByRefManuscriptInformation(manuscriptInformation.getRefManuscriptInformation());
        maniscrupInfoFind.setType(manuscriptInformation.getType());
       // maniscrupInfoFind.setFontType(manuscriptInformation.getFontType());
        maniscrupInfoFind.setWritingDate(manuscriptInformation.getWritingDate());
        maniscrupInfoFind.setBibliography(manuscriptInformation.getBibliography());
        maniscrupInfoFind.setConclusion(manuscriptInformation.getConclusion());
        maniscrupInfoFind.setIntroduction(manuscriptInformation.getIntroduction());
        maniscrupInfoFind.setExplanation(manuscriptInformation.getExplanation());
        maniscrupInfoFind.setRule(manuscriptInformation.getRule());
        maniscrupInfoFind.setSize(manuscriptInformation.getSize());
        maniscrupInfoFind.setAuthorshipDate(manuscriptInformation.getAuthorshipDate());
        maniscrupInfoFind.setCopyDate(manuscriptInformation.getCopyDate());
        maniscrupInfoFind.setAuthorName(manuscriptInformation.getAuthorName());
        maniscrupInfoFind.setDocumentsSubject(manuscriptInformation.getDocumentsSubject());
        maniscrupInfoFind.setIssuingAuthority(manuscriptInformation.getIssuingAuthority());
        maniscrupInfoFind.setAuthoritiesConcerned(manuscriptInformation.getAuthoritiesConcerned());
        maniscrupInfoFind.setTranscriberName(manuscriptInformation.getTranscriberName());
        maniscrupInfoFind.setManuscriptTitle(manuscriptInformation.getManuscriptTitle());
        maniscrupInfoFind.setPapersNumber(manuscriptInformation.getPapersNumber());
        return manuscriptInformationRepository.save(maniscrupInfoFind);
    }

    @Override
    public void delete(ManuscriptInformation maniscruptInformation) {
        manuscriptInformationRepository.delete(maniscruptInformation);
    }

    @Override
    public Void save(MultipartFile file) {
        try{
            List<ManuscriptInformation> manuscriptInformations=importExcelData(file.getInputStream());
            for(ManuscriptInformation manuscriptInformation:manuscriptInformations){
                manuscriptInformation.setRefManuscriptInformation(referenceSequenceService.generateRefManuscriptType());
                manuscriptInformationRepository.save(manuscriptInformation);
            }}
        catch(IOException e){
            throw  new RuntimeException("fail to store excel data:" + e.getMessage());
        }

        return null;
    }

    @Override
    public List<ManuscriptInformation> importExcelData(InputStream is) throws IOException {
        List<ManuscriptInformation> manuscriptInformations=new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yy");
        for (Row row : sheet){
            if(IterableUtils.size(row)<=1){
                break;
            }
            if (row.getRowNum() != 0) {
                ManuscriptInformation manuscriptInformation = new ManuscriptInformation();
                manuscriptInformation.setDocumentsSubject(getCellValue(row, 0));
                manuscriptInformation.setIssuingAuthority(getCellValue(row, 1));
                manuscriptInformation.setAuthoritiesConcerned(getCellValue(row, 2));

                ManuscriptType manuscriptType = new ManuscriptType();
                manuscriptType.setType(getCellValue(row, 3));
                manuscriptType.setRefManuscriptType(referenceSequenceService.generateRefDocument());
                manuscriptType = manuscriptTypeRepository.save(manuscriptType);
                manuscriptInformation.setType(manuscriptType);

                FontType fontType = new FontType();
                fontType.setType(getCellValue(row, 4));
                fontType.setRefFontType(referenceSequenceService.generateRefDocument());
                fontType = fontTypeRepository.save(fontType);
                //manuscriptInformation.setFontType(fontType);

                manuscriptInformation.setWritingDate(LocalDate.parse(getCellValue(row, 5), formatter));
                manuscriptInformation.setBibliography(getCellValue(row, 6));
                manuscriptInformation.setManuscriptTitle(getCellValue(row, 7));
                manuscriptInformation.setAuthorName(getCellValue(row, 8));
                manuscriptInformation.setIntroduction(getCellValue(row, 9));
                manuscriptInformation.setConclusion(getCellValue(row, 10));
                manuscriptInformation.setPapersNumber(Integer.parseInt(getCellValue(row, 11)));
                manuscriptInformation.setSize(Float.parseFloat(getCellValue(row, 12)));
                manuscriptInformation.setRule(getCellValue(row, 13));
                manuscriptInformation.setAuthorshipDate(LocalDate.parse(getCellValue(row, 14), formatter));
                manuscriptInformation.setTranscriberName(getCellValue(row, 15));
                manuscriptInformation.setCopyDate(LocalDate.parse(getCellValue(row, 16), formatter));
                manuscriptInformation.setExplanation(getCellValue(row, 17));
              /*  OwnerHandWritten ownerHandWritten=ownerHandWrittenRepository.findOwnerHandWrittenByCin(getCellValue(row,18));
                if(ownerHandWritten!=null){
                    manuscriptInformation.setOwner(ownerHandWritten);
                    manuscriptInformations.add(manuscriptInformation);
                }*/

            }
            }
        return manuscriptInformations;
    }

    @Override
    public String getCellValue(Row row, int cellNo) {
        DataFormatter formatter=new DataFormatter();
        Cell cell= (Cell) row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    @Override
    public List<ManuscriptInformation> getAllManuscriptInformation() {
        return manuscriptInformationRepository.findAll();
    }

    @Override
    public ByteArrayInputStream exportManuscriptData(List<ManuscriptInformation> manuscriptInformations) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet = workbook.createSheet("manuscriptInformations");
            Row row = sheet.createRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            String[] Header= {"RefManuscriptInfo", "documentsSubject","issuingAuthority","authoritiesConcerned","type","fontType","owner firstname","owner lastname","owner cin","writingDate","bibliography","manuscriptTitle","authorName","introduction","conclusion","papersNumber","size","rule","authorshipDate","transcriberName","copyDate","explanation"};
            for(int i=0;i<Header.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
            for (int i = 0; i < manuscriptInformations.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(manuscriptInformations.get(i).getRefManuscriptInformation());
                dataRow.createCell(1).setCellValue(manuscriptInformations.get(i).getDocumentsSubject());
                dataRow.createCell(2).setCellValue(manuscriptInformations.get(i).getIssuingAuthority());
                dataRow.createCell(3).setCellValue(manuscriptInformations.get(i).getIssuingAuthority());
                dataRow.createCell(4).setCellValue(manuscriptInformations.get(i).getAuthoritiesConcerned());
                dataRow.createCell(5).setCellValue(manuscriptInformations.get(i).getType().getType());
               // dataRow.createCell(6).setCellValue(manuscriptInformations.get(i).getFontType().getType());
                //dataRow.createCell(7).setCellValue(manuscriptInformations.get(i).getOwner().getFirstName());
               // dataRow.createCell(8).setCellValue(manuscriptInformations.get(i).getOwner().getLastName());
                dataRow.createCell(9).setCellValue(manuscriptInformations.get(i).getWritingDate());
                dataRow.createCell(10).setCellValue(manuscriptInformations.get(i).getBibliography());
                dataRow.createCell(11).setCellValue(manuscriptInformations.get(i).getManuscriptTitle());
                dataRow.createCell(12).setCellValue(manuscriptInformations.get(i).getAuthorName());
                dataRow.createCell(13).setCellValue(manuscriptInformations.get(i).getIntroduction());
                dataRow.createCell(14).setCellValue(manuscriptInformations.get(i).getConclusion());
                dataRow.createCell(15).setCellValue(manuscriptInformations.get(i).getPapersNumber());
                dataRow.createCell(16).setCellValue(manuscriptInformations.get(i).getSize());
                dataRow.createCell(17).setCellValue(manuscriptInformations.get(i).getRule());
                dataRow.createCell(18).setCellValue(manuscriptInformations.get(i).getAuthorshipDate());
                dataRow.createCell(19).setCellValue(manuscriptInformations.get(i).getTranscriberName());
                dataRow.createCell(20).setCellValue(manuscriptInformations.get(i).getCopyDate());
                dataRow.createCell(21).setCellValue(manuscriptInformations.get(i).getExplanation());

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
    public ManuscriptInformation findManuscriptInformationByDemand(DemandPrice demandPrice) {
        return manuscriptInformationRepository.findManuscriptInformationByDemand(demandPrice);
    }

    @Override
    public ManuscriptInformation findManuscriptInformationByRef(String ref) {
        return manuscriptInformationRepository.findManuscriptInformationByRefManuscriptInformation(ref);
    }
}



