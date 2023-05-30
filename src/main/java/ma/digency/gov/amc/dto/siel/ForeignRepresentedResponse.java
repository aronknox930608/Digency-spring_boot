package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ForeignRepresentedResponse {

    private String refForeignRepresented;

    private LocalDate passportExpiration;

    private String passportNumber;

    private String passportType;

    private String personName;

    private LocalDate birthDay;

    private String birthCountry;

    private String birthNationality;
}
