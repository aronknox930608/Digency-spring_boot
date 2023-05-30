package ma.digency.gov.amc.dto.card;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class DemandCardResponse {
    private String refDemandCard;
    private String status;
    private LocalDate dateDemand;
    private String comment;
    private String cardType;
    private ArtistAccountCardResponse artistAccount;
    private String refArtistCard;

}
