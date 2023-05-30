package ma.digency.gov.amc.repository.entity.proposalproject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.Address;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookPersonAccount extends AcmAbstractAuditEntity {

    @Column(name = "ref_book_person_account")
    private String refBookPerson;

    private String holder;

    private String title;

    private Integer duration;

    private Double amount;

    @Embedded
    private Address address;

}
