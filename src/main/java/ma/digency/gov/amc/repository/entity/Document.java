package ma.digency.gov.amc.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Document extends AcmAbstractAuditEntity {

    @Column(name = "ref_document")
    private String refDocument;

    private String type;

    private String nature;

    private String name;

    private Long size;

    private String refObject;

    private String refParent;

    @Lob
    private byte[] data;


}
