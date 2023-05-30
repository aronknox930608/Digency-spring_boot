package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class BookingStandRequest {

    @NotBlank
    private String volumeInCubicMeter;

    @NotBlank
    private String category;

    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String branchActivity;
}
