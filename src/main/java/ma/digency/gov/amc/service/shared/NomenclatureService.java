package ma.digency.gov.amc.service.shared;

import ma.digency.gov.amc.repository.entity.shared.Nomenclature;
import ma.digency.gov.amc.repository.entity.shared.NomenclatureValues;

import java.util.List;

public interface NomenclatureService {

    Nomenclature getAllNomenclatureByFamily(String family);

    List<Nomenclature> getAllNomenclature();

    NomenclatureValues findNomenclatureValue(Long in);
}
