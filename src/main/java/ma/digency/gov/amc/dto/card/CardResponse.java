package ma.digency.gov.amc.dto.card;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.IdentityType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Getter
@Setter

public class CardResponse {
    private String refCard;
    private int numCard;
    private LocalDate dateCardCreation;
    private LocalDate dateCardValidation;
    private String cardType;
    private String refArtistAccount;
    private String status;

}
