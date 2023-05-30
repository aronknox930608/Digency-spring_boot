package ma.digency.gov.amc.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Category extends AcmAbstractAuditEntity {

    private String refCategory;

    private String label;
}
