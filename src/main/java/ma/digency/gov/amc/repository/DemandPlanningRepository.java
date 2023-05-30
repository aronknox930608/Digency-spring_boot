package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.artistCard.DemandPlanning;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandPlanningRepository extends GenericRepository<DemandPlanning,Long>{
    DemandPlanning findByRefDemandPlanning(String refDemandPlanning);
    DemandPlanning findByStatus(StatusEnum status);

}
