package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.attributionsprix.AwardHonorary;

public interface AwardHonoraryRepository extends GenericRepository<AwardHonorary, Long>{

    AwardHonorary findAwardHonoraryByRefAwardHonorary(String refAward);
}
