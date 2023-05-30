package ma.digency.gov.amc.repository.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.List;

@Getter
@Setter
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Embeddable
public class ArtWork extends AcmAbstractAuditEntity {

    private String refArtWork;

    private String projectName;

    private String projectTitle;

    private String projectType;

    private int numberDanceOrSongs;

    private int durationTime;

    private Double projectCost;

    @Type(type = "list-array")
    @Column(name = "artwork_member", columnDefinition = "ARRAY", nullable = true)
    private List<String> members;//list

    private String description;

    private String albumTitle;

}
