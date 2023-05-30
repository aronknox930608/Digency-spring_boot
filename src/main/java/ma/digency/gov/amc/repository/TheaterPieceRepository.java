package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.TheaterPiece;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterPieceRepository extends GenericRepository<TheaterPiece,Long>{

    TheaterPiece findTheaterPieceByRefTheaterPiece(String refTheaterPiece);
}
