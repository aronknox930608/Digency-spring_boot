package ma.digency.gov.amc.repository.entity.attributionsprix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.repository.entity.shared.Account;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class ManuscriptInformation extends AcmAbstractAuditEntity{

    @Column(name = "ref_manuscript_information")
    private String refManuscriptInformation;

    private String documentsSubject;

    private String issuingAuthority;

    private String authoritiesConcerned;


    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_manuscript_type", referencedColumnName = "ref_manuscript_type")
    private ManuscriptType type;


   /* @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "ref_font_type", referencedColumnName = "ref_font_type")
    private FontType fontType;*/

    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name = "ref_artist_account", referencedColumnName = "ref_artist_account")
    private ArtistAccount owner;


    @OneToOne
    @JoinColumn(name = "ref_demand", referencedColumnName = "ref_demand")
    private DemandPrice demand ;

    private LocalDate writingDate;

    private String bibliography;

    private String manuscriptTitle;

    private String authorName;

    private String introduction;

    private String conclusion;

    private int papersNumber;

    private float size;

    private String rule;

    private LocalDate authorshipDate;

    private String transcriberName;

    private LocalDate copyDate;

    private String explanation;

    private LocalDate creationDate;

    private String printerName;

    private LocalDate printDate;

    private String ink;

    private String category;

    private String holderType;


}
