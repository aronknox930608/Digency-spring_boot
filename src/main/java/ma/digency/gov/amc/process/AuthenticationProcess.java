package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.*;

public interface AuthenticationProcess {

    AuthenticationLoginResponse login(AuthenticationLoginRequest authRequest);

    AccountResponse createUser(AccountRequest userDtoRequest);

    UserDtoResponse updateUser(UserUpdateDtoRequest userUpdateDtoRequest);

    AssignRoleResponse assignRoleToUser(AssignRoleRequest assignRoleRequest);
}