package ma.digency.gov.amc.dto.participant;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

@Getter
@Setter
public class WritingLanguageResponse {

    @NotEmptyString
    private String refWritingLanguage;

    @NotEmptyString
    private String Name;

    private String description;


}
