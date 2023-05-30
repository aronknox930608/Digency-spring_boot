package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.participant.FacilitiesServices;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilitiesServicesRepository extends GenericRepository<FacilitiesServices, Long> {

    Optional<FacilitiesServices> findFacilitiesServicesByRefFacilitiesServices(String refFacilitiesServices);

}
