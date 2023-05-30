package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.artistCard.DemandCard;
import ma.digency.gov.amc.utils.DemandPage;
import ma.digency.gov.amc.utils.searching.DemandSearchCriteria;
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
public class DemandCardCriteriaAPIRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;


    public DemandCardCriteriaAPIRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }


    public Page<DemandCard> findAllDemandByFilter(DemandPage demandPage, DemandSearchCriteria demandSearchCriteria) {
        CriteriaQuery<DemandCard> criteriaQuery = criteriaBuilder.createQuery(DemandCard.class);
        Root<DemandCard> demandRoot = criteriaQuery.from(DemandCard.class);
        Predicate demandPredicate = getPredicate(demandSearchCriteria, demandRoot);
        criteriaQuery.where(demandPredicate);
        setOrder(demandPage, criteriaQuery, demandRoot);
        TypedQuery<DemandCard> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(demandPage.getPageNumber() * demandPage.getPageSize());
        typedQuery.setMaxResults(demandPage.getPageSize());
        Pageable pageable = getPageable(demandPage);
        long demandCount = getDemandAccount(demandPredicate);
        return new PageImpl<>(typedQuery.getResultList(),pageable, demandCount);
    }


    private long getDemandAccount(Predicate demandPredicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<DemandCard> demandRoot = countQuery.from(DemandCard.class);
        countQuery.select(criteriaBuilder.count(demandRoot)).where(demandPredicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }


    private Pageable getPageable(DemandPage demandPage) {
        Sort sort = Sort.by(demandPage.getSortDirection(), demandPage.getSortBy());
        return PageRequest.of(demandPage.getPageNumber(), demandPage.getPageSize(), sort);
    }


    private Predicate getPredicate(DemandSearchCriteria demandSearchCriteria, Root<DemandCard> rootDemand) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(demandSearchCriteria.getTypeDemand())) {
            predicates.add(criteriaBuilder.like(rootDemand.get("typeDemand"), "%" + demandSearchCriteria.getTypeDemand() + "%"));

        }
        if (Objects.nonNull(demandSearchCriteria.getRefDemand())) {
            predicates.add(criteriaBuilder.like(rootDemand.get("refDemandCard"), "%" + demandSearchCriteria.getRefDemand() + "%"));


        }
        if (Objects.nonNull(demandSearchCriteria.getStatus())) {
            predicates.add(criteriaBuilder.like(rootDemand.get("status"), "%" + demandSearchCriteria.getStatus() + "%"));


        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }


    private void setOrder(DemandPage demandPage, CriteriaQuery<DemandCard> criteriaQuery, Root<DemandCard> demandRoot) {
        if (demandPage.getSortDirection().equals(Sort.Direction.ASC)) {

            criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get(demandPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get(demandPage.getSortBy())));
        }

    }


}


