package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.attributionsprix.ParticipantsTheater;
import ma.digency.gov.amc.repository.entity.attributionsprix.TheaterPiece;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ParticipantsTheaterService {

    ParticipantsTheater createOrUpdate(ParticipantsTheater participantsTheater);

    List<ParticipantsTheater> getListParticipants(TheaterPiece theaterPiece);

    ParticipantsTheater getParticipantByRef(String refParticipant);

    void deleteParticipant(ParticipantsTheater participantTheater);

    List<ParticipantsTheater> getParticipationByCin(String cin);

    Void save(MultipartFile file, String refTheaterPiece);

    List<ParticipantsTheater> importExcelData(InputStream is, String refTheaterPiece) throws IOException;

    String getCellValue(Row row, int cellNo);

    ByteArrayInputStream exportParticipantsData(List<ParticipantsTheater> participantsTheaters);

}
