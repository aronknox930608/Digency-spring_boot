package ma.digency.gov.amc.repository.entity.participant;

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
public class BookPromotionForm extends AcmAbstractAuditEntity {
    protected static final String BOOK_PROMOTION_FORM_REF_BOOK_PROMOTION_FORM= "ref_book_promotion_form";


    @Column(name = "ref_book_promotion_form")
    private String refBookPromotionForm;

    private String Name;

    private String description;


}
