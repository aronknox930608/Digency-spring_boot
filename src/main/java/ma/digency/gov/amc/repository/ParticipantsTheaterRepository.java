package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.ParticipantsTheater;
import ma.digency.gov.amc.repository.entity.attributionsprix.TheaterPiece;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantsTheaterRepository extends GenericRepository<ParticipantsTheater,Long>{

    List<ParticipantsTheater> findParticipantsTheaterByTheaterPiece(TheaterPiece theaterPiece);

    ParticipantsTheater findParticipantsTheaterByRefParticipant(String refParticipant);

    List<ParticipantsTheater> findParticipantsTheaterByCin(String cin);
}
