package ma.digency.gov.amc.dto.searchParticipant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@Validated
public class AuthorSearchCriteria {


    private String fullName;


    private String city;


    private String gender;


    private String email;
}
