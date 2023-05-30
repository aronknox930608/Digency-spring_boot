package ma.digency.gov.amc.service;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.participant.DistributorResponse;
import ma.digency.gov.amc.dto.participant.PrinterResponse;
import ma.digency.gov.amc.mapper.ParticipantMapper;
import ma.digency.gov.amc.mapper.PrinterMapper;
import ma.digency.gov.amc.repository.DistributorRepository;
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
public class ExcelDistributorServiceImpl implements ExcelDistributorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DistributorRepository distributorRepository;

    private final ReferenceSequenceService referenceSequenceService;

    private final ParticipantMapper participantMapper;

    String[] Header={"refDistributor","businessName","lineOfBusiness","ownerName","creationDate","socialCapital","businessRegisterNumber",

            "commonCompanyIdentifier","cnssNumber","space","Address","city","phoneNumber","faxNumber","email","website","permanentEmployeesNumber",

            "temporaryEmployeesNumber","numberPointSaleUrban","numberPointSaleRural","averageSharesDistributedAnnually","averageNumberContactsAnnuallyWithPublishers","overseasDistribution",
            "costOfDistributionComparedRetailPrice"
           };

    @Override
    public List<DistributorResponse> getAllDistributors() {
        List<DistributorResponse> distributors=new ArrayList<>();


        distributorRepository.findAll().forEach(find-> distributors.add(participantMapper.distributorToDistributorResponse(find)));

        return distributors;
    }

    @Override
    public ByteArrayInputStream exportDistributorsData(List<DistributorResponse> distributors) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet = workbook.createSheet("distributors");
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

            for (int i = 0; i < distributors.size(); i++) {

                Row dataRow = sheet.createRow(i + 1);

                dataRow.createCell(0).setCellValue(distributors.get(i).getRefDistributor());

                dataRow.createCell(1).setCellValue(distributors.get(i).getBusinessName());

                dataRow.createCell(2).setCellValue(distributors.get(i).getLineOfBusiness());

                dataRow.createCell(3).setCellValue(distributors.get(i).getOwnerName());

                dataRow.createCell(4).setCellValue(distributors.get(i).getAddress());

                dataRow.createCell(5).setCellValue(distributors.get(i).getCreationDate());

                dataRow.createCell(6).setCellValue(distributors.get(i).getBusinessRegisterNumber());

                dataRow.createCell(7).setCellValue(distributors.get(i).getCommonCompanyIdentifier());

                dataRow.createCell(8).setCellValue(distributors.get(i).getCnssNumber());

                dataRow.createCell(9).setCellValue(distributors.get(i).getSpace());

                dataRow.createCell(10).setCellValue(distributors.get(i).getAddress());

                dataRow.createCell(11).setCellValue(distributors.get(i).getCity());

                dataRow.createCell(12).setCellValue(distributors.get(i).getPhoneNumber());

                dataRow.createCell(13).setCellValue(distributors.get(i).getFaxNumber());

                dataRow.createCell(14).setCellValue(distributors.get(i).getEmail());

                dataRow.createCell(15).setCellValue(distributors.get(i).getWebsite());

                dataRow.createCell(16).setCellValue(distributors.get(i).getPermanentEmployeesNumber());

                dataRow.createCell(17).setCellValue(distributors.get(i).getTemporaryEmployeesNumber());

                dataRow.createCell(18).setCellValue(distributors.get(i).getNumberPointSaleUrban());

                dataRow.createCell(19).setCellValue(distributors.get(i).getNumberPointSaleRural());

                dataRow.createCell(20).setCellValue(distributors.get(i).getAverageSharesDistributedAnnually());

                dataRow.createCell(21).setCellValue(distributors.get(i).getAverageNumberContactsAnnuallyWithPublishers());

                dataRow.createCell(22).setCellValue(distributors.get(i).getOverseasDistribution());

                dataRow.createCell(23).setCellValue(distributors.get(i).getCostOfDistributionComparedRetailPrice());
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