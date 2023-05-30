package ma.digency.gov.amc.repository.entity.proposalproject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArtisticProfessionDomain extends AcmAbstractAuditEntity {

    @Column(name = "ref_artistic_profession_domain")
    private String refArtisticProfessionDomain;

    private String Name;

    @Column(name = "name_ar")
    private String NameAr;

    @Column(name = "ref_artistic_profession_category")
    private String  refArtisticProfessionCategory;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "ref_artistic_profession_domain",referencedColumnName = "ref_artistic_profession_domain")
    private List<ArtisticProfession> artisticProfessions;
}
