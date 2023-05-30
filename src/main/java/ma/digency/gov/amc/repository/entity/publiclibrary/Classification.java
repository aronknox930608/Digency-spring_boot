package ma.digency.gov.amc.repository.entity.publiclibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Classification extends AcmAbstractAuditEntity {
    protected static final String CLASSIFICATION_REF_CLASSIFICATION= "ref_classification";


    @Column(name = "ref_classification")
    private String refClassification;

    @Type(type = "list-array")
    @Column(name = "CLASSIFICATION_TYPE", columnDefinition = "varchar[]")
    private List<String>  classificationType;

    @Type(type = "list-array")
    @Column(name = "LOAN_TYPE", columnDefinition = "varchar[]")
    private List<String> loanType;

}
