package ma.digency.gov.amc.repository.entity.distributor;


import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.searchParticipant.DistributorSearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.LibrarySearchCriteria;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.printer.Printer;
import ma.digency.gov.amc.repository.entity.search.DistributorPage;
import ma.digency.gov.amc.repository.entity.search.LibraryPage;
import ma.digency.gov.amc.repository.entity.search.PrinterPage;
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
public class DistributorCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public DistributorCriteriaRepository(EntityManager entityManager){

        this.entityManager=entityManager;
        this.criteriaBuilder=getEntityManager().getCriteriaBuilder();

    }


    public Page<Distributor> findAllWithFilters(DistributorPage distributorPage, DistributorSearchCriteria distributorSearchCriteria){

        CriteriaQuery<Distributor> criteriaQuery=criteriaBuilder.createQuery(Distributor.class);

        Root<Distributor> distributorRoot=criteriaQuery.from(Distributor.class);

        Predicate predicate = getPredicate(distributorSearchCriteria,distributorRoot);

        criteriaQuery.where(predicate);

        setOrder(distributorPage,criteriaQuery,distributorRoot);

        TypedQuery<Distributor> typedQuery=entityManager.createQuery(criteriaQuery);

        typedQuery.setFirstResult(distributorPage.getPageNumber()*distributorPage.getPageSize());

        typedQuery.setMaxResults(distributorPage.getPageSize());

        Pageable pageable=getPageable(distributorPage);

        long libraryCount=getDistributorCount(predicate);


        return new PageImpl<>(typedQuery.getResultList(),pageable,libraryCount);

    }


    private Predicate getPredicate(DistributorSearchCriteria distributorSearchCriteria, Root<Distributor> distributorRoot){


        List<Predicate> predicates=new ArrayList<>();


        predicates.add(criteriaBuilder.like(distributorRoot.get("refDistributor"), "%DISTR%"));


        if(Objects.nonNull(distributorSearchCriteria.getBusinessName())){

            predicates.add(criteriaBuilder.like(distributorRoot.get("businessName"), "%" + distributorSearchCriteria.getBusinessName() + "%" ));

        }



        if(Objects.nonNull(distributorSearchCriteria.getLineOfBusiness())){

            predicates.add(criteriaBuilder.like(distributorRoot.get("lineOfBusiness"), "%" + distributorSearchCriteria.getLineOfBusiness() + "%" ));

        }


        if(Objects.nonNull(distributorSearchCriteria.getOwnerName())){

            predicates.add(criteriaBuilder.like(distributorRoot.get("ownerName"), "%" + distributorSearchCriteria.getOwnerName() + "%" ));

        }


        if(Objects.nonNull(distributorSearchCriteria.getBusinessRegisterNumber())){

            predicates.add(criteriaBuilder.like(distributorRoot.get("businessRegisterNumber"), "%" + distributorSearchCriteria.getBusinessRegisterNumber() + "%" ));

        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }


    private void setOrder(DistributorPage distributorPage, CriteriaQuery<Distributor> criteriaQuery, Root<Distributor> distributorRoot){


        if(distributorPage.getSortDirection().equals(Sort.Direction.ASC)){

            if(distributorPage.getSortBy().equals("businessName")){

                criteriaQuery.orderBy(criteriaBuilder.asc(distributorRoot.get(distributorPage.getSortBy())));

            }

            else if(distributorPage.getSortBy().equals("lineOfBusiness")){

                criteriaQuery.orderBy(criteriaBuilder.asc(distributorRoot.get(distributorPage.getSortBy())));

            }

            else if(distributorPage.getSortBy().equals("ownerName")){

                criteriaQuery.orderBy(criteriaBuilder.asc(distributorRoot.get(distributorPage.getSortBy())));

            }

            else{

                criteriaQuery.orderBy(criteriaBuilder.asc(distributorRoot.get(distributorPage.getSortBy())));

            }


        }

        else{

            if(distributorPage.getSortBy().equals("businessName")){

                criteriaQuery.orderBy(criteriaBuilder.desc(distributorRoot.get(distributorPage.getSortBy())));

            }

            else if(distributorPage.getSortBy().equals("lineOfBusiness")){

                criteriaQuery.orderBy(criteriaBuilder.desc(distributorRoot.get(distributorPage.getSortBy())));

            }

            else if(distributorPage.getSortBy().equals("ownerName")){

                criteriaQuery.orderBy(criteriaBuilder.desc(distributorRoot.get(distributorPage.getSortBy())));

            }

            else{

                criteriaQuery.orderBy(criteriaBuilder.desc(distributorRoot.get(distributorPage.getSortBy())));

            }

        }

    }


    private Pageable getPageable(DistributorPage distributorPage){

        Sort sort=Sort.by(distributorPage.getSortDirection(), distributorPage.getSortBy());

        return PageRequest.of(distributorPage.getPageNumber(),distributorPage.getPageSize(),sort);

    }


    private long getDistributorCount(Predicate predicate){

        CriteriaQuery<Long> countQuery=criteriaBuilder.createQuery(Long.class);

        Root<Distributor> printerRoot=countQuery.from(Distributor.class);

        countQuery.select(criteriaBuilder.count(printerRoot)).where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();

    }


}