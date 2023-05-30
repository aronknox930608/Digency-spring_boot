package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.BookPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookPriceRepository extends GenericRepository<BookPrice,Long>{

    @Query("SELECT b FROM BookPrice b WHERE b.author=:author")
    List<BookPrice> findBookByAuthor(@Param("author") String author);

    BookPrice findBookByRefBook(String refBook);
}
