package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.TheaterPieceRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.TheaterPiece;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
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
public class TheaterPieceServiceImpl implements TheaterPieceService{

    private final ReferenceSequenceService referenceSequenceService;

    private final TheaterPieceRepository theaterPieceRepository;

    @Override
    public TheaterPiece createOrUpdate(TheaterPiece theaterPiece) {
        if(null==theaterPiece.getRefTheaterPiece()){
            var ref=referenceSequenceService.generateRefTheaterPiece();
            theaterPiece.setRefTheaterPiece(ref);
        }
        return theaterPieceRepository.save(theaterPiece);
    }

    @Override
    public TheaterPiece findTheaterPieceByRef(String refTheaterPiece) {
        return theaterPieceRepository.findTheaterPieceByRefTheaterPiece(refTheaterPiece);
    }

    @Override
    public Void save(MultipartFile file) {
        try{
            List<TheaterPiece> theaterPieces=importExcelData(file.getInputStream());
            for(TheaterPiece theaterPiece:theaterPieces){
                theaterPiece.setRefTheaterPiece(referenceSequenceService.generateRefTheaterPiece());
                theaterPieceRepository.save(theaterPiece);
            }}
        catch(IOException e){
            throw  new RuntimeException("fail to store excel data:" + e.getMessage());
        }

        return null;
    }

    @Override
    public List<TheaterPiece> importExcelData(InputStream is) throws IOException {
        List<TheaterPiece> theaterPieces=new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yy");
        for (Row row : sheet){
            if(IterableUtils.size(row)<=1){
                break;
            }
            if (row.getRowNum() != 0) {
                TheaterPiece theaterPiece =new TheaterPiece();
                theaterPiece.setTitle(getCellValue(row,0));
                theaterPiece.setTheaterTroupeName(getCellValue(row,1));
                theaterPiece.setTextTheaterPiece(getCellValue(row,2));
                theaterPiece.setDate(LocalDate.parse(getCellValue(row,3),formatter));
                theaterPieces.add(theaterPiece);

            }
        }
        return theaterPieces;
    }

    @Override
    public String getCellValue(Row row, int cellNo) {
        DataFormatter formatter=new DataFormatter();
        Cell cell= (Cell) row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    @Override
    public List<TheaterPiece> getAllTheaterPiece() {
        return theaterPieceRepository.findAll();
    }

    @Override
    public ByteArrayInputStream exportTheaterPircesData(List<TheaterPiece> theaterPieces) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet=workbook.createSheet("theaterPiece");
            Row row = sheet.createRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            String[] Header={"refTheaterPiece","Title","TheaterTroupeName","TextTheaterPiece","Date"};
            for(int i=0;i<Header.length;i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
            for (int i = 0; i < theaterPieces.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(theaterPieces.get(i).getRefTheaterPiece());
                dataRow.createCell(1).setCellValue(theaterPieces.get(i).getTitle());
                dataRow.createCell(2).setCellValue(theaterPieces.get(i).getTheaterTroupeName());
                dataRow.createCell(3).setCellValue(theaterPieces.get(i).getTextTheaterPiece());
                dataRow.createCell(4).setCellValue(theaterPieces.get(i).getDate());

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
