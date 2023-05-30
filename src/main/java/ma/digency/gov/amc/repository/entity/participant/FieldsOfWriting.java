package ma.digency.gov.amc.repository.entity.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FieldsOfWriting extends AcmAbstractAuditEntity {
    protected static final String FIELDS_OF_WRITING_REF_FIELDS_OF_WRITING= "ref_fields_of_writing";


    @Column(name = "ref_fields_of_writing")
    private String refFieldsOfWriting;

    private String Name;

    private String description;


}
