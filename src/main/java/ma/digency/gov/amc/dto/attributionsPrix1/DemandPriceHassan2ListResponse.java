package ma.digency.gov.amc.dto.attributionsPrix1;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.AccountResponse;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.TheaterPiece;
import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Validated
public class DemandPriceHassan2ListResponse {

    private String refDemand;

    private String status;

    private Date decision_date;

    private String comment;

    private String key;

    private AccountResponse accountOwner;

    private AwardCategoriesResponse awardCategories;

}
