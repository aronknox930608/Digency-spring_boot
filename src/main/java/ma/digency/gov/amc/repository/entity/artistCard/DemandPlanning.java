package ma.digency.gov.amc.repository.entity.artistCard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DemandPlanning extends AcmAbstractAuditEntity {
    @Column(name="REF_DEMAND_PLANNING")
    private String refDemandPlanning;
    private LocalDate startedDate;
    private LocalDate endDate;
    private LocalTime startedTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

}
