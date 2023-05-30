package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.Demand;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DemandPriceRepository extends GenericRepository<DemandPrice,Long>{

    DemandPrice findDemandPriceByRefDemand(String ref);

    @Query("SELECT d FROM DemandPrice d where d.key='AHASSAN2'")
    Page<DemandPrice> findDemandAwardHassan2(Pageable pageable);

    @Query("SELECT d FROM DemandPrice d where d.key='AHONORARY'")
    Page<DemandPrice> findDemandAwardHonorary(Pageable pageable);

    @Query("SELECT d FROM DemandPrice d where d.key='ATHEATER'")
    Page<DemandPrice> findDemandAwardTheater(Pageable pageable);

    @Query("SELECT d FROM DemandPrice d where d.key='ABOOK'")
    Page<DemandPrice> findDemandAwardBook(Pageable pageable);

    @Query("SELECT d FROM DemandPrice d where d.key='AHASSAN2'")
    List<DemandPrice> findAllDemandAwardHassa2();
}
