package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import java.time.LocalDate;

@Getter
@Setter
public class BriefcaseBooksResponse {

    @NotEmptyString
    private String refBriefcaseBooks;

    private String beneficiary;

    private String from;

    private String at;

    private String numberOfReaders;

    private String numberOfBooksRead;
}
