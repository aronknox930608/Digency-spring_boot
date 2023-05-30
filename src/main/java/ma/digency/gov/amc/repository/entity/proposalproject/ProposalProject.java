package ma.digency.gov.amc.repository.entity.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class ProposalProject extends AcmAbstractAuditEntity {

    @Column(name = "ref_Project")
    private String refProject;

    private LocalDate startDate;

    private LocalDate endDate;

    private StatusEnum status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_sub_domain", referencedColumnName = "ref_sub_domain")
    private ProjectSubDomain subDomain;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_Project", referencedColumnName = "ref_Project", updatable = false)
    private List<ProposalProjectDocument> documents;

    private Double amount;
}
