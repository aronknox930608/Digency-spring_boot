package ma.digency.gov.amc.repository.entity.siel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Edition extends AcmAbstractAuditEntity {

    private static final String REF_EDITION="ref_edition";

    @Column(name = "ref_edition")
    private String refEdition;

    private String name;

    private LocalDate startedDate;

    private LocalDate endDate;

    private String address;

    private String rib;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = REF_EDITION, referencedColumnName = REF_EDITION, updatable = false)
    private List<BookingSchool> bookingSchools;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = REF_EDITION, referencedColumnName = REF_EDITION, updatable = false)
    private List<BookingStand> bookingStands;


    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
