package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.shared.GeneralMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GeneralMemberRepository extends GenericRepository<GeneralMember, Long>{

    Optional<GeneralMember> findByRefGeneralMember(String refGeneralMember);

    List<GeneralMember> findByRefParent(String refExhibitor);


}
