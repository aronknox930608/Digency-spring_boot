package ma.digency.gov.amc.repository.entity.attributionsprix;

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
public class TheaterPiece extends AcmAbstractAuditEntity {

    @Column(name = "ref_theater_piece")
    private String refTheaterPiece;

    private String title;

    private String theaterTroupeName;

    private String textTheaterPiece;

    private LocalDate date;

}
