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
public class PublishingOtherProduct extends AcmAbstractAuditEntity {
    protected static final String PUBLISHING_OTHER_PRODUCT_REF_PUBLISHING_OTHER_PRODUCT= "ref_publishing_other_product";


    @Column(name = "ref_publishing_other_product")
    private String refPublishingOtherProduct;

    private String Name;

    private String description;


}
