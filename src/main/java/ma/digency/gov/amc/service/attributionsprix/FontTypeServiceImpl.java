package ma.digency.gov.amc.service.attributionsprix;


import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.FontTypeRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.FontType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class FontTypeServiceImpl implements FontTypeService{

    private  final FontTypeRepository fontTypeRepository;

    @Override
    public FontType findFontTypeByRefFontType(String refFontType) {
        return fontTypeRepository.findFontTypeByRefFontType(refFontType);
    }
}
