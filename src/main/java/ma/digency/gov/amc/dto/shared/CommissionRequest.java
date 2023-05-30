package ma.digency.gov.amc.dto.shared;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.CommissionTypeEnum;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class CommissionRequest {

    private CommissionTypeEnum commissionType;

    private LocalDate startedDate;

    private LocalDate endDate;

    private List<PlanningRequest> plannings;
}
