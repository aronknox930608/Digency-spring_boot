package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;

import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsprix.FontType;
import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptType;
import ma.digency.gov.amc.repository.entity.attributionsprix.OwnerHandWritten;
import org.springframework.validation.annotation.Validated;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@Validated
public class ManuscriptInformationRequest {

    private String documentsSubject;

    private String issuingAuthority;

    private String authoritiesConcerned;

    private String type;

    private String fontType;

    private String owner;

    private String demand;

    private LocalDate writingDate;

    private String bibliography;

    private String manuscriptTitle;

    private String authorName;

    private String introduction;

    private String conclusion;

    private int papersNumber;

    private float size;

    private String rule;

    private LocalDate authorshipDate;

    private String transcriberName;

    private LocalDate copyDate;

    private String explanation;

    private LocalDate creationDate;

    private String printerName;

    private LocalDate printDate;

    private String ink;

    private String category;

    private String holderType;

}
