package ma.digency.gov.amc.service.card;

import ma.digency.gov.amc.dto.card.CardResponse;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountResponse;
import ma.digency.gov.amc.repository.entity.artistCard.Card;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ExcelService {
    void save(MultipartFile file);
    void saveCardExcelFile(MultipartFile file);
    List<ArtistAccount> importExcelData(InputStream is) throws IOException;
    List<Card> importArtistCardFile(InputStream inputStream) throws IOException;
    String getCellValue(Row row, int cellNo);
    List<ArtistAccountResponse> getAllArtists();
    ByteArrayInputStream exportArtistData(List<ArtistAccountResponse> artists);
    ByteArrayInputStream exportArtistCard(List<CardResponse> cards);


}
