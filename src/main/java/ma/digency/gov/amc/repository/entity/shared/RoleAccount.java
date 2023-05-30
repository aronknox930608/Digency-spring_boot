package ma.digency.gov.amc.repository.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleAccount implements Serializable {

    @EmbeddedId
    private AccountRoleId accountRoleId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @MapsId("ref_role")
    @JoinColumn(name = "ref_role", insertable = false,updatable = false, referencedColumnName = "ref_role")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleAccount that = (RoleAccount) o;
        return Objects.equals(accountRoleId, that.accountRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountRoleId);
    }
}
