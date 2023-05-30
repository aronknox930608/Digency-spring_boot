package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.artistCard.Card;
import org.hibernate.criterion.CriteriaQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public interface CardRepository extends GenericRepository<Card, Long>, JpaSpecificationExecutor<Card> {

  @Override
  Page<Card> findAll(Specification<Card> spec, Pageable page);
  Card findCardByRefCard(String refCard);


}
