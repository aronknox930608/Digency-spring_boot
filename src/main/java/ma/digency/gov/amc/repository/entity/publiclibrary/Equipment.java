package ma.digency.gov.amc.repository.entity.publiclibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Equipment extends AcmAbstractAuditEntity {
    protected static final String EQUIPMENT_REF_EQUIPMENT= "ref_equipment";


    @Column(name = "ref_equipment")
    private String refEquipment;

    private String equipmentAndMaterials;

    private Integer quantity;
}
