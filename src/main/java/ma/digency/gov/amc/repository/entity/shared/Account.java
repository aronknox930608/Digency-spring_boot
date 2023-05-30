package ma.digency.gov.amc.repository.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.proposalproject.RequestProposalProject;
import ma.digency.gov.amc.utils.enumeration.AccountEnum;
import ma.digency.gov.amc.utils.enumeration.AccountType;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.junit.runner.Request;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account extends AcmAbstractAuditEntity {

    @Column(name = "ref_account")
    private String refAccount;

    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String cin;

    private String login;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private String password;

    private String vkey;

    @Lob
    private byte[] photo;

    @Enumerated(EnumType.STRING)
    private AccountEnum status;

    @Column(name="account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ref_account",referencedColumnName = "ref_account")
    private Set<RoleAccount> roles;





    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "ref_account" , referencedColumnName = "ref_account")
    private List<RequestProposalProject> request;

}
