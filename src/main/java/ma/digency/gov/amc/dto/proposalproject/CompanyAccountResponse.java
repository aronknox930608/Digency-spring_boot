package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.Address;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfession;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import java.time.OffsetDateTime;


@Getter
@Setter
public class CompanyAccountResponse {
    private String refCompanyAccount;

    private String companyName;

    private String juridicForm;

    private String phoneNumber;

    private String faxNumber;

    private String responsibleName;

    private String registerNumber;

    private Address address;

    private StatusEnum status;

    private GeneralInformationResponse generalInformation;

    private ArtisticProfession artisticProfession;

    private OffsetDateTime dateCreation;

    private OffsetDateTime dateModification;
}
