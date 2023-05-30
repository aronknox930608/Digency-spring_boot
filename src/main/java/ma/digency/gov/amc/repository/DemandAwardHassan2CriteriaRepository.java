package ma.digency.gov.amc.repository;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.attributionsprix.DemandAwardSearchCriteria;
import ma.digency.gov.amc.repository.SearchFiles.DemandPage;
import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Getter
@Setter
@Repository
public class DemandAwardHassan2CriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public DemandAwardHassan2CriteriaRepository(EntityManager entityManager){
        this.entityManager=entityManager;
        this.criteriaBuilder=getEntityManager().getCriteriaBuilder();
    }


    public Page<Demand> findAllWithFilters(DemandPage demandPage, DemandAwardSearchCriteria demandAwardHassan2SearchCriteria){
        CriteriaQuery<Demand> criteriaQuery=criteriaBuilder.createQuery(Demand.class);
        Root<Demand> demandAwardHassan2Root=criteriaQuery.from(Demand.class);
        Predicate predicate=getPredicate(demandAwardHassan2SearchCriteria,demandAwardHassan2Root);
        criteriaQuery.where(predicate);
        setOrder(demandPage,criteriaQuery,demandAwardHassan2Root);
        TypedQuery<Demand> typedQuery=entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(demandPage.getPageNumber()*demandPage.getPageSize());
        typedQuery.setMaxResults(demandPage.getPageSize());
        Pageable pageable=getPageable(demandPage);
        long demandAwardHassan2Count=getDemandAwardHassan2Count(predicate);

        return new PageImpl<>(typedQuery.getResultList(),pageable,demandAwardHassan2Count);
    }

    private Predicate getPredicate(DemandAwardSearchCriteria demandAwardHassan2SearchCriteria, Root<Demand> demandRoot){

        List<Predicate> predicates=new ArrayList<>();

        predicates.add(criteriaBuilder.like(demandRoot.get("demandOwner").get("refOwnerHandWritten"), "%HANDWR%"));

        if(Objects.nonNull(demandAwardHassan2SearchCriteria.getStatus())){
            predicates.add(criteriaBuilder.like(demandRoot.get("status"), "%" + demandAwardHassan2SearchCriteria.getStatus() + "%" ));
        }

        if(Objects.nonNull(demandAwardHassan2SearchCriteria.getDecision_date())){
            predicates.add(criteriaBuilder.like(demandRoot.get("decision_date"), "%" + demandAwardHassan2SearchCriteria.getDecision_date() + "%" ));
        }

        if(Objects.nonNull(demandAwardHassan2SearchCriteria.getDemandOwnerFirstName())){
            predicates.add(criteriaBuilder.like(demandRoot.get("demandOwner").get("firstName"), "%" + demandAwardHassan2SearchCriteria.getDemandOwnerFirstName() + "%" ));
        }

        if(Objects.nonNull(demandAwardHassan2SearchCriteria.getDemandOwnerLastName())){
            predicates.add(criteriaBuilder.like(demandRoot.get("demandOwner").get("lastName"), "%" + demandAwardHassan2SearchCriteria.getDemandOwnerLastName() + "%" ));
        }

        if(Objects.nonNull(demandAwardHassan2SearchCriteria.getAwardType())){
            predicates.add(criteriaBuilder.like(demandRoot.get("award").get("type"), "%" + demandAwardHassan2SearchCriteria.getAwardType() + "%" ));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(DemandPage demandPage,CriteriaQuery<Demand> criteriaQuery,Root<Demand> demandRoot){
        if(demandPage.getSortDirection().equals(Sort.Direction.ASC)){
            if(demandPage.getSortBy().equals("firstName")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("demandOwner").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("lastName")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("demandOwner").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("type")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("award").get(demandPage.getSortBy())));
            }
            else{
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get(demandPage.getSortBy())));
            }

        }
        else{
            if(demandPage.getSortBy().equals("firstName")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("demandOwner").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("lastName")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("demandOwner").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("type")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("award").get(demandPage.getSortBy())));
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

    private long getDemandAwardHassan2Count(Predicate predicate){
        CriteriaQuery<Long> coutnQuery=criteriaBuilder.createQuery(Long.class);
        Root<Demand> demandRoot=coutnQuery.from(Demand.class);
        coutnQuery.select(criteriaBuilder.count(demandRoot)).where(predicate);
        return entityManager.createQuery(coutnQuery).getSingleResult();
    }

}
