package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.card.DemandPlanningRequest;
import ma.digency.gov.amc.dto.card.DemandPlanningResponse;
import ma.digency.gov.amc.repository.entity.artistCard.DemandPlanning;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true,
        withCustomFields = {
                @Field(value = {"startedTime", "endTime"}, withCustom = CustomDateMapper.class)
        })
public interface DemandPlanningMapper {
    DemandPlanning demandPlanningRequestToDemandPlanning(DemandPlanningRequest demandPlannigRequest);
    DemandPlanningResponse demandPlanningToDemandPlanningResponse(DemandPlanning demandPlanning);
    DemandPlanning updateDemandPlanningResponseToDemandPlanning(DemandPlanningResponse newDemand,DemandPlanning oldDemand);




}
