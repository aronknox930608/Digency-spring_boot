package ma.digency.gov.amc.repository.entity.siel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditionPublicationId implements Serializable {

    private static final long serialVersionUID =1l;

    @Column(name="ref_edition")
    private String refEdition;

    @Column(name = "ref_publication")
    private String refPublication;
}
