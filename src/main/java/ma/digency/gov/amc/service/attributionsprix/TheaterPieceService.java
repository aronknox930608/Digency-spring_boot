package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.TheaterPiece;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface TheaterPieceService {

    TheaterPiece createOrUpdate(TheaterPiece theaterPiece);

    TheaterPiece findTheaterPieceByRef(String refTheaterPiece);

    Void save(MultipartFile file);

    List<TheaterPiece> importExcelData(InputStream is) throws IOException;

    String getCellValue(Row row, int cellNo);

    List<TheaterPiece> getAllTheaterPiece();

    ByteArrayInputStream exportTheaterPircesData(List<TheaterPiece> theaterPieces);

}
