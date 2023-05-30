package ma.digency.gov.amc.repository.entity.siel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingStand extends AcmAbstractAuditEntity {

    @Column(name = "ref_booking_stand")
    private String refBookingStand;

    private String volumeInCubicMeter;

    private String category;

    private String paymentMethod;

    private String branchActivity;

    @Column(name = "ref_exhibitor")
    private String refExhibitor;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "ref_edition")
    private String refEdition;


}
