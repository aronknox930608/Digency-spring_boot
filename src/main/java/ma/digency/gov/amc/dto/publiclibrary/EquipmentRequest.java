package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class EquipmentRequest {


    private String equipmentAndMaterials;

    private Integer quantity;

}
