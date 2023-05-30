package ma.digency.gov.amc.repository.entity.author;


import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.searchParticipant.AuthorSearchCriteria;
import ma.digency.gov.amc.repository.entity.search.AuthorPage;

import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import org.springframework.data.domain.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Objects;


@Getter
@Setter
@Repository
public class AuthorCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public AuthorCriteriaRepository(EntityManager entityManager){

        this.entityManager=entityManager;
        this.criteriaBuilder=getEntityManager().getCriteriaBuilder();

    }


    public Page<Author> findAllWithFilters(AuthorPage authorPage, AuthorSearchCriteria authorSearchCriteria){

        CriteriaQuery<Author> criteriaQuery=criteriaBuilder.createQuery(Author.class);

        Root<Author> authorRoot=criteriaQuery.from(Author.class);

        Predicate predicate=getPredicate(authorSearchCriteria,authorRoot);

        criteriaQuery.where(predicate);

        setOrder(authorPage,criteriaQuery,authorRoot);

        TypedQuery<Author> typedQuery=entityManager.createQuery(criteriaQuery);

        typedQuery.setFirstResult(authorPage.getPageNumber()*authorPage.getPageSize());

        typedQuery.setMaxResults(authorPage.getPageSize());

        Pageable pageable=getPageable(authorPage);

        long authorCount=getAuthorCount(predicate);


        return new PageImpl<>(typedQuery.getResultList(),pageable,authorCount);

    }


    private Predicate getPredicate(AuthorSearchCriteria authorSearchCriteria, Root<Author> authorRoot){


        List<Predicate> predicates=new ArrayList<>();


        predicates.add(criteriaBuilder.like(authorRoot.get("refAuthor"), "%AUTH%"));


        if(Objects.nonNull(authorSearchCriteria.getFullName())){

            predicates.add(criteriaBuilder.like(authorRoot.get("fullName"), "%" + authorSearchCriteria.getFullName() + "%" ));

        }



        if(Objects.nonNull(authorSearchCriteria.getCity())){

            predicates.add(criteriaBuilder.like(authorRoot.get("city"), "%" + authorSearchCriteria.getCity() + "%" ));

        }


        if(Objects.nonNull(authorSearchCriteria.getEmail())){

            predicates.add(criteriaBuilder.like(authorRoot.get("email"), "%" + authorSearchCriteria.getEmail() + "%" ));

        }


        if(Objects.nonNull(authorSearchCriteria.getGender())){

            predicates.add(criteriaBuilder.like(authorRoot.get("gender"), "%" + authorSearchCriteria.getGender() + "%" ));

        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }


    private void setOrder(AuthorPage authorPage,CriteriaQuery<Author> criteriaQuery,Root<Author> authorRoot){


        if(authorPage.getSortDirection().equals(Sort.Direction.ASC)){

            if(authorPage.getSortBy().equals("fullName")){

                criteriaQuery.orderBy(criteriaBuilder.asc(authorRoot.get(authorPage.getSortBy())));

            }

            else if(authorPage.getSortBy().equals("city")){

                criteriaQuery.orderBy(criteriaBuilder.asc(authorRoot.get(authorPage.getSortBy())));

            }

            else if(authorPage.getSortBy().equals("gender")){

                criteriaQuery.orderBy(criteriaBuilder.asc(authorRoot.get(authorPage.getSortBy())));

            }

            else{

                criteriaQuery.orderBy(criteriaBuilder.asc(authorRoot.get(authorPage.getSortBy())));

            }


        }

        else{

            if(authorPage.getSortBy().equals("fullName")){

                criteriaQuery.orderBy(criteriaBuilder.desc(authorRoot.get(authorPage.getSortBy())));

            }

            else if(authorPage.getSortBy().equals("city")){

                criteriaQuery.orderBy(criteriaBuilder.desc(authorRoot.get(authorPage.getSortBy())));

            }

            else if(authorPage.getSortBy().equals("gender")){

                criteriaQuery.orderBy(criteriaBuilder.desc(authorRoot.get(authorPage.getSortBy())));

            }

            else{

                criteriaQuery.orderBy(criteriaBuilder.desc(authorRoot.get(authorPage.getSortBy())));

            }

        }

    }


    private Pageable getPageable(AuthorPage authorPage){

        Sort sort=Sort.by(authorPage.getSortDirection(), authorPage.getSortBy());

        return PageRequest.of(authorPage.getPageNumber(),authorPage.getPageSize(),sort);

    }


    private long getAuthorCount(Predicate predicate){

        CriteriaQuery<Long> countQuery=criteriaBuilder.createQuery(Long.class);

        Root<Author> authorRoot=countQuery.from(Author.class);

        countQuery.select(criteriaBuilder.count(authorRoot)).where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();

    }


}


