package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.Address;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtisticProfession;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.Embedded;
import java.time.OffsetDateTime;
import java.util.List;


@Getter
@Setter
public class CooperativeAccountResponse {
    private String refCooperativeAccount;

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
