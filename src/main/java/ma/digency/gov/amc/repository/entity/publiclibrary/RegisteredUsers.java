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
public class RegisteredUsers extends AcmAbstractAuditEntity {
    protected static final String REGISTERED_USERS_REF_REGISTERED_USERS= "ref_registered_users";


    @Column(name = "ref_registered_users")
    private String refRegisteredUsers;

    private Integer registeredFeminineUsersNumber;

    private Integer registeredMaleUsersNumber;

    private Integer registeredChildNumber;

    private Integer registeredTeensNumber;

    private Integer registeredYouthNumber;

}
