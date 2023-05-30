package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Getter
@Setter
@Validated
public class BriefcaseBooksRequest {


    private String beneficiary;

    private String from;

    private String at;

    private String numberOfReaders;

    private String numberOfBooksRead;

}
