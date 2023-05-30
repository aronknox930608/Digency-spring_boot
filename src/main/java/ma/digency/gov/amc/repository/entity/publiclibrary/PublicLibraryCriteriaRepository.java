package ma.digency.gov.amc.repository.entity.publiclibrary;


import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.searchParticipant.LibrarySearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.PublicLibrarySearchCriteria;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.search.LibraryPage;
import ma.digency.gov.amc.repository.entity.search.PublicLibraryPage;
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
public class PublicLibraryCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public PublicLibraryCriteriaRepository(EntityManager entityManager){

        this.entityManager=entityManager;
        this.criteriaBuilder=getEntityManager().getCriteriaBuilder();

    }


    public Page<PublicLibrary> findAllWithFilters(PublicLibraryPage libraryPage, PublicLibrarySearchCriteria librarySearchCriteria){

        CriteriaQuery<PublicLibrary> criteriaQuery=criteriaBuilder.createQuery(PublicLibrary.class);

        Root<PublicLibrary> libraryRoot=criteriaQuery.from(PublicLibrary.class);

        Predicate predicate = getPredicate(librarySearchCriteria,libraryRoot);

        criteriaQuery.where(predicate);

        setOrder(libraryPage,criteriaQuery,libraryRoot);

        TypedQuery<PublicLibrary> typedQuery=entityManager.createQuery(criteriaQuery);

        typedQuery.setFirstResult(libraryPage.getPageNumber()*libraryPage.getPageSize());

        typedQuery.setMaxResults(libraryPage.getPageSize());

        Pageable pageable=getPageable(libraryPage);

        long libraryCount=getLibraryCount(predicate);


        return new PageImpl<>(typedQuery.getResultList(),pageable,libraryCount);

    }


    private Predicate getPredicate(PublicLibrarySearchCriteria librarySearchCriteria, Root<PublicLibrary> libraryRoot){


        List<Predicate> predicates=new ArrayList<>();


        predicates.add(criteriaBuilder.like(libraryRoot.get("refPublicLibrary"), "%PUBLI%"));


        if(Objects.nonNull(librarySearchCriteria.getLibraryName())){

            predicates.add(criteriaBuilder.like(libraryRoot.get("libraryName"), "%" + librarySearchCriteria.getLibraryName() + "%" ));

        }



        if(Objects.nonNull(librarySearchCriteria.getNameOfTheResponsible())){

            predicates.add(criteriaBuilder.like(libraryRoot.get("nameOfTheResponsible"), "%" + librarySearchCriteria.getNameOfTheResponsible() + "%" ));

        }


        if(Objects.nonNull(librarySearchCriteria.getPartner())){

            predicates.add(criteriaBuilder.like(libraryRoot.get("partner"), "%" + librarySearchCriteria.getPartner() + "%" ));

        }


        if(Objects.nonNull(librarySearchCriteria.getLibraryStatus())){

            predicates.add(criteriaBuilder.like(libraryRoot.get("libraryStatus"), "%" + librarySearchCriteria.getLibraryStatus() + "%" ));

        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }


    private void setOrder(PublicLibraryPage libraryPage,CriteriaQuery<PublicLibrary> criteriaQuery,Root<PublicLibrary> libraryRoot){


        if(libraryPage.getSortDirection().equals(Sort.Direction.ASC)){

            if(libraryPage.getSortBy().equals("LibraryName")){

                criteriaQuery.orderBy(criteriaBuilder.asc(libraryRoot.get(libraryPage.getSortBy())));

            }

            else if(libraryPage.getSortBy().equals("nameOfTheResponsible")){

                criteriaQuery.orderBy(criteriaBuilder.asc(libraryRoot.get(libraryPage.getSortBy())));

            }

            else if(libraryPage.getSortBy().equals("partner")){

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

            else if(libraryPage.getSortBy().equals("nameOfTheResponsible")){

                criteriaQuery.orderBy(criteriaBuilder.desc(libraryRoot.get(libraryPage.getSortBy())));

            }

            else if(libraryPage.getSortBy().equals("partner")){

                criteriaQuery.orderBy(criteriaBuilder.desc(libraryRoot.get(libraryPage.getSortBy())));

            }

            else{

                criteriaQuery.orderBy(criteriaBuilder.desc(libraryRoot.get(libraryPage.getSortBy())));

            }

        }

    }


    private Pageable getPageable(PublicLibraryPage libraryPage){

        Sort sort=Sort.by(libraryPage.getSortDirection(), libraryPage.getSortBy());

        return PageRequest.of(libraryPage.getPageNumber(),libraryPage.getPageSize(),sort);

    }


    private long getLibraryCount(Predicate predicate){

        CriteriaQuery<Long> countQuery=criteriaBuilder.createQuery(Long.class);

        Root<PublicLibrary> libraryRoot=countQuery.from(PublicLibrary.class);

        countQuery.select(criteriaBuilder.count(libraryRoot)).where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();

    }


}


