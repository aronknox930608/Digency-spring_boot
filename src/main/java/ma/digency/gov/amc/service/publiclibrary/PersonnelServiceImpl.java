package ma.digency.gov.amc.service.publiclibrary;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.PersonnelRepository;
import ma.digency.gov.amc.repository.PublicLibraryRepository;
import ma.digency.gov.amc.repository.entity.publiclibrary.Personnel;
import ma.digency.gov.amc.repository.entity.publiclibrary.PublicLibrary;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonnelServiceImpl implements PersonnelService {

    private final PersonnelRepository personnelRepository;
    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public Personnel findPersonnelByRefPersonnel(String refPersonnel) {
        return personnelRepository.findPersonnelByRefPersonnel(refPersonnel)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public Personnel createOrUpdatePersonnel(Personnel personnel) {
        if (null == personnel.getRefPersonnel()) {
             var ref = referenceSequenceService.generateRefPersonnel();
             personnel.setRefPersonnel(ref);
        }
        return personnelRepository.save(personnel);
    }



    @Override
    public void deletePersonnel(String refPersonnel) {
        Personnel personnel = findPersonnelByRefPersonnel(refPersonnel);
        personnelRepository.delete(personnel);
    }

    @Override
    public List<Personnel> findAllPersonnel() {
        return personnelRepository.findAll();
    }
}
