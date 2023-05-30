package ma.digency.gov.amc.repository.entity.siel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditionPublication implements Serializable {

    @EmbeddedId
    private EditionPublicationId editionPublicationId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @MapsId("ref_edition")
    @JoinColumn(name = "ref_edition", insertable = false,updatable = false, referencedColumnName = "ref_edition")
    private Edition edition;




}
