package ma.digency.gov.amc.repository.entity.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractEntity;
import ma.digency.gov.amc.repository.entity.shared.NomenclatureValues;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ProposalProjectDocument extends AcmAbstractEntity {

    @Column(name = "ref_Project")
    private String refProject;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_nomenclature", referencedColumnName = "id")
    private NomenclatureValues value;
}
