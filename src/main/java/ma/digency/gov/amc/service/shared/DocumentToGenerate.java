package ma.digency.gov.amc.service.shared;

import javax.validation.constraints.NotNull;

public interface DocumentToGenerate {

	@NotNull
	DocumentResource getResource();

}