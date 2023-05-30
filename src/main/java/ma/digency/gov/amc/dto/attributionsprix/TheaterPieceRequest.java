package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TheaterPieceRequest {

    private String title;

    private String theaterTroupeName;

    private String textTheaterPiece;

    private LocalDate date;

}
