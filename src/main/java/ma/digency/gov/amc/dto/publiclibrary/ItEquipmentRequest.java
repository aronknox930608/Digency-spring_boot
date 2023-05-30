package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class ItEquipmentRequest {




    private Integer numberOfFunctionalComputers;

    private Integer numberOfNonFunctionalComputers;

    private Integer numberOfFunctionalPrinters;

    private Integer numberOfNonFunctionalPrinters;

    private Integer numberOfFunctionalProjector;

    private Integer numberOfNonFunctionalProjector;

    private Integer numberOfFunctionalScanner;

    private Integer numberOfNonFunctionalScanner;

}
