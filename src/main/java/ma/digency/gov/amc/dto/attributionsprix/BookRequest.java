package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.attributionsprix.Domain;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Validated
public class BookRequest {

    private String refDemand;

    private String title;

    private String publishingHouse;

    private LocalDate publishingDate;

    private int pagesNumber;

    private String language;

    private String abstractBook;

    private String publicationPlace;

    private String country;

    private Domain domain;

    private String Author;
}
