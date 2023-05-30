package ma.digency.gov.amc.mapper;

import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class CustomDateMapper {

    public LocalTime stringToLocalTime(String time) {
        try {
            if (time != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                return LocalTime.parse(time, formatter);
            }
        } catch (Exception e) {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TIME_FORMAT_INVALID);
        }
        return null;
    }

    public String localTimeToString(LocalTime time) {
        if (time != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return formatter.format(time);
        }
        return null;
    }
}
