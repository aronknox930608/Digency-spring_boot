package ma.digency.gov.amc.repository.entity.library;


import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.searchParticipant.AuthorSearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.LibrarySearchCriteria;
import ma.digency.gov.amc.repository.entity.search.LibraryPage;
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
public class LibraryCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public LibraryCriteriaRepository(EntityManager entityManager){

        this.entityManager=entityManager;
        this.criteriaBuilder=getEntityManager().getCriteriaBuilder();

    }


    public Page<Library> findAllWithFilters(LibraryPage libraryPage, LibrarySearchCriteria librarySearchCriteria){

        CriteriaQuery<Library> criteriaQuery=criteriaBuilder.createQuery(Library.class);

        Root<Library> libraryRoot=criteriaQuery.from(Library.class);

        Predicate predicate = getPredicate(librarySearchCriteria,libraryRoot);

        criteriaQuery.where(predicate);

        setOrder(libraryPage,criteriaQuery,libraryRoot);

        TypedQuery<Library> typedQuery=entityManager.createQuery(criteriaQuery);

        typedQuery.setFirstResult(libraryPage.getPageNumber()*libraryPage.getPageSize());

        typedQuery.setMaxResults(libraryPage.getPageSize());

        Pageable pageable=getPageable(libraryPage);

        long libraryCount=getLibraryCount(predicate);


        return new PageImpl<>(typedQuery.getResultList(),pageable,libraryCount);

    }


    private Predicate getPredicate(LibrarySearchCriteria librarySearchCriteria, Root<Library> libraryRoot){


        List<Predicate> predicates=new ArrayList<>();


        predicates.add(criteriaBuilder.like(libraryRoot.get("refLibrary"), "%LIBRA%"));


        if(Objects.nonNull(librarySearchCriteria.getLibraryName())){

            predicates.add(criteriaBuilder.like(libraryRoot.get("libraryName"), "%" + librarySearchCriteria.getLibraryName() + "%" ));

        }



        if(Objects.nonNull(librarySearchCriteria.getLibraryOwnerName())){

            predicates.add(criteriaBuilder.like(libraryRoot.get("libraryOwnerName"), "%" + librarySearchCriteria.getLibraryOwnerName() + "%" ));

        }


        if(Objects.nonNull(librarySearchCriteria.getBusinessRegisterNumber())){

            predicates.add(criteriaBuilder.like(libraryRoot.get("businessRegisterNumber"), "%" + librarySearchCriteria.getBusinessRegisterNumber() + "%" ));

        }


        if(Objects.nonNull(librarySearchCriteria.getCnssNumber())){

            predicates.add(criteriaBuilder.like(libraryRoot.get("cnssNumber"), "%" + librarySearchCriteria.getCnssNumber() + "%" ));

        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }


    private void setOrder(LibraryPage libraryPage,CriteriaQuery<Library> criteriaQuery,Root<Library> libraryRoot){


        if(libraryPage.getSortDirection().equals(Sort.Direction.ASC)){

            if(libraryPage.getSortBy().equals("LibraryName")){

                criteriaQuery.orderBy(criteriaBuilder.asc(libraryRoot.get(libraryPage.getSortBy())));

            }

            else if(libraryPage.getSortBy().equals("libraryOwnerName")){

                criteriaQuery.orderBy(criteriaBuilder.asc(libraryRoot.get(libraryPage.getSortBy())));

            }

            else if(libraryPage.getSortBy().equals("businessRegisterNumber")){

                criteriaQuery.orderBy(criteriaBuilder.asc(libraryRoot.get(libraryPage.getSortBy())));

            }

            else{

                criteriaQuery.orderBy(criteriaBuilder.asc(libraryRoot.get(libraryPage.getSortBy())));

            }


        }

        else{

            if(libraryPage.getSortBy().equals("LibraryName")){

                criteriaQuery.orderBy(criteriaBuilder.desc(libraryRoot.get(libraryPage.getSortBy())));

            }

            else if(libraryPage.getSortBy().equals("libraryOwnerName")){

                criteriaQuery.orderBy(criteriaBuilder.desc(libraryRoot.get(libraryPage.getSortBy())));

            }

            else if(libraryPage.getSortBy().equals("businessRegisterNumber")){

                criteriaQuery.orderBy(criteriaBuilder.desc(libraryRoot.get(libraryPage.getSortBy())));

            }

            else{

                criteriaQuery.orderBy(criteriaBuilder.desc(libraryRoot.get(libraryPage.getSortBy())));

            }

        }

    }


    private Pageable getPageable(LibraryPage libraryPage){

        Sort sort=Sort.by(libraryPage.getSortDirection(), libraryPage.getSortBy());

        return PageRequest.of(libraryPage.getPageNumber(),libraryPage.getPageSize(),sort);

    }


    private long getLibraryCount(Predicate predicate){

        CriteriaQuery<Long> countQuery=criteriaBuilder.createQuery(Long.class);

        Root<Library> libraryRoot=countQuery.from(Library.class);

        countQuery.select(criteriaBuilder.count(libraryRoot)).where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();

    }


}


