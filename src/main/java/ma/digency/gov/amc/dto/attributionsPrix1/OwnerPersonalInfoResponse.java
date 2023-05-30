package ma.digency.gov.amc.dto.attributionsPrix1;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.Address;
import ma.digency.gov.amc.repository.entity.BirthData;
import ma.digency.gov.amc.utils.enumeration.MaritalStatus;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class OwnerPersonalInfoResponse {

    private String refArtistAccount;

    private String cin;

    private String firstName;

    private String lastName;

    private String firstNameAr;

    private String lastNameAr;

    private String gender;

    private String email;

    private String phoneNumber;

    private String ribNumber;

    private BirthData birthdata;

    private Address address;

    private String otherPhoneNumber;

    private MaritalStatus maritalStatus;

    private Integer dependentChildren;

    private String biography;
}
