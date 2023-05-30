package ma.digency.gov.amc.service.card;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.card.CardResponse;
import ma.digency.gov.amc.mapper.CardMapper;
import ma.digency.gov.amc.repository.CardCriteriaRepository;
import ma.digency.gov.amc.repository.CardRepository;
import ma.digency.gov.amc.repository.entity.artistCard.Card;
import ma.digency.gov.amc.utils.CardPage;
import ma.digency.gov.amc.utils.searching.CardSearchCriteria;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    //modify
    private final CardRepository cardRepository;
    private final CardCriteriaRepository cardCriteriaRepository;
    private final CardMapper cardMapper;
    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public Page<Card> findCardsPageable(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }


    @Override
    public Page<CardResponse> getCardsWithCriteriaSearch(CardPage cardPage, CardSearchCriteria cardSearchCriteria) {
        return cardCriteriaRepository.findAllCardByFilter(cardPage,cardSearchCriteria).map(cardMapper::cardToCardResponse);

    }


    @Override
    public Card createCard(Card card) {
        var refCard= referenceSequenceService.generateRefCard();
        card.setRefCard(refCard);
        return cardRepository.save(card);
    }


    @Override
    public Card findCardByRefCard(String refCard) {
        return cardRepository.findCardByRefCard(refCard);
    }


    @Override
    public void deleteCard(String refCard) {
        cardRepository.delete(cardRepository.findCardByRefCard(refCard));

    }

    @Override
    public Card retiredCard(Card find) {
        find.setStatus("RETIRED");
        return cardRepository.save(find);
    }


    @Override
    public List<CardResponse> getAllCards() {
         return cardRepository.findAll().stream().map(cardMapper::cardToCardResponse).collect(Collectors.toList());
    }


}
