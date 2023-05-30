package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.Address;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfession;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;

@Getter
@Setter
@Validated
public class CooperativeAccountBookResponse {
    private String refCooperativeAccountBook;

    private String cooperativeName;

    private String phoneNumber;

    private String faxNumber;

    private Address address;

    private String firstName;

    private String lastName;

    private String responsibleName;

    private String cooperativeMember;

    private StatusEnum status;

    private GeneralInformationResponse generalInformation;

    private ArtisticProfession artisticProfession;

    private OffsetDateTime dateCreation;

    private OffsetDateTime dateModification;
}
