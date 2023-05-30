package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.attributionsprix.AwardHonorary;

import java.util.List;

public interface AwardHonoraryService {

    AwardHonorary findAwardHonoraryByRef(String refAwardHonorary);

    AwardHonorary createOrUpdate(AwardHonorary awardHonorary);

    List<AwardHonorary> findAllAwardHonorary();

    void delete(AwardHonorary awardHonorary);
}
