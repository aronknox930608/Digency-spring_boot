package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class EditionRequest {

    @NotBlank
    private String name;

    private LocalDate startedDate;

    private LocalDate endDate;

    private String address;

    private String rib;

}
