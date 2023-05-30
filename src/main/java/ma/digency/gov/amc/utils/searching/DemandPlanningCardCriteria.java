package ma.digency.gov.amc.utils.searching;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class DemandPlanningCardCriteria {

    private String refDemandPlanning;
    private String startedDate;
    private LocalDate endDate;


    public LocalDate stringToLocalDate(String date) {
        try {
            if (date != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                return LocalDate.parse(date,formatter);
            }
        } catch (Exception e) {

            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TIME_FORMAT_INVALID);
        }
        return null;
    }

}
