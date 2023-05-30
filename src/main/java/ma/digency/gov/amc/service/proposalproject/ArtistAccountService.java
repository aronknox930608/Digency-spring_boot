package ma.digency.gov.amc.service.proposalproject;

import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface ArtistAccountService {

    ArtistAccount createNewArtistAccount(ArtistAccount artistAccount);
    ArtistAccount updateArtistAccount(ArtistAccount artistAccount);
    void deleteArtistAccount(ArtistAccount artistAccount);
    List<ArtistAccount> findAllArtistAccounts();
    Optional<ArtistAccount> findArtistAccountById(Long id);
    ArtistAccount  findArtistAccountByEmail(String email);
    Optional<ArtistAccount> findArtistAccountByRef(String ref);
    ArtistAccount findArtistAccountByRefArtist(String ref);
    Page<ArtistAccount> findAllArtistAccountsPageable(Pageable pageable);
    Optional<ArtistAccount> updateArtistAccountStatus(ArtistAccount artistAccount, StatusEnum status);
    Optional<ArtistAccount> findArtistAccountByAccount(Account account);
    ArtistAccount findArtistByCin(String cin);
    Optional<ArtistAccount> findArtistByRef(String ref);
    Void save(MultipartFile file);
    List<ArtistAccount> importExcelData(InputStream is) throws IOException;
    String getCellValue(Row row, int cellNo);
    ByteArrayInputStream exportArtistsData(List<ArtistAccount> artistAccounts);

    Page<ArtistAccount> findAllByAccountAndStatusNot(Account account,StatusEnum status,Pageable pageable);
}
