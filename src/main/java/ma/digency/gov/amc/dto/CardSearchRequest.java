package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardSearchRequest {

    private String cardType;
    private String firstNameFr;
    private String lastNameFr;
    private String identityNumber;
    private String email;
    private String status;
    private String refCard;

}
