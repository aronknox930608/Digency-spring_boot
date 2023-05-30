package ma.digency.gov.amc.dto.shared;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
public class PlanningRequest {

    private LocalDate planningDate;

    @ApiModelProperty(example = "12:30")
    private String startedTime;

    private String endTime;

}
