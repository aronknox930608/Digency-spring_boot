package ma.digency.gov.amc.repository.entity.siel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ForeignRepresented extends AcmAbstractAuditEntity {

    @Column(name = "ref_foreign_represented")
    private String refForeignRepresented;

    private LocalDate passportExpiration;

    private String passportNumber;

    private String passportType;

    private String personName;

    private LocalDate birthDay;

    private String birthCountry;

    private String birthNationality;

    @Column(name = "ref_exhibitor")
    private String refExhibitor;

}


