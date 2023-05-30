package ma.digency.gov.amc.dto.siel;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.validation.annotation.Isbn;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
public class PublicationRequest {

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    private Integer publishingDate;

    @NotBlank
    private String publisher;

    @Positive
    private Long copiesNbr;

    @Positive
    private Double amout;

    @NotBlank
    private String speciality;

    @Isbn
    private String isbn;

    private String legalDeposit;

    private Integer colis;


}
