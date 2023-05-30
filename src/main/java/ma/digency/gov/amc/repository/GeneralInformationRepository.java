package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.proposalproject.GeneralInformation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneralInformationRepository extends GenericRepository<GeneralInformation,Long>{
    Optional<GeneralInformation> findByRefGeneralInformation(String RefGeneralInformation);
}
