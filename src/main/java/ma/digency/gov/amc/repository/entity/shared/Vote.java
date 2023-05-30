package ma.digency.gov.amc.repository.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vote extends AcmAbstractAuditEntity {

    @Column(name = "ref_planning")
    private String refPlanning;

    @Column(name = "ref_publication")
    private String refPublication;

    @Column(name = "ref_vote")
    private String refVote;

    private Integer accepted;

    private Integer rejected;

    private String rejectedReason;



}
