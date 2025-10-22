package ci.trabrouss.gatewayservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {
    private TokenDTO accessToken;
    private TokenDTO refreshToken;
    private String tokenType;
    private UserInfoDTO data;
}
