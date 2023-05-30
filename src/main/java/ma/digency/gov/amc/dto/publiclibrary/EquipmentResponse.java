package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

@Getter
@Setter
public class EquipmentResponse {

    @NotEmptyString
    private String refEquipment;

    private String equipmentAndMaterials;

    private Integer quantity;
}
