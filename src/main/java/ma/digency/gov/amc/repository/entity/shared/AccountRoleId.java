package ma.digency.gov.amc.repository.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRoleId implements Serializable {

    private static final long serialVersionUID =1l;

    @Column(name = "ref_Role")
    private String refRole;

    @Column(name = "ref_Account")
    private String refAccount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountRoleId that = (AccountRoleId) o;
        return Objects.equals(refRole, that.refRole) && Objects.equals(refAccount, that.refAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refRole, refAccount);
    }
}
