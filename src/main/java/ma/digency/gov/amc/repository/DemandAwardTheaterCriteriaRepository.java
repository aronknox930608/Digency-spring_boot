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
public class DemandAwardTheaterCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public DemandAwardTheaterCriteriaRepository(EntityManager entityManager){
        this.entityManager=entityManager;
        this.criteriaBuilder=getEntityManager().getCriteriaBuilder();
    }


    public Page<Demand> findAllWithFilters(DemandPage demandPage, DemandAwardSearchCriteria demandAwardTheaterSearchCriteria){
        CriteriaQuery<Demand> criteriaQuery=criteriaBuilder.createQuery(Demand.class);
        Root<Demand> demandAwardTheaterRoot=criteriaQuery.from(Demand.class);
        Predicate predicate=getPredicate(demandAwardTheaterSearchCriteria,demandAwardTheaterRoot);
        criteriaQuery.where(predicate);
        setOrder(demandPage,criteriaQuery,demandAwardTheaterRoot);
        TypedQuery<Demand> typedQuery=entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(demandPage.getPageNumber()*demandPage.getPageSize());
        typedQuery.setMaxResults(demandPage.getPageSize());
        Pageable pageable=getPageable(demandPage);
        long demandAwardTheaterCount=getDemandAwardTheaterCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(),pageable,demandAwardTheaterCount);
    }

    private Predicate getPredicate(DemandAwardSearchCriteria demandAwardTheaterSearchCriteria, Root<Demand> demandRoot){

        List<Predicate> predicates=new ArrayList<>();

        predicates.add(criteriaBuilder.like(demandRoot.get("representativeTheaterPiece").get("refArtistAccount"), "%ARTS%"));

        if(Objects.nonNull(demandAwardTheaterSearchCriteria.getStatus())){
            predicates.add(criteriaBuilder.like(demandRoot.get("status"), "%" + demandAwardTheaterSearchCriteria.getStatus() + "%" ));
        }

        if(Objects.nonNull(demandAwardTheaterSearchCriteria.getDecision_date())){
            predicates.add(criteriaBuilder.like(demandRoot.get("decision_date"), "%" + demandAwardTheaterSearchCriteria.getDecision_date() + "%" ));
        }

        if(Objects.nonNull(demandAwardTheaterSearchCriteria.getDemandOwnerFirstName())){
            predicates.add(criteriaBuilder.like(demandRoot.get("representativeTheaterPiece").get("firstName"), "%" + demandAwardTheaterSearchCriteria.getDemandOwnerFirstName() + "%" ));
        }

        if(Objects.nonNull(demandAwardTheaterSearchCriteria.getDemandOwnerLastName())){
            predicates.add(criteriaBuilder.like(demandRoot.get("representativeTheaterPiece").get("lastName"), "%" + demandAwardTheaterSearchCriteria.getDemandOwnerLastName() + "%" ));
        }

        if(Objects.nonNull(demandAwardTheaterSearchCriteria.getAwardType())){
            predicates.add(criteriaBuilder.like(demandRoot.get("awardTheater").get("type"), "%" + demandAwardTheaterSearchCriteria.getAwardType() + "%" ));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(DemandPage demandPage,CriteriaQuery<Demand> criteriaQuery,Root<Demand> demandRoot){
        if(demandPage.getSortDirection().equals(Sort.Direction.ASC)){
            if(demandPage.getSortBy().equals("firstName")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("representativeTheaterPiece").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("lastName")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("representativeTheaterPiece").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("type")){
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get("awardTheater").get(demandPage.getSortBy())));
            }
            else{
                criteriaQuery.orderBy(criteriaBuilder.asc(demandRoot.get(demandPage.getSortBy())));
            }

        }
        else{
            if(demandPage.getSortBy().equals("firstName")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("representativeTheaterPiece").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("lastName")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("representativeTheaterPiece").get(demandPage.getSortBy())));
            }
            else if(demandPage.getSortBy().equals("type")){
                criteriaQuery.orderBy(criteriaBuilder.desc(demandRoot.get("awardTheater").get(demandPage.getSortBy())));
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

    private long getDemandAwardTheaterCount(Predicate predicate){
        CriteriaQuery<Long> countQuery=criteriaBuilder.createQuery(Long.class);
        Root<Demand> demandRoot=countQuery.from(Demand.class);
        countQuery.select(criteriaBuilder.count(demandRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
