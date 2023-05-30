package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.shared.Setting;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppSettingRepository extends GenericRepository<Setting, Long> {

    Optional<Setting> findByKeySetting(String code);
}
