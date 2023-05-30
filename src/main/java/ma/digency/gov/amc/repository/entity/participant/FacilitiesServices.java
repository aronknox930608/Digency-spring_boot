package ma.digency.gov.amc.repository.entity.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.author.Book;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FacilitiesServices extends AcmAbstractAuditEntity {
    protected static final String FACILITIES_SERVICES_REF_FACILITIES_SERVICES= "ref_facilities_services";


    @Column(name = "ref_facilities_services")
    private String refFacilitiesServices;

    private String Name;

    private String description;


}
