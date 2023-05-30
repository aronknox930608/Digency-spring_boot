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
public class RoleTheater extends AcmAbstractAuditEntity {

    @Column(name = "ref_role_theater")
    private String refRoleTheater;

    private String name;

}
