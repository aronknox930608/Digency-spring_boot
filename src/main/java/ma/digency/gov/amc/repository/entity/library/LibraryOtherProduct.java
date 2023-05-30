package ma.digency.gov.amc.repository.entity.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LibraryOtherProduct extends AcmAbstractAuditEntity {
    protected static final String LIBRARY_OTHER_PRODUCT_REF_LIBRARY_OTHER_PRODUCT= "ref_library_other_product";

    @Column(name = "ref_library_other_product")
    private String refLibraryOtherProduct;

    private String productName;

}
