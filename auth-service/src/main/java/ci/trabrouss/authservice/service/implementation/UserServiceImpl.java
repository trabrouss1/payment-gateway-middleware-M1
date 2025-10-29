package ci.trabrouss.authservice.service.implementation;

import ci.trabrouss.authservice.Exception.CustomAPIException;
import ci.trabrouss.authservice.contract.response.APIResponse;
import ci.trabrouss.authservice.dto.UserCreateDTO;
import ci.trabrouss.authservice.dto.UserUpdateDTO;
import ci.trabrouss.authservice.entity.User;
import ci.trabrouss.authservice.mapper.UserMapper;
import ci.trabrouss.authservice.repository.UserRepository;
import ci.trabrouss.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public APIResponse<Void> createUser(UserCreateDTO requestDTO, String logId) {
        log.info("Starting creating user {} ", logId);

        User users = userMapper.toEntity(requestDTO);
        String hashedPassword = new BCryptPasswordEncoder().encode(requestDTO.password());
        users.setPassword(hashedPassword);
        userRepository.save(users);

        log.info("user created");
        return APIResponse.buildVoidResponse(HttpStatus.CREATED, "user created");
    }

    @Override
    public APIResponse<Void> updateUser(String id, UserUpdateDTO userUpdateDto, String logId) {
        log.info("Starting updating user {} ", logId);

        User user = userRepository.findById(id).orElseThrow(() -> new CustomAPIException(HttpStatus.NOT_FOUND, "user not found"));
        userMapper.updateUserFromDto(userUpdateDto, user);
        userRepository.save(user);

        log.info("user updated");
        return APIResponse.buildVoidResponse(HttpStatus.OK, "user updated");
    }

    @Override
    public APIResponse<Void> deleteUser(String id, String logId) {
        log.info("Starting deleting user {} ", logId);

        userRepository.findById(id).orElseThrow(()-> new CustomAPIException(HttpStatus.NOT_FOUND,"Aucune permission trouvée avec l'id"));
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        userRepository.delete(user);

        log.info("user deleted");
        return APIResponse.buildVoidResponse(HttpStatus.OK, "user deleted");
    }
}
