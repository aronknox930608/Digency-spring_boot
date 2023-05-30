package ma.digency.gov.amc.repository.entity.publiclibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BriefcaseBooks extends AcmAbstractAuditEntity {
    protected static final String BRIEFCASE_BOOKS_REF_BRIEFCASE_BOOKS= "ref_briefcase_books";


    @Column(name = "ref_briefcase_books")
    private String refBriefcaseBooks;

    private String beneficiary;

    private String from;

    private String at;

    private String numberOfReaders;

    private String numberOfBooksRead;


}
