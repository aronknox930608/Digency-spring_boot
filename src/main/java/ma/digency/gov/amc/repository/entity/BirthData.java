package ma.digency.gov.amc.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class BirthData implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate birthDate;

    private String birthCountry;

    private String birthCity;

    private String nationality;




}
