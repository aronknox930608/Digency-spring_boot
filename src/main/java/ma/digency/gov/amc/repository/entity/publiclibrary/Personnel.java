package ma.digency.gov.amc.repository.entity.publiclibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Personnel extends AcmAbstractAuditEntity {
    protected static final String PERSONNEL_REF_PERSONNEL= "ref_personnel";


    @Column(name = "ref_personnel")
    private String refPersonnel;

    private String employeeName;

    private LocalDate yearOfBirth;

    private String grade ;

    private String function;

    private String competence;

    private String continuingEducation;

    private Integer generalStaffTotal;

}
