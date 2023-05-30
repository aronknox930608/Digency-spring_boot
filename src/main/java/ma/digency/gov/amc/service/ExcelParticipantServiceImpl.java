package ma.digency.gov.amc.service;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.library.AuthorResponse;
import ma.digency.gov.amc.mapper.LibraryMapper;
import ma.digency.gov.amc.repository.AuthorRepository;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.util.List;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ExcelParticipantServiceImpl implements ExcelParticipantService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final AuthorRepository authorRepository;

    private final ReferenceSequenceService referenceSequenceService;

    private final LibraryMapper libraryMapper;

    String[] Header={"Ref_Author","fullName","gender","dateOfDeath","address","countryOfResidence","city",

            "phoneNumber","fax","email","webPage","biography","writingLanguage","areasOfWriting","publishedBooks","publishedArticles"
           };




    @Override
    public List<AuthorResponse> getAllAuthors() {
        List<AuthorResponse> authors=new ArrayList<>();


        authorRepository.findAll().forEach(find-> authors.add(libraryMapper.authorToAuthorResponse(find)));

        return authors;
    }

    @Override
    public ByteArrayInputStream exportAuthorData(List<AuthorResponse> authors) {
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

            for (int i = 0; i < authors.size(); i++) {

                Row dataRow = sheet.createRow(i + 1);

                dataRow.createCell(0).setCellValue(authors.get(i).getRefAuthor());

                dataRow.createCell(1).setCellValue(authors.get(i).getFullName());

                dataRow.createCell(2).setCellValue(authors.get(i).getGender());

                dataRow.createCell(3).setCellValue(authors.get(i).getDateOfDeath());

                dataRow.createCell(4).setCellValue(authors.get(i).getAddress());

                dataRow.createCell(5).setCellValue(authors.get(i).getCountryOfResidence());

                dataRow.createCell(6).setCellValue(authors.get(i).getCity());

                dataRow.createCell(7).setCellValue(authors.get(i).getPhoneNumber());

                dataRow.createCell(8).setCellValue(authors.get(i).getFax());

                dataRow.createCell(9).setCellValue(authors.get(i).getEmail());

                dataRow.createCell(10).setCellValue(authors.get(i).getWebPage());

                dataRow.createCell(11).setCellValue(authors.get(i).getBiography());

                dataRow.createCell(12).setCellValue(authors.get(i).getWritingLanguage());

                dataRow.createCell(13).setCellValue(authors.get(i).getAreasOfWriting());

                dataRow.createCell(14).setCellValue(authors.get(i).getPublishedBooks());

                dataRow.createCell(15).setCellValue(authors.get(i).getPublishedArticles());


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
