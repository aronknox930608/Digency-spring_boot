package ma.digency.gov.amc.dto.card;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class DemandPlanningResponse {
    private String refDemandPlanning;
    private LocalDate startedDate;
    private LocalDate endDate;
    private String startedTime;
    private String endTime;

}
