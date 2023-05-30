package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardBook;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Getter
@Setter
@Validated
public class DemandAwardBookListResponse {

    private String refDemand;

    private String status;

    private String comment;

    private Date decisionDate;

    private AwardBook bookAward;

    private ArtistAccount writer;

}
