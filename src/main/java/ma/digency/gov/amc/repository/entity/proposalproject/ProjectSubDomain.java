package ma.digency.gov.amc.repository.entity.proposalproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.siel.Publication;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectSubDomain extends AcmAbstractAuditEntity {

    @Column(name = "ref_sub_domain")
    private String refSubDomain;

    @Column(name ="name")
    private String name;

    @Column(name ="name_ar")
    private String nameAr;

    @Column(name ="ref_domain")
    private String refDomain;

    @Override
    public int hashCode() {
        return Objects.hash(refSubDomain);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProjectSubDomain)) {
            return false;
        }
        ProjectSubDomain other = (ProjectSubDomain) obj;
        return Objects.equals(refSubDomain, other.refSubDomain);
    }
}
