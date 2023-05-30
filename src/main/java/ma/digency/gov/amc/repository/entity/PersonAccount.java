package ma.digency.gov.amc.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PersonAccount extends AcmAbstractAuditEntity {

    private String refPersonAccount;

    private String cin;

    private String firstName;

    private String lastName;

    @Embedded
    private BirthData birthdata;

    @Embedded
    private Address address;

    private String phoneNumber;

    private String faxNumber;
}
