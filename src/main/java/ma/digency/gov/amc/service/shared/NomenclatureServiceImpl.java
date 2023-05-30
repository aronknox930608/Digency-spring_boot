package ma.digency.gov.amc.service.shared;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.NomenclatureRepository;
import ma.digency.gov.amc.repository.NomenclatureValuesRepository;
import ma.digency.gov.amc.repository.entity.shared.Nomenclature;
import ma.digency.gov.amc.repository.entity.shared.NomenclatureValues;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {

    private final NomenclatureRepository nomenclatureRepository;

    private final NomenclatureValuesRepository nomenclatureValuesRepository;


    @Override
    public Nomenclature getAllNomenclatureByFamily(String family) {
        return nomenclatureRepository.findByFamily(family);
    }

    @Override
    public List<Nomenclature> getAllNomenclature() {
        return nomenclatureRepository.findAll();
    }

    @Override
    public NomenclatureValues findNomenclatureValue(Long in) {
        return nomenclatureValuesRepository.findById(in).orElseThrow(()-> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PROJECT_DOMAIN_NOT_FOUND));
    }
}
