package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Validated
public class DemandAwardTheaterRequest {

    private String comment;

    private String representativeTheaterPiece;

    private String awardTheater;

    private TheaterPieceRequest theaterPieceRequest;

    private String refPersonConnected;

}
