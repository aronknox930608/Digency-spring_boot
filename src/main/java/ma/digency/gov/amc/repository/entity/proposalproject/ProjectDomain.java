package ma.digency.gov.amc.repository.entity.proposalproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.utils.enumeration.DomainComponent;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDomain extends AcmAbstractAuditEntity {

    @Column(name ="ref_domain")
    private String refDomain;

    @Column(name ="short_name")
    private String shortName;

    @Column(name ="long_name")
    private String longName;

    @Column(name ="short_name_ar")
    private String shortNameAr;

    @Column(name ="long_name_ar")
    private String longNameAr;

    @Column(name="component")
    @Enumerated(EnumType.STRING)
    DomainComponent component;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "ref_domain", referencedColumnName = "ref_domain", updatable = false)
    private List<ProjectSubDomain> subDomains;

}
