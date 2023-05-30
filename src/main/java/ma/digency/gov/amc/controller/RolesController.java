package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.PageableResponse;
import ma.digency.gov.amc.dto.shared.RoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RequestMapping("admin/role")
public interface RolesController {

    @PostMapping
    ResponseEntity<Void> createRole(@RequestBody RoleDto role);

    @PutMapping(path="/{ref}")
    ResponseEntity<Void> updateRole(@RequestBody RoleDto role,@PathVariable("ref") @NotBlank String refRol);

    @DeleteMapping(path="/{ref}")
    ResponseEntity<Void> deleteRole(@PathVariable("ref") @NotBlank String refRole);

    @GetMapping(path = "/{ref}")
    ResponseEntity<RoleDto> getRole(@PathVariable("ref") @NotBlank String refRole);

    @GetMapping()
    ResponseEntity<List<RoleDto>> getRoles();

    @GetMapping(path="/data")
    ResponseEntity<PageableResponse<RoleDto>> getRolesPageable(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @GetMapping(path="/manage")
    ResponseEntity<PageableResponse<RoleDto>> getRolesPageableNotDeleted(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

}
