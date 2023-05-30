package ma.digency.gov.amc.repository.entity.artistCard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfessionDomain;
import ma.digency.gov.amc.utils.enumeration.IdentityType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Card extends AcmAbstractAuditEntity {

    @Column(name="REF_CARD")
    private String refCard;
    private int numCard;
    private LocalDate dateCardCreation;
    private LocalDate dateCardValidation;
    private String cardType;
    private String status;
    private String refArtistAccount;


    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "REF_CARD", referencedColumnName = "REF_CARD")
    private List<DemandCard> demandCards;


}
