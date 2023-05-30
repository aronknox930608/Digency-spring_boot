package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.shared.Nomenclature;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NomenclatureRepository extends GenericRepository<Nomenclature,Long> {

    Nomenclature findByFamily(String family);

    List<Nomenclature> findAll();

}
