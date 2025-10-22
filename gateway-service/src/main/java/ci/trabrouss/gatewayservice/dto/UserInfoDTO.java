package ci.trabrouss.gatewayservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDTO {
    private Long userId;
    private String username;
    private String nom;
    private String prenom;
}
