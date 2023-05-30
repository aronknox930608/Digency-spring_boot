package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.attributionsprix.DemandAwardSearchCriteria;
import ma.digency.gov.amc.repository.*;
import ma.digency.gov.amc.repository.SearchFiles.DemandPage;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.CandidateHonoraryAward;
import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptInformation;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class DemandAHServiceImpl implements DemandAHService {

    private final ReferenceSequenceService referenceSequenceService;

    private final DemandRepository demandRepository;

    private final DemandAwardHassan2CriteriaRepository demandAwardHassan2CriteriaRepository;

    private final DemandAwardBookCriteriaRepository demandAwardBookCriteriaRepository;

    private final DemandAwardHonoraryCriteriaRepository demandAwardHonoraryCriteriaRepository;

    private final DemandAwardTheaterCriteriaRepository demandAwardTheaterCriteriaRepository;

    private final ManuscriptInformationRepository manuscriptInformationRepository;

    @Override
    public Demand createOrUpdateDemand(Demand demand) {
        if (null == demand.getRefDemand()) {
            var ref = referenceSequenceService.generateRefDemand();
            demand.setRefDemand(ref);
        }

        return demandRepository.save(demand);
    }

    @Override
    public Demand findDemandByRef(String refDemand) {
        return demandRepository.findDemandByRefDemand(refDemand);
    }

    @Override
    public Demand update(Demand demand) {
        Demand demandFind = demandRepository.findDemandByRefDemand(demand.getRefDemand());
        demandFind.setRefDemand(demand.getRefDemand());
        demandFind.setStatus(demand.getStatus());
        demandFind.setComment(demand.getComment());
        demandFind.setDemandOwner(demand.getDemandOwner());
        demandFind.setAward(demand.getAward());
        demandFind.setCreationPar(demand.getCreationPar());
        demandFind.setDateCreation(demand.getDateCreation());
        demandFind.setDateModification(demand.getDateModification());
        demandFind.setDecision_date(demand.getDecision_date());
        demandFind.setHonoraryAward(demand.getHonoraryAward());
        return demandRepository.save(demandFind);
    }

    @Override
    public void deleteDemand(Demand demand) {
        demandRepository.delete(demand);
    }

    @Override
    public Page<Demand> findDemandPageableAwardHassan2(Pageable pageable) {
        return demandRepository.findDemandHassan2(pageable);
    }

    @Override
    public Page<Demand> findDemandPageableAwardHonorary(Pageable pageable) {
        return demandRepository.findDemandAwardHonorary(pageable);
    }

    @Override
    public Page<Demand> findDemandPageableAwardBook(Pageable pageable) {
        return demandRepository.findDemandAwardBook(pageable);
    }

    @Override
    public Page<Demand> findDemandPageableAwardTheater(Pageable pageable) {
        return demandRepository.findDemandAwardTheater(pageable);
    }

    @Override
    public Demand findDemandByRefDemand(String refDemand) {
        return demandRepository.findDemandByRefDemand(refDemand);
    }

    @Override
    public List<Demand> findDemandByWriter(ArtistAccount artistAccount) {
        return demandRepository.findDemandByWriter(artistAccount);}

    @Override
    public List<Demand> findDemandByOwner(OwnerHandWritten ownerHandWritten) {
        return demandRepository.findDemandByDemandOwner(ownerHandWritten);
    }

    @Override
    public List<Demand> findDemandByCandidate(ArtistAccount candidateHonoraryAward) {
        return demandRepository.findDemandByCandidate(candidateHonoraryAward);
    }

    @Override
    public Page<Demand> getDemandsAwardHassan2(DemandPage demandPage, DemandAwardSearchCriteria demandAwardHassan2SearchCriteria) {
        return demandAwardHassan2CriteriaRepository.findAllWithFilters(demandPage,demandAwardHassan2SearchCriteria);
    }

    @Override
    public Page<Demand> getDemandsAwardBook(DemandPage demandPage, DemandAwardSearchCriteria demandAwardBookSearchCriteria) {
        return demandAwardBookCriteriaRepository.findAllWithFilters(demandPage,demandAwardBookSearchCriteria);
    }

    @Override
    public Page<Demand> getDemandsAwardHonorary(DemandPage demandPage, DemandAwardSearchCriteria demandAwardHonorarySearchCriteria) {
        return demandAwardHonoraryCriteriaRepository.findAllWithFilters(demandPage,demandAwardHonorarySearchCriteria);
    }

    @Override
    public Page<Demand> getDemandsAwardTheater(DemandPage demandPage, DemandAwardSearchCriteria demandAwardTheaterSearchCriteria) {
        return demandAwardTheaterCriteriaRepository.findAllWithFilters(demandPage,demandAwardTheaterSearchCriteria);
    }

    @Override
    public List<Demand> findDemandByPersonConnected(Account account) {
        return demandRepository.findDemandByPersonConnected(account);
    }

    @Override
    public List<Demand> getAllDemand() {
        return demandRepository.findAllDemandHassan2();
    }

    @Override
    public ByteArrayInputStream exportDemandAwardHonoraryData(List<Demand> demands) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet=workbook.createSheet("demands");
            Row row = sheet.createRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            String[] Header={"refDemand","owner firstname", "owner lastname","owner cin","award","amount","stats","demand date","decision date"};
            for(int i=0;i<Header.length;i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
                for (int i = 0; i < demands.size(); i++) {
                    Row dataRow = sheet.createRow(i + 1);
                    dataRow.createCell(0).setCellValue(demands.get(i).getRefDemand());
                    dataRow.createCell(1).setCellValue(demands.get(i).getCandidate().getFirstName());
                    dataRow.createCell(2).setCellValue(demands.get(i).getCandidate().getLastName());
                    dataRow.createCell(3).setCellValue(demands.get(i).getCandidate().getCin());
                    dataRow.createCell(5).setCellValue(demands.get(i).getHonoraryAward().getType());
                    dataRow.createCell(6).setCellValue(demands.get(i).getHonoraryAward().getAmount());
                    dataRow.createCell(7).setCellValue(demands.get(i).getStatus());
                    dataRow.createCell(8).setCellValue(demands.get(i).getDateCreation().toLocalDateTime());
                    dataRow.createCell(9).setCellValue(demands.get(i).getDecision_date());
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
    public ByteArrayInputStream exportDemandAwardBookData(List<Demand> demands) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet=workbook.createSheet("demands");
            Row row = sheet.createRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            String[] Header={"refDemand","artist firstname", "artist lastname","artist cin","award","amount","stats","demand date","decision date"};
            for(int i=0;i<Header.length;i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
            for (int i = 0; i < demands.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(demands.get(i).getRefDemand());
                dataRow.createCell(1).setCellValue(demands.get(i).getWriter().getFirstName());
                dataRow.createCell(2).setCellValue(demands.get(i).getWriter().getLastName());
                dataRow.createCell(3).setCellValue(demands.get(i).getWriter().getCin());
                dataRow.createCell(5).setCellValue(demands.get(i).getBookAward().getType());
                dataRow.createCell(6).setCellValue(demands.get(i).getBookAward().getAmount());
                dataRow.createCell(7).setCellValue(demands.get(i).getStatus());
                dataRow.createCell(8).setCellValue(demands.get(i).getDateCreation().toLocalDateTime());
                dataRow.createCell(9).setCellValue(demands.get(i).getDecision_date());
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
    public ByteArrayInputStream exportDemandAwardTheaterData(List<Demand> demands) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet=workbook.createSheet("demands");
            Row row = sheet.createRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            String[] Header={"refDemand","artist firstname", "artist lastname","artist cin","award","amount","title",
                    "theaterTroupeName","textTheaterPiece","pieceDate","stats","demand date","decision date"};
            for(int i=0;i<Header.length;i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
            for (int i = 0; i < demands.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(demands.get(i).getRefDemand());
                dataRow.createCell(1).setCellValue(demands.get(i).getRepresentativeTheaterPiece().getFirstName());
                dataRow.createCell(2).setCellValue(demands.get(i).getRepresentativeTheaterPiece().getLastName());
                dataRow.createCell(3).setCellValue(demands.get(i).getRepresentativeTheaterPiece().getCin());
                dataRow.createCell(5).setCellValue(demands.get(i).getAwardTheater().getType());
                dataRow.createCell(6).setCellValue(demands.get(i).getAwardTheater().getAmount());
                dataRow.createCell(7).setCellValue(demands.get(i).getTheaterPiece().getTitle());
                dataRow.createCell(8).setCellValue(demands.get(i).getTheaterPiece().getTheaterTroupeName());
                dataRow.createCell(9).setCellValue(demands.get(i).getTheaterPiece().getTextTheaterPiece());
                dataRow.createCell(8).setCellValue(demands.get(i).getTheaterPiece().getDate());
                dataRow.createCell(9).setCellValue(demands.get(i).getStatus());
                dataRow.createCell(9).setCellValue(demands.get(i).getDecision_date());
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
    public ByteArrayInputStream exportDemandAwardHassan2Data(List<Demand> demands) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet=workbook.createSheet("demands");
            Row row = sheet.createRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            String[] Header={"refDemand","artist firstname", "artist lastname","artist cin","manuscript title","award","amount","stats","demand date","decision date"};
            for(int i=0;i<Header.length;i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
            for (int i = 0; i < demands.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(demands.get(i).getRefDemand());
                dataRow.createCell(1).setCellValue(demands.get(i).getDemandOwner().getFirstName());
                dataRow.createCell(2).setCellValue(demands.get(i).getDemandOwner().getLastName());
                dataRow.createCell(3).setCellValue(demands.get(i).getDemandOwner().getCin());
                ManuscriptInformation manuscriptInformation=manuscriptInformationRepository.findManuscriptInformationByDemand(demands.get(i));
                dataRow.createCell(4).setCellValue(manuscriptInformation.getManuscriptTitle());
                dataRow.createCell(5).setCellValue(demands.get(i).getAward().getType());
                dataRow.createCell(6).setCellValue(demands.get(i).getAward().getAmount());
                dataRow.createCell(7).setCellValue(demands.get(i).getStatus());
                dataRow.createCell(8).setCellValue(demands.get(i).getDateCreation().toLocalDateTime());
                dataRow.createCell(9).setCellValue(demands.get(i).getDecision_date());
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
    public List<Demand> demandsAwardHassan2() {
        return demandRepository.demandsAwardHassan2();
    }

    @Override
    public List<Demand> demandsAwardHonorary() {
        return demandRepository.demandsAwardHonorary();
    }

    @Override
    public List<Demand> demandsAwardBook() {
        return demandRepository.demandsAwardBook();
    }

    @Override
    public List<Demand> demandsAwardTheater() {
        return demandRepository.demandsAwardTheater();
    }
}














