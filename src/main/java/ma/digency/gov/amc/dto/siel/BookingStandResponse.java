package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class BookingStandResponse {

    private String refBookingStand;

    @NotEmptyString
    private String volumeInCubicMeter;

    @NotEmptyString
    private String category;

    @NotEmptyString
    private String paymentMethod;

    @NotEmptyString
    private String branchActivity;

    private StatusEnum status;

}
