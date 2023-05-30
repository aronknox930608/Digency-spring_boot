package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.dto.attributionsprix.OwnerHandWrittenSearchCriteria;
import ma.digency.gov.amc.repository.SearchFiles.OwnerHandWrittenPage;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface OwnerHandWrittenService {

    OwnerHandWritten createOrUpdateownerHW(OwnerHandWritten ownerHandWritten);

    OwnerHandWritten update(OwnerHandWritten ownerHandWritten);

    OwnerHandWritten findOwnerHandWrittenByRef(String refOwnerHandWritten);

    Page<OwnerHandWritten> getOwnerHandWrittens(OwnerHandWrittenPage ownerHandWrittenPage, OwnerHandWrittenSearchCriteria ownerHandWrittenSearchCriteria);

    Void save(MultipartFile file);

    List<OwnerHandWritten> importExcelData(InputStream is) throws IOException;

    String getCellValue(Row row, int cellNo);

    List<OwnerHandWritten> getAllOwners();

    ByteArrayInputStream exportArtistData(List<OwnerHandWritten> artists);
}
