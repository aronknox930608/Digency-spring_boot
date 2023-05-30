package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ActivityProposalRequest {


    private LocalDate proposedDate;

    private String topic;

    private String participants;

    private String activityPlace;

    private String bookTitle;

    private String author;

    private String publishingHouse;

    private String publishingYear;

    private String editeur;


}
