package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import java.time.LocalDate;

@Getter
@Setter
public class PersonnelResponse {

    @NotEmptyString
    private String refPersonnel;

    @NotEmptyString
    private String employeeName;

    private LocalDate yearOfBirth;

    private String grade ;

    private String function;

    private String competence;

    private String continuingEducation;

    private Integer generalStaffTotal;
}
