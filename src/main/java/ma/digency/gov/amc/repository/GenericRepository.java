package ma.digency.gov.amc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GenericRepository<T, I extends Serializable>
        extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {

}
