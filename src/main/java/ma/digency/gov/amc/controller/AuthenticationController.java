package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("authentication/")
public interface AuthenticationController {

    @PostMapping(path = "login/deprecated")
    ResponseEntity<LoginResponse> login(@RequestBody AuthenticationLoginRequest authRequest);

    @GetMapping(path = "login")
    ResponseEntity<Void> login();

    @PostMapping(path = "create-user")
    ResponseEntity<Void> createUser(@RequestBody AccountRequest userDtoRequest);

    @PutMapping(path = "update-user")
    ResponseEntity<UserDtoResponse> updateUser(@RequestBody UserUpdateDtoRequest userUpdateDtoRequest);

    @PostMapping(path = "assign-role")
    ResponseEntity<AssignRoleResponse> assignRoleToUser(@RequestBody AssignRoleRequest assignRoleRequest);
}
