package ma.digency.gov.amc.service.publiclibrary;


import ma.digency.gov.amc.repository.entity.publiclibrary.Personnel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PersonnelService {

    Personnel findPersonnelByRefPersonnel(String refPersonnel);

    Personnel createOrUpdatePersonnel(Personnel personnel);

    void deletePersonnel(String refPersonnel);

    List<Personnel> findAllPersonnel();


}
