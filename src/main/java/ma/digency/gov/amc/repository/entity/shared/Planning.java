package ma.digency.gov.amc.repository.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Planning extends AcmAbstractAuditEntity {

    private static final String PLANNING_REF_PLANNING = "ref_planning";
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = PLANNING_REF_PLANNING, referencedColumnName = PLANNING_REF_PLANNING)
    List<Vote> votes;
    @Column(name = "ref_planning")
    private String refPlanning;
    private LocalDate planningDate;
    private LocalTime startedTime;
    private LocalTime endTime;
    @Column(name = "ref_commission")
    private String refCommission;

}
