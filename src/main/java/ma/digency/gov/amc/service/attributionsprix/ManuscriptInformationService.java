package ma.digency.gov.amc.service.attributionsprix;


import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptInformation;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ManuscriptInformationService {

    ManuscriptInformation createOrUpdatemanuscript(ManuscriptInformation manuscriptInformation);

    ManuscriptInformation update(ManuscriptInformation manuscriptInformation);

    void delete(ManuscriptInformation manuscriptInformation);

    Void save(MultipartFile file);

    List<ManuscriptInformation> importExcelData(InputStream is) throws IOException;

    String getCellValue(Row row, int cellNo);

    List<ManuscriptInformation> getAllManuscriptInformation();

    ByteArrayInputStream exportManuscriptData(List<ManuscriptInformation> manuscriptInformations);

    ManuscriptInformation findManuscriptInformationByDemand(DemandPrice demandPrice);

    ManuscriptInformation findManuscriptInformationByRef(String ref);

}

