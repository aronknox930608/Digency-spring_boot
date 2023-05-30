package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExhibitorsFullData {

    private ExhibitorResponse exhibitor;

    private BookingStandResponse bookingStand;

    private Integer nbrPublications;

    private Integer nbrActivitiesProposal;

    private Integer nbrPublishers;
}
