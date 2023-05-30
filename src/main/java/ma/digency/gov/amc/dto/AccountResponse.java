package ma.digency.gov.amc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AccountResponse implements Serializable {

    private String login;
}
