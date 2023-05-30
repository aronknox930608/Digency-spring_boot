package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Setter
@Getter
public class BookingSchoolResponse {

    private String refBookingSchool;

    @NotEmptyString
    private String name;

    @NotEmptyString
    private String phoneNumber;

    @Email
    private String email;

    @Positive
    private Integer visitorsNumber;

    @NotEmptyString
    private String city;

    private LocalDate dateVisit;

    @NotEmptyString
    private String timeVisit;

    private StatusEnum status;
}
