package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardObtained;
import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface AwardObtainedService {

    List<AwardObtained> findAwardObtainedByRefArtist(String refArtist);

    AwardObtained createOrUpdateAwardObtained(AwardObtained awardObtained);

    AwardObtained findAwardObtainedByRef(String refAwardObtained);

    void deleteAwardObtained(String refAwardObtained);

    Void save(MultipartFile file);

    List<AwardObtained> importExcelData(InputStream is) throws IOException;

    String getCellValue(Row row, int cellNo);

    List<AwardObtained> getAllAwardObtained();

    ByteArrayInputStream exportAwardObtainedData(List<AwardObtained> awardObtaineds);
}
