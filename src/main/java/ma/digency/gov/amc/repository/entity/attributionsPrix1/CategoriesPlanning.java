package ma.digency.gov.amc.repository.entity.attributionsPrix1;

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
public class CategoriesPlanning extends AcmAbstractAuditEntity {

    @Column(name = "ref_categories_planning")
    private String refCategoriesPlanning;

    private String refCategories;

    private String refPricePlanning;
}
