package ma.digency.gov.amc.dto.library;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@Validated
public class BookRequest {

    @NotBlank
    private String title;

    private String description;

}
