package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.AwardObtained;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardObtainedRepository extends GenericRepository<AwardObtained, Long>{

    @Query("SELECT b FROM AwardObtained b WHERE b.artist=:author")
    List<AwardObtained> findAwardObtainedByArtist(@Param("author") String refArtist);

    AwardObtained findAwardObtainedByReAwardObtained(String refAwardObtained);

}
