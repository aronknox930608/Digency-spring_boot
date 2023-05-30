package ma.digency.gov.amc.repository.entity.artistCard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DemandCard extends AcmAbstractAuditEntity  {

    @Column(name="REF_DEMAND_CARD")
    private String refDemandCard;
    private String status;
    private LocalDate dateDecision;
    private LocalDate dateDemand;
    private String comment;
    private String demandType;
    private String cardType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_artist_account", referencedColumnName = "ref_artist_account")
    private ArtistAccount artistAccount;

    @Column(name="refArtistCard")
    private String refArtistCard;

}
