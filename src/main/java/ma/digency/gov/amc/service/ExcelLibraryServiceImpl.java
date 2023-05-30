package ma.digency.gov.amc.service;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.library.AuthorResponse;
import ma.digency.gov.amc.dto.library.LibraryResponse;
import ma.digency.gov.amc.mapper.LibraryMapper;
import ma.digency.gov.amc.repository.AuthorRepository;
import ma.digency.gov.amc.repository.LibraryRepository;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelLibraryServiceImpl implements ExcelLibraryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final LibraryRepository libraryRepository;

    private final ReferenceSequenceService referenceSequenceService;

    private final LibraryMapper libraryMapper;

    String[] Header={"refLibrary","libraryName","libraryCreationDate","businessRegisterNumber","StandardDefinitionOfBusiness","cnssNumber","librarySpace",

            "Address","city","phoneNumber","email","adressesNumber"
           };



    @Override
    public List<LibraryResponse> getAllLibraries() {
        List<LibraryResponse> libraries=new ArrayList<>();


        libraryRepository.findAll().forEach(find-> libraries.add(libraryMapper.libraryToLibraryResponse(find)));

        return libraries;
    }

    @Override
    public ByteArrayInputStream exportLibraryData(List<LibraryResponse> library) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet = workbook.createSheet("authors");
            Row row = sheet.createRow(0);

            //Define Header Cel Style
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            //creating header cells
            for(int i=0;i<Header.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
            //creating data rows foreach Author

            for (int i = 0; i < library.size(); i++) {

                Row dataRow = sheet.createRow(i + 1);

                dataRow.createCell(0).setCellValue(library.get(i).getRefLibrary());

                dataRow.createCell(1).setCellValue(library.get(i).getLibraryName());

                dataRow.createCell(2).setCellValue(library.get(i).getLibraryCreationDate());

                dataRow.createCell(3).setCellValue(library.get(i).getBusinessRegisterNumber());

                dataRow.createCell(4).setCellValue(library.get(i).getAddress());

                dataRow.createCell(5).setCellValue(library.get(i).getStandardDefinitionOfBusiness());

                dataRow.createCell(7).setCellValue(library.get(i).getPhoneNumber());

                dataRow.createCell(8).setCellValue(library.get(i).getCnssNumber());

                dataRow.createCell(9).setCellValue(library.get(i).getLibrarySpace());

                dataRow.createCell(10).setCellValue(library.get(i).getAddress());

                dataRow.createCell(11).setCellValue(library.get(i).getCity());

                dataRow.createCell(12).setCellValue(library.get(i).getPhoneNumber());

                dataRow.createCell(13).setCellValue(library.get(i).getEmail());

                dataRow.createCell(14).setCellValue(library.get(i).getAdressesNumber());

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
}
