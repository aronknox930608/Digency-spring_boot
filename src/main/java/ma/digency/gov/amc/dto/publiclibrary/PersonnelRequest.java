package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@Validated
public class PersonnelRequest {

    @NotBlank
    private String employeeName;

    private LocalDate yearOfBirth;

    private String grade ;

    private String function;

    private String competence;

    private String continuingEducation;

    private Integer generalStaffTotal;


}
