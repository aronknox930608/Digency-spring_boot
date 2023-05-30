package ma.digency.gov.amc.repository.entity.publiclibrary;

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
public class GeographicalLocation extends AcmAbstractAuditEntity {
    protected static final String GEOGRAPHICAL_LOCATION_REF_GEOGRAPHICAL_LOCATION= "ref_geographical_location";


    @Column(name = "ref_geographical_location")
    private String refGeographicalLocation;

    private String longitude;

    private String latitude;



}
