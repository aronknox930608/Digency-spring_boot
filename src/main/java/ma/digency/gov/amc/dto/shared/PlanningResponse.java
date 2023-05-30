package ma.digency.gov.amc.dto.shared;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class PlanningResponse {

    private String refPlanning;

    private LocalDate planningDate;

    private String startedTime;

    private String endTime;

    @Override
    public int hashCode() {
        return Objects.hash(refPlanning);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlanningResponse)) {
            return false;
        }
        PlanningResponse other = (PlanningResponse) obj;
        return Objects.equals(refPlanning, other.refPlanning);
    }
}
