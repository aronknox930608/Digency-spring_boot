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
public class ArtisticProfessionCategory extends AcmAbstractAuditEntity {

    @Column(name = "ref_artistic_profession_category")
    private String refArtisticProfessionCategory;

    private String Name;

    @Column(name = "name_ar")
    private String NameAr;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "ref_artistic_profession_category", referencedColumnName = "ref_artistic_profession_category")
    private List<ArtisticProfessionDomain> artisticProfessionCategories;


}
