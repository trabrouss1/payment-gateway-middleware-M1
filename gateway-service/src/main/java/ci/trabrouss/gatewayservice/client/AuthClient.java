package ci.trabrouss.gatewayservice.client;

import ci.trabrouss.gatewayservice.dto.AuthRequestDTO;
import ci.trabrouss.gatewayservice.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AUTH-SERVICE")
public interface AuthClient {

    @PostMapping("/api/v1/auth/login")
    ResponseEntity<AuthResponseDTO> auth(@RequestBody AuthRequestDTO authRequestDTO);

}
