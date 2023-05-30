package ma.digency.gov.amc.repository.entity.shared;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractEntity;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class Nomenclature extends AcmAbstractEntity {

    private String family;

    private String nameAr;

    private String nameFr;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "ID_PARENT", referencedColumnName = "ID")
    private List<NomenclatureValues> values;


}
