package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
public class GeneralInformationResponse {
    private String refGeneralInformation;

    private String projectName;

    private String projectTitle;

    private String projectType;

    private int numDancesOrSongs;

    private float durationTime;

    private Double projectCost;

    private String projectDescription;

    private String albumTitle;


}
