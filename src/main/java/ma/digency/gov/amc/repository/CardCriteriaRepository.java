package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.artistCard.Card;
import ma.digency.gov.amc.utils.CardPage;
import ma.digency.gov.amc.utils.searching.CardSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CardCriteriaRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public CardCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder=entityManager.getCriteriaBuilder();
    }
    public Page<Card> findAllCardByFilter(CardPage cardPage, CardSearchCriteria cardSearchCriteria){
        CriteriaQuery<Card> criteriaQuery=criteriaBuilder.createQuery(Card.class);
        Root<Card> cardRoot= criteriaQuery.from(Card.class);
        Predicate cardPredicate=getCardPredicate(cardSearchCriteria,cardRoot);
        criteriaQuery.where(cardPredicate) ;
        setOrder(cardPage,criteriaQuery,cardRoot);
        TypedQuery<Card> typedQuery =entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(cardPage.getNumPage() * cardPage.getSizePage());
        typedQuery.setMaxResults(cardPage.getSizePage());
        Pageable pageable = getPageable(cardPage);
        long demandCount  = getPageAccount(cardPredicate);
        return new PageImpl<>(typedQuery.getResultList(),pageable, demandCount);

    }


    private long getPageAccount(Predicate cardPredicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Card> cardRoot = countQuery.from(Card.class);
        countQuery.select(criteriaBuilder.count(cardRoot)).where(cardPredicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }


    private Pageable getPageable(CardPage cardPage) {
        Sort sort = Sort.by(cardPage.getSortDirection(), cardPage.getSortedBy());
        return PageRequest.of(cardPage.getNumPage(), cardPage.getSizePage(), sort);

    }


    private void setOrder(CardPage cardPage,
                          CriteriaQuery<Card> criteriaQuery,
                          Root<Card> cardRoot) {
        if (cardPage.getSortDirection().equals(Sort.Direction.ASC)) {

            criteriaQuery.orderBy(criteriaBuilder.asc(cardRoot.get(cardPage.getSortedBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(cardRoot.get(cardPage.getSortedBy())));
        }
    }


    private Predicate getCardPredicate(CardSearchCriteria cardSearchCriteria, Root<Card> cardRoot) {
        List<Predicate> predicates=new ArrayList<>();
        if(Objects.nonNull(cardSearchCriteria.getCardType())){
            predicates.add(criteriaBuilder.like(cardRoot.get("cardType"), "%" + cardSearchCriteria.getCardType() + "%"));
        }
        if(Objects.nonNull(cardSearchCriteria.getRefCard())){
            predicates.add(criteriaBuilder.like(cardRoot.get("refCard"), "%" + cardSearchCriteria.getRefCard() + "%"));
        }
        if(cardSearchCriteria.getNumCard()!=0){
            predicates.add(criteriaBuilder.equal(cardRoot.get("numCard"),cardSearchCriteria.getNumCard()));

        }
        if(Objects.nonNull(cardSearchCriteria.getStatus())){
            predicates.add(criteriaBuilder.like(cardRoot.get("status"), "%" + cardSearchCriteria.getStatus() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}
