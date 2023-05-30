package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.card.CardRequest;
import ma.digency.gov.amc.dto.card.CardResponse;
import ma.digency.gov.amc.repository.entity.artistCard.Card;

@Mapper(withIoC = IoC.SPRING,
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = true
)
public interface CardMapper {
    Card cardRequestToCard(CardRequest cardRequest);
    CardResponse cardToCardResponse(Card card);

}
