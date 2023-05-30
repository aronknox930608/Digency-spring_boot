package ma.digency.gov.amc.repository.entity.siel;

import liquibase.license.LicenseService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Where(clause = "status!='DELETED'")
public class Publication extends AcmAbstractAuditEntity {

    @Column(name = "ref_publication")
    private String refPublication;

    private String author;

    private String title;

    private Integer publishingDate;

    private String publisher;

    private Long copiesNbr;

    private Double amout;

    private String speciality;

    private String isbn;

    private String legalDeposit;

    private Integer colis;


    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "ref_exhibitor")
    private String refExhibitor;


    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "ref_publication",referencedColumnName = "ref_publication")
    private List<EditionPublication> editionPublications;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(refPublication);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Publication)) {
            return false;
        }
        Publication other = (Publication) obj;
        return Objects.equals(refPublication, other.refPublication);
    }
}
