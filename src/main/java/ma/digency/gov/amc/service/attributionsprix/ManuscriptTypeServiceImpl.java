package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.ManuscriptTypeRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ManuscriptTypeServiceImpl implements ManuscriptTypeService {

    private final ManuscriptTypeRepository manuscriptTypeRepository;


    @Override
    public ManuscriptType findManuscriptTypeByRefManuscriptType(String refManuscriptType) {
        return manuscriptTypeRepository.findManuscriptTypeByRefManuscriptType(refManuscriptType);
    }

    @Override
    public List<ManuscriptType> findAllManuscriptType() {
        return manuscriptTypeRepository.findAll();
    }
}
