package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerHandWrittenRepository extends GenericRepository<OwnerHandWritten, Long>{

   OwnerHandWritten findOwnerHandWrittenByRefOwnerHandWritten(String ref);

   OwnerHandWritten findOwnerHandWrittenByCin(String cin);

   @Query("SELECT o FROM OwnerHandWritten o WHERE :attributes LIKE %:keyword%")
   List<OwnerHandWritten> findAllOwner(@Param("keyword") String keyword, @Param("attributes") String attributes);

}
