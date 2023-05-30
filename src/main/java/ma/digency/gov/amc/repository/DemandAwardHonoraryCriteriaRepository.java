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

@Repository
@Getter
@Setter
public class DemandAwardHonoraryCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public DemandAwardHonoraryCriteriaRepository(EntityManager entityManager){
        this.entityManager=entityManager;
        this.criteriaBuilder=getEntityManager().getCriteriaBuilder();
    }


    public Page<Demand> findAllWithFilters(DemandPage demandPage, DemandAwardSearchCriteria demandAwardHonorarySearchCriteria){
        CriteriaQuery<Demand> criteriaQuery=criteriaBuilder.createQuery(Demand.class);
        Root<Demand> demandAwardHnoraryRoot=criteriaQuery.from(Demand.class);
        Predicate predicate=getPredicate(demandAwardHonorarySearchCriteria,demandAwardHnoraryRoot);
        criteriaQuery.where(predicate);
        setOrder(demandPage,criteriaQuery,demandAwardHnoraryRoot);
        TypedQuery<Demand> typedQuery=entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(demandPage.getPageNumber()*demandPage.getPageSize());
        typedQuery.setMaxResults(demandPage.getPageSize());
        Pageable pageable=getPageable(demandPage);
        long demandAwardHonoraryCount=getDemandAwardHonoraryCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(),pageable,demandAwardHonoraryCount);
    }

    private Predicate getPredicate(DemandAwardSearchCriteria demandAwardHonorarySearchCriteria, Root<Demand> demandRoot){

        List<Predicate> predicates=new ArrayList<>();

        predicates.add(criteriaBuilder.like(demandRoot.get("candidate").get("refArtistAccount"), "%ARTS%"));

        if(Objects.nonNull(demandAwardHonorarySearchCriteria.getStatus())){
            predicates.add(criteriaBuilder.like(demandRoot.get("status"), "%" + demandAwardHonorarySearchCriteria.getStatus() + "%" ));
        }

        if(Objects.nonNull(demandAwardHonorarySearchCriteria.getDecision_date())){
            predicates.add(criteriaBuilder.like(demandRoot.get("decision_date"), "%" + demandAwardHonorarySearchCriteria.getDecision_date() + "%" ));
        }

        if(Objects.nonNull(demandAwardHonorarySearchCriteria.getDemandOwnerFirstName())){
            predicates.add(criteriaBuilder.like(demandRoot.get("candidate").get("firstName"), "%" + demandAwardHonorarySearchCriteria.getDemandOwnerFirstName() + "%" ));
        }

        if(Objects.nonNull(demandAwardHonorarySearchCriteria.getDemandOwnerLastName())){
            predicates.add(criteriaBuilder.like(demandRoot.get("candidate").get("lastName"), "%" + demandAwardHonorarySearchCriteria.getDemandOwnerLastName() + "%" ));
        }

        if(Objects.nonNull(demandAwardHonorarySearchCriteria.getAwardType())){
            predicates.add(criteriaBuilder.like(demandRoot.get("honoraryAward").get("type"), "%" + demandAwardHonorarySearchCriteria.getAwardType() + "%" ));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(DemandPage demandPage,CriteriaQuery<Demand> criteriaQuery,Root<Demand> demandRoot){
        if(demandPage.getSortDirection().equals(Sort.Direction.ASC)){
            if(demandPage.getSortBy().equals("firstName")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("candidate").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("lastName")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("candidate").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("type")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("honoraryAward").get(demandPage.getSortBy())));
            }
            else{
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get(demandPage.getSortBy())));
            }

        }
        else{
            if(demandPage.getSortBy().equals("firstName")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("candidate").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("lastName")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("candidate").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("type")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("honoraryAward").get(demandPage.getSortBy())));
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

    private long getDemandAwardHonoraryCount(Predicate predicate){
        CriteriaQuery<Long> countQuery=criteriaBuilder.createQuery(Long.class);
        Root<Demand> demandRoot=countQuery.from(Demand.class);
        countQuery.select(criteriaBuilder.count(demandRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
