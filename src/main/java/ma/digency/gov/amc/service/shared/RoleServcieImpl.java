package ma.digency.gov.amc.service.shared;

import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.RoleRepository;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.repository.entity.shared.Role;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServcieImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByCodeRole(String codeRole) {
        return roleRepository.findRoleByCodeRole(codeRole).orElseThrow(() -> new MinistryOfCultureBusinessException(
                MinistryOfCultureMessage.AMC_ROLE_NOT_FOUND));
    }

    @Override
    public void addRole(Role role) {
        role.setEditable(true);
        role.setRemovable(true);
        role.setStatus(StatusEnum.OPEN);
        roleRepository.save(role);
    }

    @Override
    public Optional<Role> findRoleByRef(String refRole) {
        return roleRepository.findRoleByRefRole(refRole);
    }

    @Override
    public Page<Role> findAllPageable(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Page<Role> findAllPageableWithNotStatus(Pageable pageable, StatusEnum status) {
        return roleRepository.findAll(setCretieria(status),pageable);
    }

    @Override
    public void updateRole(Role role) {
        if(!role.getEditable())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ROLE_NOT_EDITABLE);
        roleRepository.save(role);
    }

    @Override
    public void updateStatusRole(Role role, StatusEnum status) {
        role.setStatus(status);
        roleRepository.save(role);
    }

    @Override
    public void deleteRoleByRef(String refRole) {
        Optional<Role> old = roleRepository.findRoleByRefRole(refRole);
        if(old.isEmpty())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ROLE_NOT_FOUND);
        if(!old.get().getRemovable())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ROLE_NOT_REMOVABLE);
        Role role = old.get();
        updateStatusRole(role,StatusEnum.DELETED);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    private Specification<Role> setCretieria(StatusEnum status) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.notEqual(root.get("status"), status));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
