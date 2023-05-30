package ma.digency.gov.amc.repository.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.OffsetDateTime;

public class AuditListener {
    @PrePersist
    private void beforePersist(AcmAbstractAuditEntity object) {
        object.setDateCreation(OffsetDateTime.now());
        object.setCreationPar("USER"); //TODO get the current user
    }


    @PreUpdate
    private void beforeUpdate(AcmAbstractAuditEntity object) {
        object.setDateModification(OffsetDateTime.now());
        object.setCreationPar("USER"); //TODO get the current user
    }
}
