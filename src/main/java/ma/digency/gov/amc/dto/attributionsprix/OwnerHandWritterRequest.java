package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class OwnerHandWritterRequest {

    private String cin;

    private String firstName;

    private String lastName;

    private String firstNameAr;

    private String lastNameAr;

    private String gender;

    private String email;

    private String phone;

    private String rib;

}
