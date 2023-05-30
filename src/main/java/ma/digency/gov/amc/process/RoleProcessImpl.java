package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.shared.RoleDto;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.RoleMapper;
import ma.digency.gov.amc.repository.entity.shared.Role;
import ma.digency.gov.amc.service.shared.RoleService;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleProcessImpl implements RoleProcess{
    private final RoleService roleService;

    private final ReferenceSequenceService referenceSequenceService;

    private final RoleMapper roleMapper;

    @Override
    public void addRole(RoleDto roleDto) {
        Role role = roleMapper.roleDtoToRole(roleDto);
        role.setRefRole(this.referenceSequenceService.generateRefRole());
        roleService.addRole(role);
    }

    @Override
    public void updateRole(RoleDto roleDto) {
        Optional<Role> old = roleService.findRoleByRef(roleDto.getRefRole());
        if(old.isEmpty())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ROLE_NOT_FOUND);
        Role role = roleMapper.updateRoleFromDto(roleDto,old.get());
        roleService.updateRole(role);
    }

    @Override
    public void deleteRole(String refRole) {
        roleService.deleteRoleByRef(refRole);
    }

    @Override
    public RoleDto findRoleByRef(String refRole) {
        Optional<Role> role = roleService.findRoleByRef(refRole);
        if(role.isEmpty())
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ROLE_NOT_FOUND);
        return roleMapper.roleToRoleDto(role.get());
    }

    @Override
    public List<RoleDto> findAll() {
        return roleMapper.rolesToRolesDto(roleService.findAll());
    }

    @Override
    public PageableResponse<RoleDto> findAllPageable(Pageable pageable) {
        Page<RoleDto> roles = roleService.findAllPageable(pageable)
                .map(this.roleMapper::roleToRoleDto);
        return new PageableResponse<>(roles);
    }

    @Override
    public PageableResponse<RoleDto> findAllPageableNotDeleted(Pageable pageable) {
        Page<RoleDto> roles = roleService.findAllPageableWithNotStatus(pageable, StatusEnum.DELETED)
                .map(this.roleMapper::roleToRoleDto);
        return new PageableResponse<>(roles);
    }
}
