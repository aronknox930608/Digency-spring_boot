package ma.digency.gov.amc.repository.entity.attributionsprix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParticipantsTheater extends AcmAbstractAuditEntity {

    @Column(name = "ref_participant")
    private String refParticipant;

    private String lastName;

    private String firstName;

    private String cin;

    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name = "ref_role1", referencedColumnName = "ref_role_theater")
    private RoleTheater role1;

    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name = "ref_role2", referencedColumnName = "ref_role_theater")
    private RoleTheater role2;

    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name = "ref_role3", referencedColumnName = "ref_role_theater")
    private RoleTheater role3;

    private String personalityName;

    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name = "ref_theater_piece", referencedColumnName = "ref_theater_piece")
    private TheaterPiece theaterPiece;

}
