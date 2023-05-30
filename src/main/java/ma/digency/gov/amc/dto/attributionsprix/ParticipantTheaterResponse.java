package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsprix.RoleTheater;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
public class ParticipantTheaterResponse {

    private String refParticipant;

    private String lastName;

    private String firstName;

    private String cin;

    private RoleTheater role1;

    private RoleTheater role2;

    private RoleTheater role3;

    private String personalityName;

}
