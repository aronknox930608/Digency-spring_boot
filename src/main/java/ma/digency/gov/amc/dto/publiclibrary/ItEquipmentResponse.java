package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

@Getter
@Setter
public class ItEquipmentResponse {

    @NotEmptyString
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
