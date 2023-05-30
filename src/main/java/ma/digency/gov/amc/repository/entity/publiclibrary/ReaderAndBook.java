package ma.digency.gov.amc.repository.entity.publiclibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReaderAndBook extends AcmAbstractAuditEntity {
    protected static final String CLASSIFICATION_REF_CLASSIFICATION= "ref_reader_and_book";


    @Column(name = "ref_reader_and_book")
    private String refReaderAndBook;

    private Integer readerNumber;

    private Integer booksReadNumber;



}
