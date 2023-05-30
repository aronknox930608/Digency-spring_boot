package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.AwardObtainedRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardObtained;
import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.service.proposalproject.ArtistAccountService;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.commons.collections4.IterableUtils;
import org.apache.poi.ss.usermodel.*;
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
public class AwardObtainedServiceImpl implements AwardObtainedService{

    private final AwardObtainedRepository awardObtainedRepository;

    private final ReferenceSequenceService referenceSequenceService;

    private final ArtistAccountService artistAccountService;

    @Override
    public List<AwardObtained> findAwardObtainedByRefArtist(String refArtist) {
        return awardObtainedRepository.findAwardObtainedByArtist(refArtist);
    }

    @Override
    public AwardObtained createOrUpdateAwardObtained(AwardObtained awardObtained) {
        if(null==awardObtained.getReAwardObtained()){
            var ref=referenceSequenceService.generateRefAwardObtained();
            awardObtained.setReAwardObtained(ref);
        }
        return awardObtainedRepository.save(awardObtained);
    }

    @Override
    public AwardObtained findAwardObtainedByRef(String refAwardObtained) {
        return awardObtainedRepository.findAwardObtainedByReAwardObtained(refAwardObtained);
    }

    @Override
    public void deleteAwardObtained(String refAwardObtained) {
        awardObtainedRepository.delete(awardObtainedRepository.findAwardObtainedByReAwardObtained(refAwardObtained));
    }

    @Override
    public Void save(MultipartFile file) {
        try{
            List<AwardObtained> awards=importExcelData(file.getInputStream());
            for(AwardObtained award:awards){
                award.setReAwardObtained(referenceSequenceService.generateRefAwardObtained());
                awardObtainedRepository.save(award);
            }}
        catch(IOException e){
            throw  new RuntimeException("fail to store excel data:" + e.getMessage());
        }

        return null;
    }

    @Override
    public List<AwardObtained> importExcelData(InputStream is) throws IOException {
        List<AwardObtained> awardObtaineds=new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet){
            if(IterableUtils.size(row)<=1){
                break;
            }
            if (row.getRowNum() != 0) {
                AwardObtained awardObtained = new AwardObtained();
                awardObtained.setAward(getCellValue(row,0));
                awardObtained.setOrganisers(getCellValue(row,1));
                awardObtained.setYear(Float.parseFloat(getCellValue(row,2)));
                awardObtained.setArtist(artistAccountService.findArtistByCin(getCellValue(row,3)).getRefArtistAccount());
                if(awardObtained.getArtist()!=null){
                    awardObtaineds.add(awardObtained);
                }

            }
        }
        return awardObtaineds;
    }

    @Override
    public String getCellValue(Row row, int cellNo) {
        DataFormatter formatter=new DataFormatter();
        Cell cell= (Cell) row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    @Override
    public List<AwardObtained> getAllAwardObtained() {
        return awardObtainedRepository.findAll();
    }

    @Override
    public ByteArrayInputStream exportAwardObtainedData(List<AwardObtained> awardObtaineds) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet=workbook.createSheet("demands");
            Row row = sheet.createRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            String[] Header={"refAwardObtained","award","organisers", "year","writerFirstName","writerLastName","writerCin"};
            for(int i=0;i<Header.length;i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
            for (int i = 0; i < awardObtaineds.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(awardObtaineds.get(i).getReAwardObtained());
                dataRow.createCell(1).setCellValue(awardObtaineds.get(i).getAward());
                dataRow.createCell(2).setCellValue(awardObtaineds.get(i).getOrganisers());
                dataRow.createCell(3).setCellValue(awardObtaineds.get(i).getYear());
                ArtistAccount artistAccount=artistAccountService.findArtistAccountByRef(awardObtaineds.get(i).getArtist()).get();
                dataRow.createCell(4).setCellValue(artistAccount.getFirstName());
                dataRow.createCell(5).setCellValue(artistAccount.getLastName());
                dataRow.createCell(6).setCellValue(artistAccount.getCin());

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
}
