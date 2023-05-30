package ma.digency.gov.amc.service.siel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SielDocumentResource {
	SIEL_DEMAND_PARTICIPATION_AND_ENGAGEMENT_FR_DOC(
			"siel/demande-participation-engagement-fr.docx"),
	SIEL_DEMAND_PARTICIPATION_AND_ENGAGEMENT_AR_DOC(
			"siel/demande-participation-engagement-ar.docx"),
	SIEL_PURCHASE_ORDER_FR_DOC(
			"siel/bon-de-commande.docx"),
	SIEL_PUBLICATION_MODULE("siel/siel-publication-module.xlsx"),
	SIEL_FORIEGN_EXHIBITOR("siel/form-visa-exposant-etranger.xlsx");


	private final String classPath;

}
