package ci.trabrouss.gatewayservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO (

        @Schema(
                description = "Le nom d'utilisateur",
                example = "admin",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "Le nom de l'utilisateur")
        String username,

        @Schema(
                description = "Mot de passe de l'utilisateur",
                example = "admin",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "Le mot de passe est obligatoire")
        String password

) {}
