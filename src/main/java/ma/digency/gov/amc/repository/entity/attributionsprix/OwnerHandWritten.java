package ma.digency.gov.amc.repository.entity.attributionsprix;

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


public class OwnerHandWritten extends AcmAbstractAuditEntity {

    @Column(name = "ref_owner_hand_written")
    private String refOwnerHandWritten;

    private String cin;

    private String firstName;

    private String lastName;

    private String firstNameAr;

    private String lastNameAr;

    private String gender;

    private String email;

    private String phone;

    private String rib;


}




