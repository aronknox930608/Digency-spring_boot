package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptInformation;
import org.springframework.stereotype.Repository;

@Repository

public interface ManuscriptInformationRepository extends GenericRepository<ManuscriptInformation, Long> {

    ManuscriptInformation findManuscriptInformationByRefManuscriptInformation(String refManuscriptInformation);

    ManuscriptInformation findManuscriptInformationByDemand(Demand demand);

    ManuscriptInformation findManuscriptInformationByDemand(DemandPrice demandPrice);



}
