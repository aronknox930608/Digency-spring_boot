package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.artistCard.DemandPlanning;
import ma.digency.gov.amc.utils.DemandPlanningPage;
import ma.digency.gov.amc.utils.searching.DemandPlanningCardCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class DemandPlanningCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private  final CriteriaBuilder criteriaBuilder;


    public DemandPlanningCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }


    public Page<DemandPlanning> findAllDemandPlannigByFilter(DemandPlanningPage demandPlanningPage, DemandPlanningCardCriteria demandPlanningCardCriteria) {
        CriteriaQuery<DemandPlanning> criteriaQuery = criteriaBuilder.createQuery(DemandPlanning.class);
        Root<DemandPlanning> demandPlanningRoot = criteriaQuery.from(DemandPlanning.class);
        Predicate demandPredicate = getPredicate(demandPlanningCardCriteria,demandPlanningRoot);
        criteriaQuery.where(demandPredicate);
        setOrder(demandPlanningPage, criteriaQuery, demandPlanningRoot);
        TypedQuery<DemandPlanning> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(demandPlanningPage.getPageNumber() * demandPlanningPage.getPageSize());
        typedQuery.setMaxResults(demandPlanningPage.getPageSize());
        Pageable pageable = getPageable(demandPlanningPage);
        long demandCount = getDemandAccount(demandPredicate);
        return new PageImpl<>(typedQuery.getResultList(),pageable, demandCount);
    }


    private long getDemandAccount(Predicate demandPredicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<DemandPlanning> demandRoot = countQuery.from(DemandPlanning.class);
        countQuery.select(criteriaBuilder.count(demandRoot)).where(demandPredicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }


    private Pageable getPageable(DemandPlanningPage demandPage) {
        Sort sort = Sort.by(demandPage.getSortDirection(), demandPage.getSortBy());
        return PageRequest.of(demandPage.getPageNumber(), demandPage.getPageSize(), sort);
    }


    private Predicate getPredicate(DemandPlanningCardCriteria demandSearchCriteria, Root<DemandPlanning> rootDemand) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(demandSearchCriteria.getRefDemandPlanning())) {
            predicates.add(criteriaBuilder.like(rootDemand.get("refDemandPlanning"), "%" + demandSearchCriteria.getRefDemandPlanning() + "%"));

        }
        if (Objects.nonNull(demandSearchCriteria.getStartedDate())) {
            predicates.add(criteriaBuilder.equal(rootDemand.get("startedDate"),demandSearchCriteria.stringToLocalDate(demandSearchCriteria.getStartedDate())));


        }
        if (Objects.nonNull(demandSearchCriteria.getEndDate())) {
            predicates.add(criteriaBuilder.equal(rootDemand.get("endDate"), demandSearchCriteria.getEndDate()));


        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }


    private void setOrder(DemandPlanningPage demandPlanningPage, CriteriaQuery<DemandPlanning> criteriaQuery, Root<DemandPlanning> demandPlanningRoot) {
        if (demandPlanningPage.getSortDirection().equals(Sort.Direction.ASC)) {

            criteriaQuery.orderBy(criteriaBuilder.asc(demandPlanningRoot.get(demandPlanningPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(demandPlanningRoot.get(demandPlanningPage.getSortBy())));
        }

    }
}
