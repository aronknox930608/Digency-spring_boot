package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.Address;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
public class CompanyAccountRequest {

    private String companyName;

    private String juridicForm;

    private String phoneNumber;

    private String faxNumber;

    private String responsibleName;

    private String registerNumber;

    private Address address;

    private GeneralInformationRequest generalInformation;

    private String refArtisticProfession;
}
