package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.ParticipantsTheaterRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.ParticipantsTheater;
import ma.digency.gov.amc.repository.entity.attributionsprix.RoleTheater;
import ma.digency.gov.amc.repository.entity.attributionsprix.TheaterPiece;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.commons.collections4.IterableUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class ParticipantsTheaterServiceImpl implements ParticipantsTheaterService{

    private final ParticipantsTheaterRepository participantsTheaterRepository;

    private final ReferenceSequenceService referenceSequenceService;

    private final RoleTheaterService roleTheaterService;

    private final TheaterPieceService theaterPieceService;

    @Override
    public ParticipantsTheater createOrUpdate(ParticipantsTheater participantsTheater) {
        if(null==participantsTheater.getRefParticipant()){
            var ref=referenceSequenceService.generateRefParticipantsTheater();
            participantsTheater.setRefParticipant(ref);
        }
        return participantsTheaterRepository.save(participantsTheater);
    }

    @Override
    public List<ParticipantsTheater> getListParticipants(TheaterPiece theaterPiece) {
        return participantsTheaterRepository.findParticipantsTheaterByTheaterPiece(theaterPiece);
    }

    @Override
    public ParticipantsTheater getParticipantByRef(String refParticipant) {
        return participantsTheaterRepository.findParticipantsTheaterByRefParticipant(refParticipant);
    }

    @Override
    public void deleteParticipant(ParticipantsTheater participantTheater) {
        participantsTheaterRepository.delete(participantTheater);
    }

    @Override
    public List<ParticipantsTheater> getParticipationByCin(String cin) {
        return participantsTheaterRepository.findParticipantsTheaterByCin(cin);
    }

    @Override
    public Void save(MultipartFile file, String refTheaterPiece) {
        try{
            List<ParticipantsTheater> participantsTheaters=importExcelData(file.getInputStream(),refTheaterPiece);
            for(ParticipantsTheater participantsTheater:participantsTheaters){
                participantsTheater.setRefParticipant(referenceSequenceService.generateRefParticipantsTheater());
                participantsTheaterRepository.save(participantsTheater);
            }}
        catch(IOException e){
            throw  new RuntimeException("fail to store excel data:" + e.getMessage());
        }

        return null;
    }

    @Override
    public List<ParticipantsTheater> importExcelData(InputStream is, String refTheaterPiece) throws IOException {
        List<ParticipantsTheater> participantsTheaters=new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet){
            if(IterableUtils.size(row)<=1){
                break;
            }
            if (row.getRowNum() != 0) {
                ParticipantsTheater participantsTheater =new ParticipantsTheater();
                participantsTheater.setLastName(getCellValue(row,0));
                participantsTheater.setFirstName(getCellValue(row,1));
                participantsTheater.setCin(getCellValue(row,2));
                if(getCellValue(row,3)!=null){
                    participantsTheater.setRole1(roleTheaterService.findRoleTheaterByName(getCellValue(row,3)));
                }
                if(getCellValue(row,4)!=null){
                    participantsTheater.setRole1(roleTheaterService.findRoleTheaterByName(getCellValue(row,4)));
                }
                if(getCellValue(row,5)!=null){
                    participantsTheater.setRole1(roleTheaterService.findRoleTheaterByName(getCellValue(row,5)));
                }
                participantsTheater.setPersonalityName(getCellValue(row,6));
                participantsTheater.setTheaterPiece(theaterPieceService.findTheaterPieceByRef(refTheaterPiece));
                participantsTheaters.add(participantsTheater);
            }
        }
        return participantsTheaters;
    }

    @Override
    public String getCellValue(Row row, int cellNo) {
        DataFormatter formatter=new DataFormatter();
        Cell cell= (Cell) row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    @Override
    public ByteArrayInputStream exportParticipantsData(List<ParticipantsTheater> participantsTheaters) {
            return null;
    }

}


