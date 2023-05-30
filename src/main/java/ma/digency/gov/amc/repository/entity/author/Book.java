package ma.digency.gov.amc.repository.entity.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.ManyToMany;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book extends AcmAbstractAuditEntity {
    protected static final String BOOK_REF_BOOK= "ref_book";
    protected static final String LIBRARY_REF_LIBRARY= "ref_library";

    @Column(name = "ref_book")
    private String refBook;

    private String title;

    private String description;

    @Column(name = "ref_author")
    private String refAuthor;


}
