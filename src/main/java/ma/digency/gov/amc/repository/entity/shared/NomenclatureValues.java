package ma.digency.gov.amc.repository.entity.shared;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class NomenclatureValues extends AcmAbstractEntity {

    private String code;

    private String nameAr;

    private String nameFr;

    @Column(name = "id_parent")
    private Long idParent;
}
