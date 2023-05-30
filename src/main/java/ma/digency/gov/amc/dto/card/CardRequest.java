package ma.digency.gov.amc.dto.card;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.utils.enumeration.IdentityType;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.time.LocalDate;
@Getter
@Setter
@Validated

public class CardRequest {
    private String refCard;
    private int numCard;
    private LocalDate dateCardCreation;
    private LocalDate dateCardValidation;
    private String cardType;
    private String refArtistAccount;
    private String status;
}
