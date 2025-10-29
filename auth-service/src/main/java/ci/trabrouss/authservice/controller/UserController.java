package ci.trabrouss.authservice.controller;

import ci.trabrouss.authservice.Utils.Utils;
import ci.trabrouss.authservice.contract.response.APIResponse;
import ci.trabrouss.authservice.controller.api.UserApi;
import ci.trabrouss.authservice.dto.UserCreateDTO;
import ci.trabrouss.authservice.dto.UserUpdateDTO;
import ci.trabrouss.authservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    public ResponseEntity<APIResponse<Void>> createUser(UserCreateDTO requestDTO) {
        return ResponseEntity.ok(userService.createUser(requestDTO, Utils.generateLogId()));
    }

    public ResponseEntity<APIResponse<Void>> updateUser(String id, UserUpdateDTO requestDTO) {
        return ResponseEntity.ok(userService.updateUser(id, requestDTO, Utils.generateLogId()));
    }

    public ResponseEntity<APIResponse<Void>> deleteUser(String id) {
        return ResponseEntity.ok(userService.deleteUser(id, Utils.generateLogId()));
    }
}
