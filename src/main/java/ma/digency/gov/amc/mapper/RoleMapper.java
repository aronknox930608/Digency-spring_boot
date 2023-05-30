package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.shared.RoleDto;
import ma.digency.gov.amc.repository.entity.shared.Role;

import java.util.List;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface RoleMapper {
    Role roleDtoToRole(RoleDto roleDto);

    List<Role> rolesDtoToRoles(List<RoleDto> roleDtoList);

    RoleDto roleToRoleDto(Role role);

    List<RoleDto> rolesToRolesDto(List<Role> roleList);

    Role updateRoleFromDto(RoleDto roleDto,Role role);

}
