package ma.digency.gov.amc.repository.entity.proposalproject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArtisticProfession extends AcmAbstractAuditEntity {

    @Column(name = "ref_artistic_profession")
    private String refArtisticProfession;

    private String Name;

    @Column(name = "name_ar")
    private String NameAr;

    @Column(name = "ref_artistic_profession_domain")
    private String  refArtisticProfessionDomain;

}
