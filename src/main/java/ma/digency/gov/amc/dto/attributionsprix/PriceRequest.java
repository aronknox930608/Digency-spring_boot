package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Getter
@Setter
@Validated
public class PriceRequest {

    private String title;

    private String description;

    private String price;

    private Date startDate;

    private Date closingDate;

    private Date deadlineFiling;

    private Date resultsAnnouncementDate;
}
