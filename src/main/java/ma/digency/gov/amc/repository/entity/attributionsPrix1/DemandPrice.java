package ma.digency.gov.amc.repository.entity.attributionsPrix1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import ma.digency.gov.amc.repository.entity.attributionsprix.TheaterPiece;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DemandPrice extends AcmAbstractAuditEntity {

    @Column(name = "ref_demand")
    private String refDemand;

    private String status;

    private Date decision_date;

    private String comment;

    private String key;

    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name = "ref_artist_account", referencedColumnName = "ref_artist_account")
    private ArtistAccount accountOwner;

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_award_category", referencedColumnName = "ref_award_category")
    private AwardCategories awardCategories;

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="ref_theater_piece" , referencedColumnName = "ref_theater_piece")
    private TheaterPiece theaterPiece;


    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="ref_book" , referencedColumnName = "ref_book")
    private BookPrice bookPrice;
}
