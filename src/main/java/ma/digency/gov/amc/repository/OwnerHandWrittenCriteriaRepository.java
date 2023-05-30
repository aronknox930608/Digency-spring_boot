package ma.digency.gov.amc.repository;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.attributionsprix.OwnerHandWrittenSearchCriteria;
import ma.digency.gov.amc.repository.SearchFiles.OwnerHandWrittenPage;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
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
public class OwnerHandWrittenCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public OwnerHandWrittenCriteriaRepository(EntityManager entityManager){
        this.entityManager=entityManager;
        this.criteriaBuilder=getEntityManager().getCriteriaBuilder();
    }


    public Page<OwnerHandWritten> findAllWithFilters(OwnerHandWrittenPage ownerHandWrittenPage, OwnerHandWrittenSearchCriteria ownerHandWrittenSearchCriteria){
        CriteriaQuery<OwnerHandWritten> criteriaQuery=criteriaBuilder.createQuery(OwnerHandWritten.class);
        Root<OwnerHandWritten> ownerHandWrittenRoot=criteriaQuery.from(OwnerHandWritten.class);
        Predicate predicate=getPredicate(ownerHandWrittenSearchCriteria,ownerHandWrittenRoot);
        criteriaQuery.where(predicate);
        setOrder(ownerHandWrittenPage,criteriaQuery,ownerHandWrittenRoot);
        TypedQuery<OwnerHandWritten> typedQuery=entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(ownerHandWrittenPage.getPageNumber()*ownerHandWrittenPage.getPageSize());
        typedQuery.setMaxResults(ownerHandWrittenPage.getPageSize());
        Pageable pageable=getPageable(ownerHandWrittenPage);
        long ownerHandWrittenCount=getOwnerHandWrittenCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(),pageable,ownerHandWrittenCount);
    }

    private Predicate getPredicate(OwnerHandWrittenSearchCriteria ownerHandWrittenSearchCriteria,Root<OwnerHandWritten> ownerHandWrittenRoot){

        List<Predicate> predicates=new ArrayList<>();
        if(Objects.nonNull(ownerHandWrittenSearchCriteria.getCin())){
            predicates.add(criteriaBuilder.like(ownerHandWrittenRoot.get("cin"), "%" + ownerHandWrittenSearchCriteria.getCin() + "%" ));
        }

        if(Objects.nonNull(ownerHandWrittenSearchCriteria.getFirstName())){
            predicates.add(criteriaBuilder.like(ownerHandWrittenRoot.get("firstName"), "%" + ownerHandWrittenSearchCriteria.getFirstName() + "%" ));
        }

        if(Objects.nonNull(ownerHandWrittenSearchCriteria.getLastName())){
            predicates.add(criteriaBuilder.like(ownerHandWrittenRoot.get("lastName"), "%" + ownerHandWrittenSearchCriteria.getLastName() + "%" ));
        }

        if(Objects.nonNull(ownerHandWrittenSearchCriteria.getFirstNameAr())){
            predicates.add(criteriaBuilder.like(ownerHandWrittenRoot.get("firstNameAr"), "%" + ownerHandWrittenSearchCriteria.getFirstNameAr() + "%" ));
        }

        if(Objects.nonNull(ownerHandWrittenSearchCriteria.getLastNameAr())){
            predicates.add(criteriaBuilder.like(ownerHandWrittenRoot.get("lastNameAr"), "%" + ownerHandWrittenSearchCriteria.getLastNameAr() + "%" ));
        }

        if(Objects.nonNull(ownerHandWrittenSearchCriteria.getGender())){
            predicates.add(criteriaBuilder.like(ownerHandWrittenRoot.get("gender"), "%" + ownerHandWrittenSearchCriteria.getGender() + "%" ));
        }

        if(Objects.nonNull(ownerHandWrittenSearchCriteria.getEmail())){
            predicates.add(criteriaBuilder.like(ownerHandWrittenRoot.get("email"), "%" + ownerHandWrittenSearchCriteria.getEmail() + "%" ));
        }

        if(Objects.nonNull(ownerHandWrittenSearchCriteria.getPhone())){
            predicates.add(criteriaBuilder.like(ownerHandWrittenRoot.get("phone"), "%" + ownerHandWrittenSearchCriteria.getPhone() + "%" ));
        }

        if(Objects.nonNull(ownerHandWrittenSearchCriteria.getRib())){
            predicates.add(criteriaBuilder.like(ownerHandWrittenRoot.get("rib"), "%" + ownerHandWrittenSearchCriteria.getRib() + "%" ));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(OwnerHandWrittenPage ownerHandWrittenPage,CriteriaQuery<OwnerHandWritten> criteriaQuery,Root<OwnerHandWritten> ownerHandWrittenRoot){
        if(ownerHandWrittenPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(ownerHandWrittenRoot.get(ownerHandWrittenPage.getSortBy())));
        }
        else{
            criteriaQuery.orderBy(criteriaBuilder.desc(ownerHandWrittenRoot.get(ownerHandWrittenPage.getSortBy())));
        }
    }

    private Pageable getPageable(OwnerHandWrittenPage ownerHandWrittenPage){
        Sort sort=Sort.by(ownerHandWrittenPage.getSortDirection(), ownerHandWrittenPage.getSortBy());
        return PageRequest.of(ownerHandWrittenPage.getPageNumber(),ownerHandWrittenPage.getPageSize(),sort);
    }

    private long getOwnerHandWrittenCount(Predicate predicate){
        CriteriaQuery<Long> coutnQuery=criteriaBuilder.createQuery(Long.class);
        Root<OwnerHandWritten> ownerHandWrittenRoot=coutnQuery.from(OwnerHandWritten.class);
        coutnQuery.select(criteriaBuilder.count(ownerHandWrittenRoot)).where(predicate);
        return entityManager.createQuery(coutnQuery).getSingleResult();
    }

}

