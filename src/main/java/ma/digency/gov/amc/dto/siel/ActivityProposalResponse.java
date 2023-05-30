package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class ActivityProposalResponse {

    private String refActivityProposal;

    private LocalDate proposedDate;

    private String topic;

    private String participants;

    private String activityPlace;

    private String bookTitle;

    private String author;

    private String publishingHouse;

    private String publishingYear;

    private String editeur;


    @Override
    public int hashCode() {
        return Objects.hash(refActivityProposal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivityProposalResponse)) {
            return false;
        }
        ActivityProposalResponse other = (ActivityProposalResponse) obj;
        return Objects.equals(refActivityProposal, other.refActivityProposal);
    }
}
