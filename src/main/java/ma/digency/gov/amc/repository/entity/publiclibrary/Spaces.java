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
public class Spaces extends AcmAbstractAuditEntity {
    protected static final String SPACES_REF_SPACES= "ref_spaces";


    @Column(name = "ref_spaces")
    private String refSpaces;

    private Integer numberOfSeats;

    private Integer childrenArea;

    private Integer adultSpace;

    private Integer youthArea;

    private Integer multimediaSpace;

    private Integer grandTotalOfSeats;

}
