package ma.digency.gov.amc.repository.entity.shared;

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
public class GeneralMember extends AcmAbstractAuditEntity {

    @Column(name = "ref_general_member")
    private String refGeneralMember;

    private String refParent;

    private String firstName;

    private String lastName;

    private String gender;

    private String cin;

    private String email;

    private String phoneNumber;

    private String role;

}
