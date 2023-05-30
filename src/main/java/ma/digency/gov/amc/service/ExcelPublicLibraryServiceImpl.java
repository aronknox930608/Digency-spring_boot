package ma.digency.gov.amc.service;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.participant.PrinterResponse;
import ma.digency.gov.amc.dto.publiclibrary.PublicLibraryResponse;
import ma.digency.gov.amc.mapper.PrinterMapper;
import ma.digency.gov.amc.mapper.PublicLibraryMapper;
import ma.digency.gov.amc.repository.PrinterRepository;
import ma.digency.gov.amc.repository.PublicLibraryRepository;
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
public class ExcelPublicLibraryServiceImpl implements ExcelPublicLibraryService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PublicLibraryRepository publicLibraryRepository;

    private final ReferenceSequenceService referenceSequenceService;

    private final PublicLibraryMapper publicLibraryMapper;

    String[] Header={"refPublicLibrary","libraryName","nameOfTheResponsible","Partner","libraryStatus","libraryType",

            "creationDate","space","employeesNumber","openingTime","address","region","provincePrefecture","office_Phone","fax"

            ,"email","website","internetConnection","updateDateDocumentaryFund"
           };



    @Override
    public List<PublicLibraryResponse> getAllPublicLibraries() {
        List<PublicLibraryResponse> libraries=new ArrayList<>();


        publicLibraryRepository.findAll().forEach(find-> libraries.add(publicLibraryMapper.publicLibraryToPublicLibraryResponse(find)));

        return libraries;
    }

    @Override
    public ByteArrayInputStream exportPublicLibraryData(List<PublicLibraryResponse> publicLibraries) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet = workbook.createSheet("publicLibraries");
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

            for (int i = 0; i < publicLibraries.size(); i++) {

                Row dataRow = sheet.createRow(i + 1);

                dataRow.createCell(0).setCellValue(publicLibraries.get(i).getRefPublicLibrary());

                dataRow.createCell(1).setCellValue(publicLibraries.get(i).getLibraryName());

                dataRow.createCell(2).setCellValue(publicLibraries.get(i).getNameOfTheResponsible());

                dataRow.createCell(3).setCellValue(publicLibraries.get(i).getPartner());

                dataRow.createCell(4).setCellValue(publicLibraries.get(i).getLibraryStatus());

                dataRow.createCell(5).setCellValue(publicLibraries.get(i).getLibraryType());

                dataRow.createCell(6).setCellValue(publicLibraries.get(i).getCreationDate());

                dataRow.createCell(7).setCellValue(publicLibraries.get(i).getSpace());

                dataRow.createCell(8).setCellValue(publicLibraries.get(i).getEmployeesNumber());

                dataRow.createCell(9).setCellValue(publicLibraries.get(i).getOpeningTime());

                dataRow.createCell(10).setCellValue(publicLibraries.get(i).getAddress());

                dataRow.createCell(11).setCellValue(publicLibraries.get(i).getRegion());

                dataRow.createCell(12).setCellValue(publicLibraries.get(i).getProvincePrefecture());

                dataRow.createCell(13).setCellValue(publicLibraries.get(i).getOffice_Phone());

                dataRow.createCell(14).setCellValue(publicLibraries.get(i).getFax());

                dataRow.createCell(15).setCellValue(publicLibraries.get(i).getEmail());

                dataRow.createCell(16).setCellValue(publicLibraries.get(i).getWebsite());

                dataRow.createCell(17).setCellValue(publicLibraries.get(i).getInternetConnection());

                dataRow.createCell(18).setCellValue(publicLibraries.get(i).getUpdateDateDocumentaryFund());
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
