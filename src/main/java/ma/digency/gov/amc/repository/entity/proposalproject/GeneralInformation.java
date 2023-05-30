package ma.digency.gov.amc.repository.entity.proposalproject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeneralInformation extends AcmAbstractAuditEntity {

    @Column(name = "ref_general_information")
    private String refGeneralInformation;

    private String projectName;

    private String projectTitle;

    private String projectType;

    @Column(name = "NUM_DANCES_OR_SONGS")
    private int numDancesOrSongs;

    private float durationTime;

    private Double projectCost;

    private String projectDescription;

    private String albumTitle;

}
