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
public class WritingLanguage extends AcmAbstractAuditEntity {
    protected static final String WRITING_LANGUAGE_REF_WRITING_LANGUAGE= "ref_writing_language";


    @Column(name = "ref_writing_language")
    private String refWritingLanguage;

    private String Name;

    private String description;


}
