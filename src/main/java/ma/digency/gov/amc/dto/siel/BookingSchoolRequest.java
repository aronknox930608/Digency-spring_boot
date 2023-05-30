package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Setter
@Getter
public class BookingSchoolRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @Email
    private String email;

    @Positive
    private Integer visitorsNumber;

    @NotBlank
    private String city;

    private LocalDate dateVisit;

    @NotBlank
    private String timeVisit;

}
