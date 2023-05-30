package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.Address;
import ma.digency.gov.amc.repository.entity.BirthData;
import ma.digency.gov.amc.utils.enumeration.IdentityType;
import ma.digency.gov.amc.utils.enumeration.MaritalStatus;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Getter
@Setter
@Validated
public class CandidateHonoraryAwardResponse {

    private String refArtistAccount;

    private String cin;

    private String firstName;

    private String lastName;

    private String firstNameAR;

    private String lastNameAR;

    private String gender;

    private IdentityType identityType;

    private String identityNumber;

    private String email;

    private String phoneNumber;

    private String otherPhoneNumber;

    private MaritalStatus maritalStatus;

    private Integer dependentChildren;

    private BirthData birthdata;

    private Address address;

    private String refpicture;

    private String refCv;

    private String refIdentityStatement;
}
