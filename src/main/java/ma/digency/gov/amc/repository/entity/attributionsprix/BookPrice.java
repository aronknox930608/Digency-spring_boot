package ma.digency.gov.amc.repository.entity.attributionsprix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.Demand;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookPrice extends AcmAbstractAuditEntity {

    @Column(name = "ref_book")
    private String refBook;

    private String title;

    private String publishingHouse;

    private LocalDate publishingDate;

    private int pagesNumber;

    private String language;

    private String abstractBook;

    private String publicationPlace;

    private String country;

    private String author;

    private String domain;

}
