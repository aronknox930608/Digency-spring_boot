package ma.digency.gov.amc.service.participant;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.DistributorRepository;
import ma.digency.gov.amc.repository.FacilitiesServicesRepository;
import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.participant.FacilitiesServices;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilitiesServicesServiceImpl implements FacilitiesServicesService {

    private final FacilitiesServicesRepository facilitiesServicesRepository;
    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public FacilitiesServices findFacilitiesServicesByRefFacilitiesServices(String refFacilitiesServices) {
        return facilitiesServicesRepository.findFacilitiesServicesByRefFacilitiesServices(refFacilitiesServices)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public FacilitiesServices createOrUpdateFacilitiesServices(FacilitiesServices facilitiesServices) {
        if (null == facilitiesServices.getRefFacilitiesServices()) {
            var ref = referenceSequenceService.generateRefFacilitiesServices();
            facilitiesServices.setRefFacilitiesServices(ref);
        }
        return facilitiesServicesRepository.save(facilitiesServices);
    }

    @Override
    public void deleteFacilitiesServices(String refFacilitiesServices) {
        FacilitiesServices facilitiesServices= findFacilitiesServicesByRefFacilitiesServices(refFacilitiesServices);
        facilitiesServicesRepository.delete(facilitiesServices);
    }

    @Override
    public List<FacilitiesServices> findAllFacilitiesServices() {
        return facilitiesServicesRepository.findAll();
    }


}
