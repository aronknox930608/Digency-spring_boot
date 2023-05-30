package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.AccountEnum;
import ma.digency.gov.amc.utils.enumeration.AccountType;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class AccountRequest implements Serializable {

    private static final long serialVersionUID = 1l;

    private String login;

    @NotBlank
    private String password;

    @Unique @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    private String[] roles;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @Unique
    private String cin;

    private boolean mail;

    private String vkey;

    private AccountEnum status;

    private AccountType accountType;

    private String redirectTo;

    private boolean doNotSendEmail;

}
