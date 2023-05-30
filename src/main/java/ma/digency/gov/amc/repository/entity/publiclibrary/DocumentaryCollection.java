package ma.digency.gov.amc.repository.entity.publiclibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentaryCollection extends AcmAbstractAuditEntity {
    protected static final String DOCUMENTARY_COLLECTION_REF_DOCUMENTARY_COLLECTION= "ref_documentary_collection";


    @Column(name = "ref_documentary_collection")
    private String refDocumentaryCollection;

    private Integer numberTitlesPerLanguageArabic;

    private Integer numberTitlesPerLanguageAmazigh;

    private Integer numberTitlesPerLanguageEnglish;

    private Integer numberTitlesPerLanguageSpanish;

    private Integer numberTitlesPerLanguageOtherLanguage;

    private String multimeadiaFund;

    private Integer generalTotalDocumentaryHoldings;

    private Integer numberOfTitlesPerThemeGeneral;

    private Integer numberOfTitlesPerThemePhilosophyPsychology;

    private Integer numberOfTitlesPerThemeSocialScience;

    private Integer numberOfTitlesPerThemeLanguages;

    private Integer numberOfTitlesPerThemePureSciences;

    private Integer numberOfTitlesPerThemeAppliedScience;

    private Integer numberOfTitlesPerThemeArts;

    private Integer numberOfTitlesPerThemeLiterature;

    private Integer numberOfTitlesPerThemeHistoryGeography;

    private Integer generalTotalOfDocumentaryHoldings;

    private LocalDate dateOfLastUpdate;

    private Integer numberOfNewlyAcquiredShares;

    private Integer rateDevelopmentDocumentaryCollection;

}
