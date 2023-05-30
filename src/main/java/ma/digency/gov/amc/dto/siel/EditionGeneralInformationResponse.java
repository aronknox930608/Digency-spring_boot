package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.PageableResponse;

@Getter
@Setter
public class EditionGeneralInformationResponse {

    private EditionResponse edition;

    private PageableResponse<PublicationResponse> publications;

    private BookingStandResponse bookingStand;
}
