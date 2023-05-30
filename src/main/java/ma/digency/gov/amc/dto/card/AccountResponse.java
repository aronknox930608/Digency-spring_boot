package ma.digency.gov.amc.dto.card;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.shared.RoleAccount;
import ma.digency.gov.amc.utils.enumeration.AccountEnum;
import ma.digency.gov.amc.utils.enumeration.AccountType;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
public class AccountResponse {

    private String refAccount;
    private String firstName;
    private String lastName;
    private String cin;
    private String login;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;






}
