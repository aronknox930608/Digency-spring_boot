package ma.digency.gov.amc.service.siel;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ma.digency.gov.amc.service.shared.DocumentResource;
import ma.digency.gov.amc.service.shared.DocumentToGenerate;
import ma.digency.gov.amc.service.shared.annotation.DocumentField;

@AllArgsConstructor
@ToString
@Builder
@Getter
public class SielParticipationDocument implements DocumentToGenerate {

    private final String startDateEdition;

    private final String startDateEditionFr;

    private final String endDateEdition;

    private final String endDateEditionFr;

    private final String endDate;

    private final String endDateFr;

    private final String establishmentName;

    private final String address;

    private final String responsible;

    private final String phoneNumber;

    private final String faxNumber;

    private final String email;

    private final String representing;

    private final String productsExhibited;

    private final String rib;

    private final String standSize;

    private final String date;

    private final String standEquipped;

    private final String standEmpty;

    private final String totalStand;

    private final String cheque;

    private final String virement;

    private final String pays;

    private final String totalLetterAr;

    private final String totalLetterFr;


    @NotBlank
    private String language;

    @Override
    public DocumentResource getResource() {
        if ("ar".equalsIgnoreCase(language)) {
            return () -> SielDocumentResource.SIEL_DEMAND_PARTICIPATION_AND_ENGAGEMENT_AR_DOC.getClassPath();
        }
        return () -> SielDocumentResource.SIEL_DEMAND_PARTICIPATION_AND_ENGAGEMENT_FR_DOC.getClassPath();

    }
}
