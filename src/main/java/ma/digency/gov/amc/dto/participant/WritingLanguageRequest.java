package ma.digency.gov.amc.dto.participant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Validated
public class WritingLanguageRequest {

    @NotBlank
    private String Name;

    private String Description;


}
