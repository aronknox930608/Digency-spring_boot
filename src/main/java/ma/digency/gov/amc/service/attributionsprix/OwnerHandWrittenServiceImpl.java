package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.attributionsprix.OwnerHandWrittenSearchCriteria;
import ma.digency.gov.amc.repository.OwnerHandWrittenCriteriaRepository;
import ma.digency.gov.amc.repository.OwnerHandWrittenRepository;
import ma.digency.gov.amc.repository.SearchFiles.OwnerHandWrittenPage;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.data.domain.Page;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
@RequiredArgsConstructor

public class OwnerHandWrittenServiceImpl implements OwnerHandWrittenService {

    private final ReferenceSequenceService referenceSequenceService;

    private final OwnerHandWrittenRepository ownerHandWrittenRepository;

    private final OwnerHandWrittenCriteriaRepository ownerHandWrittenCriteriaRepository;


    @Override
    public OwnerHandWritten createOrUpdateownerHW(OwnerHandWritten ownerHandWritten) {
       if(ownerHandWritten.getRefOwnerHandWritten()==null){
           var ref=referenceSequenceService.generateRefOwnerHandwritten();
           ownerHandWritten.setRefOwnerHandWritten(ref);
       }
       return ownerHandWrittenRepository.save(ownerHandWritten);
    }

    @Override
    public OwnerHandWritten update(OwnerHandWritten ownerHandWritten) {
        return ownerHandWrittenRepository.save(ownerHandWritten);
    }

    @Override
    public OwnerHandWritten findOwnerHandWrittenByRef(String refOwnerHandWritten) {
        return ownerHandWrittenRepository.findOwnerHandWrittenByRefOwnerHandWritten(refOwnerHandWritten);
    }

    @Override
    public Page<OwnerHandWritten> getOwnerHandWrittens(OwnerHandWrittenPage ownerHandWrittenPage, OwnerHandWrittenSearchCriteria ownerHandWrittenSearchCriteria) {
        return ownerHandWrittenCriteriaRepository.findAllWithFilters(ownerHandWrittenPage,ownerHandWrittenSearchCriteria);
    }

    @Override
    public Void save(MultipartFile file) {
        try{
            List<OwnerHandWritten> ownerHandWrittens=importExcelData(file.getInputStream());
            for(OwnerHandWritten ownerHandWritten:ownerHandWrittens){
                OwnerHandWritten owner=ownerHandWrittenRepository.findOwnerHandWrittenByCin(ownerHandWritten.getCin());
                if(owner==null) {
                    ownerHandWritten.setRefOwnerHandWritten(referenceSequenceService.generateRefOwnerHandwritten());
                    ownerHandWrittenRepository.save(ownerHandWritten);
                }
            }
        }
            catch(IOException e){
                throw  new RuntimeException("fail to store excel data:" + e.getMessage());
            }

        return null;
    }

    @Override
    public List<OwnerHandWritten> importExcelData(InputStream is) throws IOException {
        List<OwnerHandWritten> ownerHandWrittens=new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet){
            if (row.getRowNum() != 0) {
                OwnerHandWritten ownerHandWritten = new OwnerHandWritten();
                ownerHandWritten.setCin(getCellValue(row, 0));
                ownerHandWritten.setFirstName(getCellValue(row, 1));
                ownerHandWritten.setLastName(getCellValue(row, 2));
                ownerHandWritten.setFirstNameAr(getCellValue(row, 3));
                ownerHandWritten.setLastNameAr(getCellValue(row, 4));
                ownerHandWritten.setGender(getCellValue(row, 5));
                ownerHandWritten.setPhone(getCellValue(row, 6));
                ownerHandWritten.setRib(getCellValue(row, 7));
                ownerHandWrittens.add(ownerHandWritten);
            }
        }
        return ownerHandWrittens;
    }

    @Override
    public String getCellValue(Row row, int cellNo) {
        DataFormatter formatter=new DataFormatter();
        Cell cell= (Cell) row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    @Override
    public List<OwnerHandWritten> getAllOwners() {
        return ownerHandWrittenRepository.findAll();
    }

    @Override
    public ByteArrayInputStream exportArtistData(List<OwnerHandWritten> owners) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet = workbook.createSheet("owners");
            Row row = sheet.createRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            String[] Header= {"RefOwner", "cin", "firstName","lastName","firstNameAr","lastNameAr","gender","email","phone","rib"};
            for(int i=0;i<Header.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
            for (int i = 0; i < owners.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(owners.get(i).getRefOwnerHandWritten());
                dataRow.createCell(1).setCellValue(owners.get(i).getCin());
                dataRow.createCell(2).setCellValue(owners.get(i).getFirstName());
                dataRow.createCell(3).setCellValue(owners.get(i).getLastName());
                dataRow.createCell(4).setCellValue(owners.get(i).getFirstNameAr());
                dataRow.createCell(5).setCellValue(owners.get(i).getLastNameAr());
                dataRow.createCell(6).setCellValue(owners.get(i).getGender());
                dataRow.createCell(7).setCellValue(owners.get(i).getEmail());
                dataRow.createCell(8).setCellValue(owners.get(i).getPhone());
                dataRow.createCell(9).setCellValue(owners.get(i).getRib());

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



