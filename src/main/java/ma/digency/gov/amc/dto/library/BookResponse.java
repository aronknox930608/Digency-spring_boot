package ma.digency.gov.amc.dto.library;


import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

@Getter
@Setter
public class BookResponse {

    private String refBook;

    @NotEmptyString
    private String title;

    private String description;

}
