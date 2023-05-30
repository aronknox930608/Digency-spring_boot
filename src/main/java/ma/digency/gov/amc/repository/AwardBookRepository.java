package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.AwardBook;


public interface AwardBookRepository extends GenericRepository<AwardBook, Long>{

    AwardBook findAwardBookByRefAwardBook(String refAwardBook);
}
