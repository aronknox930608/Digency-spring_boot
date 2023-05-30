package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.AccountEnum;
import ma.digency.gov.amc.utils.enumeration.AccountType;

@Getter
@Setter
public class AccountAdminResponse {
    private String refAccount;

    private String firstname;

    private String lastname;

    private String cin;

    private String email;

    private String phoneNumber;

    private AccountType accountType;

    private AccountEnum status;
}
