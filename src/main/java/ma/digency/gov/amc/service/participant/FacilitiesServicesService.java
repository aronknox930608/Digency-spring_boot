package ma.digency.gov.amc.service.participant;


import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.participant.FacilitiesServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FacilitiesServicesService {

    FacilitiesServices findFacilitiesServicesByRefFacilitiesServices(String refFacilitiesServices);

    FacilitiesServices createOrUpdateFacilitiesServices(FacilitiesServices facilitiesServices);

    void deleteFacilitiesServices(String refFacilitiesServices);

    List<FacilitiesServices> findAllFacilitiesServices();


}
