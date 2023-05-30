package ma.digency.gov.amc.repository.entity.printer;


import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.searchParticipant.LibrarySearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.PrinterSearchCriteria;
import ma.digency.gov.amc.repository.entity.library.Library;
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
public class PrinterCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public PrinterCriteriaRepository(EntityManager entityManager){

        this.entityManager=entityManager;
        this.criteriaBuilder=getEntityManager().getCriteriaBuilder();

    }


    public Page<Printer> findAllWithFilters(PrinterPage printerPage, PrinterSearchCriteria printerSearchCriteria){

        CriteriaQuery<Printer> criteriaQuery=criteriaBuilder.createQuery(Printer.class);

        Root<Printer> printerRoot=criteriaQuery.from(Printer.class);

        Predicate predicate = getPredicate(printerSearchCriteria,printerRoot);

        criteriaQuery.where(predicate);

        setOrder(printerPage,criteriaQuery,printerRoot);

        TypedQuery<Printer> typedQuery=entityManager.createQuery(criteriaQuery);

        typedQuery.setFirstResult(printerPage.getPageNumber()*printerPage.getPageSize());

        typedQuery.setMaxResults(printerPage.getPageSize());

        Pageable pageable=getPageable(printerPage);

        long printerCount=getPrinterCount(predicate);


        return new PageImpl<>(typedQuery.getResultList(),pageable,printerCount);

    }


    private Predicate getPredicate(PrinterSearchCriteria printerSearchCriteria, Root<Printer> printerRoot){


        List<Predicate> predicates=new ArrayList<>();


        predicates.add(criteriaBuilder.like(printerRoot.get("refPrinter"), "%PRINT%"));


        if(Objects.nonNull(printerSearchCriteria.getBusinessName())){

            predicates.add(criteriaBuilder.like(printerRoot.get("businessName"), "%" + printerSearchCriteria.getBusinessName() + "%" ));

        }



        if(Objects.nonNull(printerSearchCriteria.getLineOfBusiness())){

            predicates.add(criteriaBuilder.like(printerRoot.get("lineOfBusiness"), "%" + printerSearchCriteria.getLineOfBusiness() + "%" ));

        }


        if(Objects.nonNull(printerSearchCriteria.getOwnerName())){

            predicates.add(criteriaBuilder.like(printerRoot.get("ownerName"), "%" + printerSearchCriteria.getOwnerName() + "%" ));

        }


        if(Objects.nonNull(printerSearchCriteria.getBusinessRegisterNumber())){

            predicates.add(criteriaBuilder.like(printerRoot.get("businessRegisterNumber"), "%" + printerSearchCriteria.getBusinessRegisterNumber() + "%" ));

        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }


    private void setOrder(PrinterPage printerPage,CriteriaQuery<Printer> criteriaQuery,Root<Printer> printerRoot){


        if(printerPage.getSortDirection().equals(Sort.Direction.ASC)){

            if(printerPage.getSortBy().equals("businessName")){

                criteriaQuery.orderBy(criteriaBuilder.asc(printerRoot.get(printerPage.getSortBy())));

            }

            else if(printerPage.getSortBy().equals("lineOfBusiness")){

                criteriaQuery.orderBy(criteriaBuilder.asc(printerRoot.get(printerPage.getSortBy())));

            }

            else if(printerPage.getSortBy().equals("ownerName")){

                criteriaQuery.orderBy(criteriaBuilder.asc(printerRoot.get(printerPage.getSortBy())));

            }

            else{

                criteriaQuery.orderBy(criteriaBuilder.asc(printerRoot.get(printerPage.getSortBy())));

            }


        }

        else{

            if(printerPage.getSortBy().equals("businessName")){

                criteriaQuery.orderBy(criteriaBuilder.desc(printerRoot.get(printerPage.getSortBy())));

            }

            else if(printerPage.getSortBy().equals("lineOfBusiness")){

                criteriaQuery.orderBy(criteriaBuilder.desc(printerRoot.get(printerPage.getSortBy())));

            }

            else if(printerPage.getSortBy().equals("ownerName")){

                criteriaQuery.orderBy(criteriaBuilder.desc(printerRoot.get(printerPage.getSortBy())));

            }

            else{

                criteriaQuery.orderBy(criteriaBuilder.desc(printerRoot.get(printerPage.getSortBy())));

            }

        }

    }


    private Pageable getPageable(PrinterPage printerPage){

        Sort sort=Sort.by(printerPage.getSortDirection(), printerPage.getSortBy());

        return PageRequest.of(printerPage.getPageNumber(),printerPage.getPageSize(),sort);

    }


    private long getPrinterCount(Predicate predicate){

        CriteriaQuery<Long> countQuery=criteriaBuilder.createQuery(Long.class);

        Root<Printer> printerRoot=countQuery.from(Printer.class);

        countQuery.select(criteriaBuilder.count(printerRoot)).where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();

    }


}


