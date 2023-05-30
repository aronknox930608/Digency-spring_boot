package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.Address;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Embedded;
import java.util.List;


@Getter
@Setter
@Validated
public class CooperativeAccountRequest {

    private String cooperativeName;

    private String phoneNumber;

    private String faxNumber;

    private Address address;

    private String firstName;

    private String lastName;

    private String responsibleName;

    private GeneralInformationRequest generalInformation;

    private String cooperativeMember;

    private String refArtisticProfession;
}
