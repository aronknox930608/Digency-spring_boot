package ma.digency.gov.amc.repository.entity.siel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingSchool extends AcmAbstractAuditEntity {

    @Column(name = "ref_booking_school")
    private String refBookingSchool;

    private String name;

    private String phoneNumber;

    private String email;

    private Integer visitorsNumber;

    private String city;

    private LocalDate dateVisit;

    private LocalTime timeVisit;

    @Column(name = "ref_edition")
    private String refEdition;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
