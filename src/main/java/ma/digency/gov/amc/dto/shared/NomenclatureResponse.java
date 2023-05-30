package ma.digency.gov.amc.dto.shared;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NomenclatureResponse {

    private String id;

    private String family;

    private String nameAr;

    private String nameFr;

    private List<SubFamilyResponse> values;

}
