package ma.digency.gov.amc.repository;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.attributionsprix.DemandAwardSearchCriteria;
import ma.digency.gov.amc.repository.SearchFiles.DemandPage;
import ma.digency.gov.amc.repository.entity.Demand;
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

@Getter
@Setter
@Repository
public class DemandAwardBookCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public DemandAwardBookCriteriaRepository(EntityManager entityManager){
        this.entityManager=entityManager;
        this.criteriaBuilder=getEntityManager().getCriteriaBuilder();
    }

    public Page<Demand> findAllWithFilters(DemandPage demandPage, DemandAwardSearchCriteria demandAwardBookSearchCriteria){
        CriteriaQuery<Demand> criteriaQuery=criteriaBuilder.createQuery(Demand.class);
        Root<Demand> demandAwardBookRoot=criteriaQuery.from(Demand.class);
        Predicate predicate=getPredicate(demandAwardBookSearchCriteria,demandAwardBookRoot);
        criteriaQuery.where(predicate);
        setOrder(demandPage,criteriaQuery,demandAwardBookRoot);
        TypedQuery<Demand> typedQuery=entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(demandPage.getPageNumber()*demandPage.getPageSize());
        typedQuery.setMaxResults(demandPage.getPageSize());
        Pageable pageable=getPageable(demandPage);
        long demandAwardBookCount=getDemandAwardBookCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(),pageable,demandAwardBookCount);
    }

    private Predicate getPredicate(DemandAwardSearchCriteria demandAwardBook2SearchCriteria, Root<Demand> demandRoot){

        List<Predicate> predicates=new ArrayList<>();

        predicates.add(criteriaBuilder.like(demandRoot.get("writer").get("refArtistAccount"), "%ARTS%"));

        if(Objects.nonNull(demandAwardBook2SearchCriteria.getStatus())){
            predicates.add(criteriaBuilder.like(demandRoot.get("status"), "%" + demandAwardBook2SearchCriteria.getStatus() + "%" ));
        }

        if(Objects.nonNull(demandAwardBook2SearchCriteria.getDecision_date())){
            predicates.add(criteriaBuilder.like(demandRoot.get("decision_date"), "%" + demandAwardBook2SearchCriteria.getDecision_date() + "%" ));
        }

        if(Objects.nonNull(demandAwardBook2SearchCriteria.getDemandOwnerFirstName())){
            predicates.add(criteriaBuilder.like(demandRoot.get("writer").get("firstName"), "%" + demandAwardBook2SearchCriteria.getDemandOwnerFirstName() + "%" ));
        }

        if(Objects.nonNull(demandAwardBook2SearchCriteria.getDemandOwnerLastName())){
            predicates.add(criteriaBuilder.like(demandRoot.get("writer").get("lastName"), "%" + demandAwardBook2SearchCriteria.getDemandOwnerLastName() + "%" ));
        }

        if(Objects.nonNull(demandAwardBook2SearchCriteria.getAwardType())){
            predicates.add(criteriaBuilder.like(demandRoot.get("bookAward").get("type"), "%" + demandAwardBook2SearchCriteria.getAwardType() + "%" ));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(DemandPage demandPage,CriteriaQuery<Demand> criteriaQuery,Root<Demand> demandRoot){

        if(demandPage.getSortDirection().equals(Sort.Direction.ASC)){
            if(demandPage.getSortBy().equals("firstName")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("writer").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("lastName")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("writer").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("type")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("bookAward").get(demandPage.getSortBy())));
            }
            else{
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get(demandPage.getSortBy())));
            }

        }
        else{
            if(demandPage.getSortBy().equals("firstName")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("writer").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("lastName")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("writer").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("type")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("bookAward").get(demandPage.getSortBy())));
            }
            else{
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get(demandPage.getSortBy())));
            }
        }
    }

    private Pageable getPageable(DemandPage demandPage){
        Sort sort=Sort.by(demandPage.getSortDirection(), demandPage.getSortBy());
        return PageRequest.of(demandPage.getPageNumber(),demandPage.getPageSize(),sort);
    }

    private long getDemandAwardBookCount(Predicate predicate){
        CriteriaQuery<Long> countQuery=criteriaBuilder.createQuery(Long.class);
        Root<Demand> demandRoot=countQuery.from(Demand.class);
        countQuery.select(criteriaBuilder.count(demandRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
