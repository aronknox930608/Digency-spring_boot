package ma.digency.gov.amc.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
@Audited
@EntityListeners(AuditListener.class)
public class AcmAbstractAuditEntity extends AcmAbstractEntity {

    @Column(name = "DATE_CREATION")
    private OffsetDateTime dateCreation;

    @Column(name = "CREATION_PAR")
    private String creationPar;

    @Column(name = "DATE_MODIFICATION")
    private OffsetDateTime dateModification;

    @Column(name = "MODIFICATION_PAR")
    private String modificationPar;


}
