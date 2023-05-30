package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface BookPriceService {

    BookPrice createOrUpdateBook(BookPrice book);

    List<BookPrice> findBookByAuthor(String refAuthor);

    BookPrice findBookByDemand(String refDemand);

    void delete(BookPrice book);

    BookPrice findBookByRef(String refBook);

    void deleteBook(String refBook);

    Void save(MultipartFile file);

    List<BookPrice> importExcelData(InputStream is) throws IOException;

    String getCellValue(Row row, int cellNo);

    List<BookPrice> getAllBookPrice();

    ByteArrayInputStream exportBookPriceData(List<BookPrice> books);

}
