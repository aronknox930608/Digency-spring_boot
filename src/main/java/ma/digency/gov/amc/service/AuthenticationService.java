package ma.digency.gov.amc.service;

import ma.digency.gov.amc.dto.*;
import ma.digency.gov.amc.repository.entity.shared.Account;

public interface AuthenticationService {

    AuthenticationLoginResponse login(AuthenticationLoginRequest authRequest);

    AccountResponse createUser(Account userDtoRequest);

    UserDtoResponse updateUser(UserUpdateDtoRequest userUpdateDtoRequest);

    AssignRoleResponse assignRoleToUser(AssignRoleRequest assignRoleRequest);
}
