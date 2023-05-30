package ma.digency.gov.amc.repository.entity.attributionsprix;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AwardObtained extends AcmAbstractAuditEntity {

    @Column(name = "ref_award_obtained")
    private String reAwardObtained;

    private String award;

    private String organisers;

    private float year;

    private String artist;

}
