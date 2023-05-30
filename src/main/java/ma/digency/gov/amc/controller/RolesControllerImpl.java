package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.shared.RoleDto;
import ma.digency.gov.amc.process.RoleProcess;
import ma.digency.gov.amc.utils.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Roles")
public class RolesControllerImpl implements RolesController{
    @Autowired
    private RoleProcess roleProcess;

    @Override
    public ResponseEntity<Void> createRole(RoleDto role) {
        roleProcess.addRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> updateRole(RoleDto role,String ref) {
        role.setRefRole(ref);
        roleProcess.updateRole(role);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> deleteRole(String refRole) {
        roleProcess.deleteRole(refRole);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<RoleDto> getRole(String refRole) {
        return ResponseEntity.status(HttpStatus.OK).body(
                roleProcess.findRoleByRef(refRole)
        );
    }

    @Override
    public ResponseEntity<List<RoleDto>> getRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(
                roleProcess.findAll()
        );
    }

    @Override
    public ResponseEntity<PageableResponse<RoleDto>> getRolesPageable(Integer page, Integer size) {
        Pageable pageable = SearchUtils.createPageable(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(
                roleProcess.findAllPageable(pageable)
        );
    }

    @Override
    public ResponseEntity<PageableResponse<RoleDto>> getRolesPageableNotDeleted(Integer page, Integer size) {
        Pageable pageable = SearchUtils.createPageable(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(
                roleProcess.findAllPageableNotDeleted(pageable)
        );
    }


}
