package ci.trabrouss.authservice.controller.api;

import ci.trabrouss.authservice.contract.response.APIResponse;
import ci.trabrouss.authservice.dto.UserCreateDTO;
import ci.trabrouss.authservice.dto.UserUpdateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Tag(name = "Gestion des utilisateurs", description = "API da Gestion des utilisateurs")
public interface UserApi {

    @PostMapping
    ResponseEntity<APIResponse<Void>> createUser(UserCreateDTO userCreateDto);

    ResponseEntity<APIResponse<Void>> updateUser(String id, UserUpdateDTO userUpdateDto);

    ResponseEntity<APIResponse<Void>> deleteUser(String id);
}
