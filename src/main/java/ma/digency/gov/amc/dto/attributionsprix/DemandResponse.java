package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardHassan2;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Getter
@Setter
@Validated
public class DemandResponse {

    private String refDemand;

    private String status;

    private String comment;

    private Date decision_date;

    private OwnerHandWritten demandOwner  ;

    private String award;

    private ManuscriptInformationResponse manuscriptInformation;

}
