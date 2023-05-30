package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.shared.Commission;

import java.util.Optional;

public interface CommissionRepository extends GenericRepository<Commission, Long> {

    Optional<Commission> findCommissionByRefCommission(String refCommission);

}
