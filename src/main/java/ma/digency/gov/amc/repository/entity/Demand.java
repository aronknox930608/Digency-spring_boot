package ma.digency.gov.amc.repository.entity;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Demand extends AcmAbstractAuditEntity {

    @Column(name = "ref_demand")
    private String refDemand;

    private String status;

    private Date decision_date;

    private String comment;

    //award demand

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_demand_owner", referencedColumnName = "ref_owner_hand_written")
    private OwnerHandWritten demandOwner;

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_price", referencedColumnName = "ref_award_hassan2")
    private AwardHassan2 award;


    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_honorary_award", referencedColumnName = "ref_award_honorary")
    private AwardHonorary honoraryAward;


    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_candidate", referencedColumnName = "ref_artist_account")
    private ArtistAccount candidate;


    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_book_award", referencedColumnName = "ref_award_book")
    private AwardBook bookAward;


    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_writer", referencedColumnName = "ref_artist_account")
    private ArtistAccount writer;


    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_theater_award", referencedColumnName = "ref_award_theater")
    private AwardTheater awardTheater;

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="ref_theater_piece" , referencedColumnName = "ref_theater_piece")
    private TheaterPiece theaterPiece;

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="ref_representative" , referencedColumnName = "ref_artist_account")
    private ArtistAccount representativeTheaterPiece;

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name="ref_person_connected" , referencedColumnName = "ref_account")
    private Account personConnected;

}
