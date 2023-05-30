package ma.digency.gov.amc.repository.entity.attributionsprix;

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

public class FontType extends AcmAbstractAuditEntity {

    @Column(name = "ref_font_type")
    private String refFontType;

    private String type;


}
