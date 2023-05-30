package ma.digency.gov.amc.repository.entity.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuggestedTypesBook extends AcmAbstractAuditEntity {
    protected static final String SUGGESTED_TYPES_BOOK_REF_SUGGESTED_TYPES_BOOK= "ref_suggested_types_book";

    @Column(name = "ref_suggested_types_book")
    private String refSuggestedTypesBook;

    private String typeBook;


}
