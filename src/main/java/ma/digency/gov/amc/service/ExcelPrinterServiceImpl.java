package ma.digency.gov.amc.service;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.library.AuthorResponse;
import ma.digency.gov.amc.dto.participant.PrinterResponse;
import ma.digency.gov.amc.mapper.LibraryMapper;
import ma.digency.gov.amc.mapper.ParticipantMapper;
import ma.digency.gov.amc.mapper.PrinterMapper;
import ma.digency.gov.amc.repository.AuthorRepository;
import ma.digency.gov.amc.repository.PrinterRepository;
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
public class ExcelPrinterServiceImpl implements ExcelPrinterService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PrinterRepository printerRepository;

    private final ReferenceSequenceService referenceSequenceService;

    private final PrinterMapper printerMapper;

    String[] Header={"refPrinter","businessName","lineOfBusiness","ownerName","creationDate","businessRegisterNumber",

            "commonCompanyIdentifier","space","Address","city","phoneNumber","faxNumber","email","website","permanentEmployeesNumber"

            ,"averageBooksPrintedAnnually","averageOrdersAnnuallyRequestPublishers","membershipAssociationOrSyndicate"
           };


    @Override
    public List<PrinterResponse> getAllPrinters() {
        List<PrinterResponse> printers=new ArrayList<>();


        printerRepository.findAll().forEach(find-> printers.add(printerMapper.printerToPrinterResponse(find)));

        return printers;
    }

    @Override
    public ByteArrayInputStream exportPrinterData(List<PrinterResponse> printers) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet = workbook.createSheet("printers");
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

            for (int i = 0; i < printers.size(); i++) {

                Row dataRow = sheet.createRow(i + 1);

                dataRow.createCell(0).setCellValue(printers.get(i).getRefPrinter());

                dataRow.createCell(1).setCellValue(printers.get(i).getBusinessName());

                dataRow.createCell(2).setCellValue(printers.get(i).getLineOfBusiness());

                dataRow.createCell(3).setCellValue(printers.get(i).getOwnerName());

                dataRow.createCell(4).setCellValue(printers.get(i).getAddress());

                dataRow.createCell(5).setCellValue(printers.get(i).getCreationDate());

                dataRow.createCell(6).setCellValue(printers.get(i).getBusinessRegisterNumber());

                dataRow.createCell(7).setCellValue(printers.get(i).getCommonCompanyIdentifier());

                dataRow.createCell(8).setCellValue(printers.get(i).getSpace());

                dataRow.createCell(9).setCellValue(printers.get(i).getAddress());

                dataRow.createCell(10).setCellValue(printers.get(i).getCity());

                dataRow.createCell(11).setCellValue(printers.get(i).getPhoneNumber());

                dataRow.createCell(12).setCellValue(printers.get(i).getFaxNumber());

                dataRow.createCell(13).setCellValue(printers.get(i).getEmail());

                dataRow.createCell(14).setCellValue(printers.get(i).getWebsite());

                dataRow.createCell(15).setCellValue(printers.get(i).getPermanentEmployeesNumber());

                dataRow.createCell(16).setCellValue(printers.get(i).getAverageBooksPrintedAnnually());

                dataRow.createCell(17).setCellValue(printers.get(i).getAverageOrdersAnnuallyRequestPublishers());

                dataRow.createCell(18).setCellValue(printers.get(i).getMembershipAssociationOrSyndicate());
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
