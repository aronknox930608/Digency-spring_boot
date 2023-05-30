package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.validation.annotation.Isbn;
import ma.digency.gov.amc.utils.validation.annotation.NotEmptyString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class PublicationResponse {

    private String refPublication;

    @NotEmptyString
    private String author;

    @NotEmptyString
    private String title;

    private Integer publishingDate;

    @NotEmptyString
    private String publisher;

    @Positive
    private Long copiesNbr;

    @Positive
    private Double amout;

    @NotEmptyString
    private String speciality;

    @Isbn
    private String isbn;

    private String legalDeposit;

    private StatusEnum status;

    private Integer colis;


    @Override
    public int hashCode() {
        return Objects.hash(refPublication);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PublicationResponse)) {
            return false;
        }
        PublicationResponse other = (PublicationResponse) obj;
        return Objects.equals(refPublication, other.refPublication);
    }


}
