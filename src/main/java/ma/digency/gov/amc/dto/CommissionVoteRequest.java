package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

@Getter
@Setter
public class CommissionVoteRequest {

    private String refPublication;

    private Integer nbrAccepted;

    private Integer nbrDenied;

    private String deniedReason;

    private StatusEnum status;

}
