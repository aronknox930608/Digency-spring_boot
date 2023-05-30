package ma.digency.gov.amc.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class SubCategory extends AcmAbstractAuditEntity {

    private String refSubCategory;

    private String label;
}
