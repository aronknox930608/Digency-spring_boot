package ma.digency.gov.amc.repository.entity.publiclibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItEquipment extends AcmAbstractAuditEntity {
    protected static final String IT_EQUIPMENT_REF_IT_EQUIPMENT = "ref_it_equipment";

    @Column(name = "ref_it_equipment")
    private String refItEquipment;

    private Integer numberOfFunctionalComputers;

    private Integer numberOfNonFunctionalComputers;

    private Integer numberOfFunctionalPrinters;

    private Integer numberOfNonFunctionalPrinters;

    private Integer numberOfFunctionalProjector;

    private Integer numberOfNonFunctionalProjector;

    private Integer numberOfFunctionalScanner;

    private Integer numberOfNonFunctionalScanner;

}
