package ma.digency.gov.amc.dto.publiclibrary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@Validated
public class DocumentaryCollectionRequest {


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
