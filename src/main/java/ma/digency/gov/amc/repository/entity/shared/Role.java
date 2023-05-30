package ma.digency.gov.amc.repository.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.AcmAbstractEntity;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role extends AcmAbstractEntity {

    @Column(name = "ref_role")
    private String refRole;

    private String codeRole;

    private String labelRole;

    private Boolean editable;

    private Boolean removable;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<RoleAccount> roles;

}
