package ma.digency.gov.amc.service.card;

import ma.digency.gov.amc.dto.card.CardResponse;
import ma.digency.gov.amc.repository.entity.artistCard.Card;
import ma.digency.gov.amc.utils.CardPage;
import ma.digency.gov.amc.utils.searching.CardSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService {
    Page<Card> findCardsPageable(Pageable pageable);
    Page<CardResponse> getCardsWithCriteriaSearch(CardPage cardPage, CardSearchCriteria cardSearchCriteria);
    Card createCard(Card card);
    Card findCardByRefCard(String refCard);
    void deleteCard(String refCard);
    Card retiredCard(Card find);
    List<CardResponse> getAllCards();

}
