package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardHonorary;
import ma.digency.gov.amc.repository.entity.attributionsprix.CandidateHonoraryAward;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.validation.annotation.Validated;

import java.util.Date;


@Getter
@Setter
@Validated

public class DemandHonoraryAwardToListResponse {

    private String refDemand;

    private String status;

    private String comment;

    private Date decisionDate;

    private AwardHonorary honoraryAward;

    private CandidateHonoraryAward candidate;
}
