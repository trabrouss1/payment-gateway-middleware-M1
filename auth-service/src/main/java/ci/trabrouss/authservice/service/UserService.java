package ci.trabrouss.authservice.service;

import ci.trabrouss.authservice.contract.response.APIResponse;
import ci.trabrouss.authservice.dto.UserCreateDTO;
import ci.trabrouss.authservice.dto.UserUpdateDTO;

public interface UserService {

    APIResponse<Void> createUser(UserCreateDTO userCreateDto, String logId);

    APIResponse<Void> updateUser(String id, UserUpdateDTO userUpdateDto, String logId);

    APIResponse<Void> deleteUser(String id, String logId);


}
