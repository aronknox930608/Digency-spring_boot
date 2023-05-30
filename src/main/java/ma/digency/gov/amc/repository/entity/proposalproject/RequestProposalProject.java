package ma.digency.gov.amc.repository.entity.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.shared.Account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class RequestProposalProject extends AcmAbstractAuditEntity {

    @Column(name = "ref_request")
    private String refRequest;

    private String type;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "ref_account", referencedColumnName = "ref_account")
    private Account refAccount;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "ref_Project", referencedColumnName = "ref_Project")
    private ProposalProject refProposalProject;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "ref_company_account", referencedColumnName = "ref_company_account")
    private CompanyAccount refCompanyAccount;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "ref_cooperative_account", referencedColumnName = "ref_cooperative_account")
    private CooperativeAccount refCooperativeAccount;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "ref_book_person_account", referencedColumnName = "ref_book_person_account")
    private BookPersonAccount refBookPersonAccount;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "ref_artist_account", referencedColumnName = "ref_artist_account")
    private ArtistAccount refArtistAccount;


}
