package ma.digency.gov.amc.service.card;

import ma.digency.gov.amc.repository.entity.artistCard.DemandPlanning;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.searching.DemandPlanningCardCriteria;
import ma.digency.gov.amc.utils.DemandPlanningPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DemandPlanningService {
    DemandPlanning createOrUpdateDemandPlanning(DemandPlanning demandPlanning);
    void deleteDemandPlanning(String refDemandPlanning);
    DemandPlanning getByDemandPlanningRef(String refDemandPlanning);
    Page<DemandPlanning> getAllPlannings(Pageable pageable);
    Page<DemandPlanning> getDemandPlanningBySearchCriteria(DemandPlanningPage demandPlanningPage, DemandPlanningCardCriteria demandPlanningCardCriteria);
    DemandPlanning findDemandPlanningByStatus(StatusEnum open);


}
